package com.fptu.paa.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {
	
	@GetMapping(value = "get")
	public String getBike() {
		String result = "";
		// Load a file system based wallet for managing identities.
				Path walletPath = Paths.get("wallet");
				try {
					Wallet wallet = Wallet.createFileSystemWallet(walletPath);

					// load a CCP
					Path networkConfigPath = Paths.get("connection.json");

					Gateway.Builder builder = Gateway.createBuilder();
					builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

					// create a gateway connection
					Gateway gateway = builder.connect();
					// get the network and contract
					Network network = gateway.getNetwork("mychannel");
					Contract contract = network.getContract("mycc");

					byte[] tempResult;
					tempResult = contract.evaluateTransaction("queryBikeByPlateNumber", "59P2-81246");
					result = new String(tempResult);
					gateway.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
		return result;
	}
}
