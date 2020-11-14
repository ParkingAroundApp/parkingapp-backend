package com.fptu.paa.service;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
	public String saveTopUpTransaction(String userID, String amount, String description, String createTime)
			throws Exception;

	public String getTransactionByUserIdInMonth(String userID, String year, String month) throws Exception;

	public String getTransactionByUserId(String userID, String pageSize, String bookmark) throws Exception;

	public String getNFCTransactionBySerial(String nfcSerial, String pageSize, String bookmark) throws Exception;

	public String getAllPayemntTransactionByType(boolean isNFC, String pageSize, String bookmark) throws Exception;

	public String getAllTopUpTransaction(String pageSize, String bookmark) throws Exception;

	public String getAllTransactionInMonth(String startDate, String endDate, boolean isPaymentTransaction, String pageSize,
			String bookmark) throws Exception;

	public String getTransactionDetail(String createTime, String id) throws Exception;

}
