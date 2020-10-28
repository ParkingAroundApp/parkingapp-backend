package com.fptu.paa.service;

import org.springframework.stereotype.Service;

@Service
public interface TicketService {
	public String checkInByBikeID(String bikeID, String ownerCheckInID, String customerId, String checkInTime,
			String checkInBikeImage, String checkInFaceImage) throws Exception;

	public String checkOutByID(String ticketKey, String ownerCheckOutId, String checkOutTime, String checkOutBikeImage,
			String checkOutFaceImage, String paymentType) throws Exception;

	public String getCheckOutTicketByBikeID(String bikeID) throws Exception;

	public String getListBikeTicket(String bikeID) throws Exception;

	public String getTicketDetail(String key) throws Exception;

	public String getListTicketByCustomerID(String customerID) throws Exception;
	
	public String getAllTicket() throws Exception;

	public String checkInByNFCID(String NFCID, String ownerCheckInID, String checkInTime, String checkInBikeImage,
			String checkInFaceImage) throws Exception;

	public String getCheckOutTicketByNFC(String NFCSerial) throws Exception;

	public String getListNFCTicket(String NFCSerial) throws Exception;
	

}
