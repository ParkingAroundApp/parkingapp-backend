package com.fptu.paa.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.springframework.stereotype.Service;

import com.fptu.paa.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Override
	public String checkInByBikeID(String bikeID, String ownerCheckInID, String checkInTime, String checkInBikeImage,
			String checkInFaceImage) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {
			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("mycc");

			contract.submitTransaction("createTicket", bikeID, "0", ownerCheckInID, checkInTime, checkInBikeImage,
					checkInFaceImage);
		}

		return null;
	}

	@Override
	public String checkOutByBikeID(String ticketKey, String ownerCheckOutId, String checkOutTime,
			String checkOutBikeImage, String checkOutFaceImage, String paymentType) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {
			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("mycc");

			contract.submitTransaction("checkOutByBike", ticketKey, ownerCheckOutId, checkOutTime, checkOutBikeImage,
					checkOutFaceImage, paymentType);
		}
		return null;
	}

	@Override
	public String getCheckOutTicketByBikeID(String bikeID) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {
			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("mycc");
			byte[] result;
			result = contract.evaluateTransaction("getCheckoutTicketByBikeID", bikeID);
			
			//Close gateway
			gateway.close();
			return new String(result);
		}
	}

	@Override
	public String getListTicketByBikeID(String bikeID) throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {
			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("mycc");
			byte[] result;
			result = contract.evaluateTransaction("queryTicketByBikeID", bikeID);

			//Close gateway
			gateway.close();
			return new String(result);
		}
	}

	@Override
	public String getAllTicket() throws Exception {
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallet.createFileSystemWallet(walletPath);

		// load a CCP
		Path networkConfigPath = Paths.get("connection.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {
			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("mycc");
			byte[] result;
			result = contract.evaluateTransaction("queryAllTicket");

			//Close gateway
			gateway.close();
			return new String(result);
		}
	}

}
