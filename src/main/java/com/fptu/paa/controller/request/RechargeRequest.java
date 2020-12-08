package com.fptu.paa.controller.request;

public class RechargeRequest {
    private String userID;
    private String amount;
    private String description;
    private String createTime;

    public RechargeRequest() {

    }

    public RechargeRequest(String userID, String amount, String description, String createTime) {
        super();
        this.userID = userID;
        this.amount = amount;
        this.description = description;
        this.createTime = createTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
