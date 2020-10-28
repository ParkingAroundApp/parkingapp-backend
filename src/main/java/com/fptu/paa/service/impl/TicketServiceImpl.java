package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.springframework.stereotype.Service;

import com.fptu.paa.service.TicketService;
import com.fptu.paa.utils.FabricGatewaySingleton;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Override
	public String checkInByBikeID(String bikeID, String ownerCheckInID, String customerId, String checkInTime,
			String checkInBikeImage, String checkInFaceImage) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.submitTransaction("createTicket", bikeID, "", customerId, ownerCheckInID, checkInTime,
				checkInBikeImage, checkInFaceImage);
//		gateway.close();
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String checkOutByID(String ticketKey, String ownerCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.submitTransaction("checkOut", ticketKey, ownerCheckOutId, checkOutTime, checkOutBikeImage,
				checkOutFaceImage, paymentType);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getCheckOutTicketByBikeID(String bikeID) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");

		byte[] result;
		result = contract.evaluateTransaction("getCheckoutTicket", bikeID, "");

		System.out.println("Finish get checkout" + new String(result));
		return new String(result);

	}

	@Override
	public String getCheckOutTicketByNFC(String NFCSerial) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("getCheckoutTicket", "", NFCSerial);
		return new String(result);

	}

	@Override
	public String getListBikeTicket(String bikeID) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("queryTicketById", "", bikeID);

		System.out.println("Finish getListTicketByBikeID");
		return new String(result);
	}

	@Override
	public String getListNFCTicket(String NFCSerial) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("queryTicketById", NFCSerial, "");
		return new String(result);
	}

	@Override
	public String getAllTicket() throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("queryAllTicket");
		return new String(result);

	}

	@Override
	public String checkInByNFCID(String NFCID, String ownerCheckInID, String checkInTime, String checkInBikeImage,
			String checkInFaceImage) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.submitTransaction("createTicket", "", NFCID, "", ownerCheckInID, checkInTime,
				checkInBikeImage, checkInFaceImage);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListTicketByCustomerID(String customerID) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("queryTicketByCustomer", customerID);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTicketDetail(String key) throws Exception {
		Gateway gateway = FabricGatewaySingleton.getInstance().gateway;
		// get the network and contract
		Network network = gateway.getNetwork("mychannel");
		Contract contract = network.getContract("mycc");
		byte[] result;
		result = contract.evaluateTransaction("queryByKey", key);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

}
