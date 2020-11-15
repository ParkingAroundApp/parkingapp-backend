package com.fptu.paa.service;

import org.springframework.stereotype.Service;

@Service
public interface TicketService {
	public String checkInByBikeID(String licensePlate, String bikeID, String ownerCheckInID, String customerId,
			String checkInTime, String checkInBikeImage, String checkInFaceImage) throws Exception;

	public String getCheckOutTicketByBikeID(String bikeID) throws Exception;

	public String getListBikeTicket(String bikeID, String bookmark) throws Exception;

	public String getTicketDetail(String key) throws Exception;

	public String getListTicketByCustomerID(String customerID, String year, String month) throws Exception;

	public String getTicketByOnwerIdAndDate(String ownerID, String date, String pageSize, String bookmark,
			boolean isCheckIn) throws Exception;

	// FOR NFC AND BIKE
	public String getTicketInDate(String date, String pageSize, String bookmark)throws Exception;
	
	public String getListTicketByPlateNumber(String plateNumber, String startDate, String endDate, String pageSize,
			String bookmark) throws Exception;

	public String checkOutByID(String ticketKey, String ownerCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType, String price, String userID) throws Exception;

	public String getAllTicket(String startDate, String endDate, String pageSize, String bookmark) throws Exception;

	public String getTicketHistory(String checkInTime, String id) throws Exception;

	public String reportTicket(String checkInTime, String id, String ownerCheckoutId, String reportTime,
			String bikeImage, String faceImage) throws Exception;

	// FOR NFC
	public String checkInByNFCID(String licensePlate, String NFCID, String ownerCheckInID, String checkInTime,
			String checkInBikeImage, String checkInFaceImage) throws Exception;

	public String getCheckOutTicketByNFC(String NFCSerial) throws Exception;

	public String getListNFCTicket(String NFCSerial, String startDate, String endDate, String pageSize, String bookmark)
			throws Exception;

}
