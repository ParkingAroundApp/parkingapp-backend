package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.hyperledger.fabric.gateway.Contract;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fptu.paa.service.TicketService;
import com.fptu.paa.utils.FabricGatewaySingleton;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	private final String PAGE_SIZE = "20";

	@Override
	public String checkInByBikeID(String licensePlate, String bikeID, String ownerCheckInID, String customerId,
			String checkInTime, String checkInBikeImage, String checkInFaceImage) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("createTicket", licensePlate, bikeID, "", customerId, ownerCheckInID,
				checkInTime, checkInBikeImage, checkInFaceImage);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String checkOutByID(String ticketKey, String ownerCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType, String price, String userID) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("checkOut", ticketKey, ownerCheckOutId, checkOutTime, checkOutBikeImage,
				checkOutFaceImage, paymentType, price, userID);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getCheckOutTicketByBikeID(String bikeID) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("getCheckoutTicket", bikeID, "");
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getCheckOutTicketByNFC(String NFCSerial) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("getCheckoutTicket", "null", NFCSerial);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListBikeTicket(String bikeID, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		JSONObject query = new JSONObject();
		JSONObject option = new JSONObject();
		query.put("selector", option.put("bikeID", bikeID));
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), PAGE_SIZE, bookmark);
		System.out.println("Finish getListTicketByBikeID");
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListNFCTicket(String NFCSerial, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;

		JSONObject query = new JSONObject();
		JSONObject option = new JSONObject();
		query.put("selector", option.put("nfcNumber", NFCSerial));
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), PAGE_SIZE, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getAllTicket(String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		JSONObject query = new JSONObject();
		JSONObject option = new JSONObject();
		query.put("selector", option.put("type", "ticket"));
		byte[] result;
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;

	}

	@Override
	public String checkInByNFCID(String licensePlate, String NFCID, String ownerCheckInID, String checkInTime,
			String checkInBikeImage, String checkInFaceImage) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("createTicket", licensePlate, "", NFCID, "", ownerCheckInID, checkInTime,
				checkInBikeImage, checkInFaceImage);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListTicketByCustomerID(String customerID, String year, String month) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("queryTicketByUserID", customerID, year, month);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTicketDetail(String key) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("queryByKey", key);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListTicketInMonth(String year, String month, String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("queryTicketInMonth", year, month, pageSize, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTicketHistory(String checkInTime, String id) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("queryKeyHistory", checkInTime, id);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTicketByOnwerIdAndDate(String ownerID, String date, String pageSize, String bookmark,
			boolean isCheckIn) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject options = new JSONObject();
		JSONObject checkInTime = new JSONObject();
		if (isCheckIn) {
			query.put("selector", options.put("ownerCheckInID", ownerID));
		} else {
			query.put("selector", options.put("ownerCheckOutID", ownerID));
		}
		checkInTime.put("$regex", String.format("^%s", date));
		query.put("selector", options.put("checkinTime", checkInTime));
		System.out.println("QUERY getTicketByOnwerIdAndDate: " + query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);

		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String reportTicket(String checkInTime, String key) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("reportTicket", checkInTime, key);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

}
