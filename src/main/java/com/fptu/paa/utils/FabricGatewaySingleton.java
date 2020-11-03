package com.fptu.paa.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FabricGatewaySingleton {
	private static FabricGatewaySingleton gatewaySingleton = null;

	public Contract contract = null;

	private FabricGatewaySingleton() {
		try {
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallet.createFileSystemWallet(walletPath);

			// load a CCP
			Path networkConfigPath = Paths.get("connection.json");

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
			
			// create a gateway connection
			Gateway gateway = builder.connect();
			Network network = gateway.getNetwork("mychannel");
			contract = network.getContract("parkingcc");
		} catch (IOException e) {
			log.info("FabricGatewaySingleton " + e.getMessage());

		}
	}

	public static FabricGatewaySingleton getInstance() {
		if (gatewaySingleton == null) {
			gatewaySingleton = new FabricGatewaySingleton();
		}
		return gatewaySingleton;
	}
}
