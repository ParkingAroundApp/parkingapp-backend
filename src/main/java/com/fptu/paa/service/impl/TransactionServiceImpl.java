package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.hyperledger.fabric.gateway.Contract;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.TransactionType;
import com.fptu.paa.service.TransactionService;
import com.fptu.paa.utils.FabricGatewaySingleton;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Override
	public String saveTopUpTransaction(String userID, String amount, String description, String createTime)
			throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.submitTransaction("rechargeAccount", userID, amount, description, createTime);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTransactionByUserIdInMonth(String userID, String year, String month) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("queryTransactionByUserID", userID, year, month);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTransactionByUserId(String userID, String startDate, String endDate, String pageSize,
			TransactionType transactionType, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject selectorOptions = new JSONObject();
		JSONObject sortValue = new JSONObject();
		JSONObject checkinTime = new JSONObject();
		JSONArray sortOptions = new JSONArray();

		checkinTime.put("$gt", startDate);
		if (endDate != null && !endDate.isEmpty()) {
			checkinTime.put("$lt", endDate);
		}
		query.put("selector", selectorOptions.put("createTime", checkinTime));
		if (transactionType != null) {
			query.put("selector", selectorOptions.put("transactionType", transactionType.name()));
		}
		query.put("selector", selectorOptions.put("userID", userID));
		query.put("selector", selectorOptions.put("type", "transaction"));

		query.put("sort", sortOptions.put(sortValue.put("createTime", "desc")));
		query.put("use_index", "indexTransaction2Doc");
		System.out.println(query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTransactionWithPagination", query.toString(), pageSize,
				bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getNFCTransactionBySerial(String nfcSerial, String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		result = contract.evaluateTransaction("getNFCPaymentTransaction", nfcSerial, pageSize, bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getAllPayemntTransactionByType(boolean isNFC, String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject options = new JSONObject();

		query.put("selector", options.put("type", "transaction"));
		query.put("selector", options.put("transactionType",
				isNFC ? TransactionType.PAYMENT_NFC.name() : TransactionType.PAYMENT_BIKE.name()));

		System.out.println("QUERY getTransactionByUserId: " + query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTransactionWithPagination", query.toString(), pageSize,
				bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getAllTopUpTransaction(String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		// Create query
		JSONObject query = new JSONObject();
		JSONObject options = new JSONObject();

		query.put("selector", options.put("type", "transaction"));
		query.put("selector", options.put("transactionType", TransactionType.RECHARGE));

		System.out.println("QUERY getAllTransaction: " + query.toString());
		// Submit query
		result = contract.evaluateTransaction("queryAllTransactionWithPagination", query.toString(), pageSize,
				bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getTransactionDetail(String createTime, String id) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;
		String key = "TRANSACTION" + "_" + createTime + "_" + id;
		result = contract.evaluateTransaction("queryByKey", key);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

	@Override
	public String getAllTransactionInMonth(String startDate, String endDate, boolean isPaymentTransaction,
			String pageSize, String bookmark) throws Exception {
		Contract contract = FabricGatewaySingleton.getInstance().contract;
		byte[] result;

		JSONObject query = new JSONObject();
		JSONObject selectorOptions = new JSONObject();
		JSONObject sortValue = new JSONObject();
		JSONObject checkinTime = new JSONObject();
		JSONObject transactionType = new JSONObject();
		JSONArray sortOptions = new JSONArray();

		checkinTime.put("$gte", startDate);
		checkinTime.put("$lte", endDate);
		query.put("selector", selectorOptions.put("createTime", checkinTime));
		if (isPaymentTransaction) {
			query.put("selector", selectorOptions.put("$not", transactionType.put("transactionType", TransactionType.RECHARGE)));
		} else {
			query.put("selector", selectorOptions.put("transactionType", TransactionType.RECHARGE));
		}
		query.put("selector", selectorOptions.put("type", "transaction"));
		query.put("sort", sortOptions.put(sortValue.put("createTime", "desc")));
		query.put("use_index", "indexTransaction1Doc");
		System.out.println(query.toString());

		result = contract.evaluateTransaction("queryAllTransactionWithPagination", query.toString(), pageSize,
				bookmark);
		if (result.length > 0) {
			return new String(result);
		}
		return null;
	}

}
