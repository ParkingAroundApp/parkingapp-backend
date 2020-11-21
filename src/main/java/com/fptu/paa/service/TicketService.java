package com.fptu.paa.service;

import org.springframework.stereotype.Service;

@Service
public interface TicketService {
	public String checkInByBikeID(String licensePlate, String bikeID, String staffCheckInID, String customerId,
			String checkInTime, String checkInBikeImage, String checkInFaceImage, String modelType) throws Exception;

	public String getCheckOutTicketByBikeID(String bikeID) throws Exception;

	public String getListBikeTicket(String bikeID, String bookmark) throws Exception;

	public String getTicketDetail(String key) throws Exception;

	public String getListTicketByCustomerID(String customerID, String year, String month) throws Exception;


	// FOR NFC AND BIKE
	public String getTicketByStaffIdAndDate(String staffID, String date, String pageSize, String bookmark,
											boolean isCheckIn) throws Exception;

	public String getTicketInDate(String date, String pageSize, String bookmark)throws Exception;
	
	public String getListTicketByPlateNumber(String plateNumber, String startDate, String endDate, String pageSize,
			String bookmark) throws Exception;

	public String checkOutByID(String ticketKey, String staffCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType, String price, String userID, String fareID) throws Exception;

	public String getAllTicket(String startDate, String endDate, String pageSize, String bookmark) throws Exception;

	public String getTicketHistory(String checkInTime, String id) throws Exception;

	public String reportTicket(String checkInTime, String id, String staffCheckoutId, String reportTime,
			String bikeImage, String faceImage, String note) throws Exception;

	// FOR NFC
	public String checkInByNFCID(String licensePlate, String NFCID, String staffCheckInID, String checkInTime,
			String checkInBikeImage, String checkInFaceImage, String modelType) throws Exception;

	public String getCheckOutTicketByNFC(String NFCSerial) throws Exception;

	public String getListNFCTicket(String NFCSerial, String startDate, String endDate, String pageSize, String bookmark)
			throws Exception;

}
