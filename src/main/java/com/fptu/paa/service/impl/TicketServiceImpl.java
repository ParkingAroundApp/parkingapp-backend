package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.hyperledger.fabric.gateway.Contract;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fptu.paa.service.TicketService;
import com.fptu.paa.utils.FabricGatewaySingleton;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	private final String PAGE_SIZE = "20";

	@Override
	public String checkInByBikeID(String licensePlate, String bikeID, String staffCheckInID, String customerId,
			String checkInTime, String checkInBikeImage, String checkInFaceImage) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("createTicket", licensePlate, bikeID, "", customerId, staffCheckInID,
				checkInTime, checkInBikeImage, checkInFaceImage);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String checkOutByID(String ticketKey, String staffCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType, String price, String userID) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("checkOut", ticketKey, staffCheckOutId, checkOutTime, checkOutBikeImage,
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
	public String getListNFCTicket(String NFCSerial, String startDate, String endDate, String pageSize, String bookmark)
			throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject selectorOptions = new JSONObject();
		JSONObject sortValue = new JSONObject();
		JSONObject checkinTime = new JSONObject();

		JSONArray sortOptions = new JSONArray();
		checkinTime.put("$gt", startDate);
		if (!endDate.isEmpty()) {
			checkinTime.put("$lt", endDate);
		}
		query.put("selector", selectorOptions.put("nfcNumber", NFCSerial));
		query.put("selector", selectorOptions.put("checkinTime", checkinTime));
		query.put("selector", selectorOptions.put("type", "ticket"));
		query.put("sort", sortOptions.put(sortValue.put("checkinTime", "desc")));
		System.out.println(query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getAllTicket(String startDate, String endDate, String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject selectorOptions = new JSONObject();
		JSONObject sortValue = new JSONObject();
		JSONObject checkinTime = new JSONObject();

		JSONArray sortOptions = new JSONArray();
		checkinTime.put("$gt", startDate);
		checkinTime.put("$lt", endDate);
		query.put("selector", selectorOptions.put("checkinTime", checkinTime));
		query.put("selector", selectorOptions.put("type", "ticket"));
		query.put("sort", sortOptions.put(sortValue.put("checkinTime", "desc")));
		System.out.println(query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;

	}

	@Override
	public String checkInByNFCID(String licensePlate, String NFCID, String staffCheckInID, String checkInTime,
			String checkInBikeImage, String checkInFaceImage) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("createTicket", licensePlate, "", NFCID, "", staffCheckInID, checkInTime,
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
	public String getTicketByStaffIdAndDate(String staffID, String date, String pageSize, String bookmark,
			boolean isCheckIn) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject options = new JSONObject();
		JSONObject checkInTime = new JSONObject();
		checkInTime.put("$gt", date + "-" + "01:00:00:000");
		checkInTime.put("$lt", date + "-" + "24:00:00:000");
		if (isCheckIn) {
			query.put("selector", options.put("staffCheckInID", staffID));
		} else {
			query.put("selector", options.put("staffCheckOutID", staffID));
		}
		query.put("selector", options.put("checkinTime", checkInTime));
		query.put("selector", options.put("type", "ticket"));
		System.out.println("QUERY getTicketByOnwerIdAndDate: " + query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);

		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTicketInDate(String date, String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject options = new JSONObject();
		JSONObject checkInTime = new JSONObject();
		checkInTime.put("$gt", date + "-" + "01:00:00:000");
		checkInTime.put("$lt", date + "-" + "24:00:00:000");

		query.put("selector", options.put("checkinTime", checkInTime));
		query.put("selector", options.put("type", "ticket"));
		System.out.println("QUERY getTicketByOnwerIdAndDate: " + query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);

		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String reportTicket(String checkInTime, String id, String staffCheckoutId, String reportTime,
			String bikeImage, String faceImage) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("reportTicket", checkInTime, id, staffCheckoutId, reportTime, bikeImage,
				faceImage);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getListTicketByPlateNumber(String plateNumber, String startDate, String endDate, String pageSize,
			String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject selectorOptions = new JSONObject();
		JSONObject sortValue = new JSONObject();
		JSONObject checkinTime = new JSONObject();

		JSONArray sortOptions = new JSONArray();
		checkinTime.put("$gt", startDate);
		if (!endDate.isEmpty()) {
			checkinTime.put("$lt", endDate);
		}
		query.put("selector", selectorOptions.put("licensePlate", plateNumber));
		query.put("selector", selectorOptions.put("checkinTime", checkinTime));
		query.put("selector", selectorOptions.put("type", "ticket"));
		query.put("sort", sortOptions.put(sortValue.put("checkinTime", "desc")));
		// Submit query
		result = contract.evaluateTransaction("queryAllTicketWithPagination", query.toString(), pageSize, bookmark);

		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

}
