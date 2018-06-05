package com.jesse.stream;

/**
 * Created by Jesse on 2018/6/4.
 */
public class OrdersDO {
    private Long AppId;
    private Long TradeAmount;
    private int Status;

    public Long getAppId() {
        return AppId;
    }

    public void setAppId(Long appId) {
        AppId = appId;
    }

    public Long getTradeAmount() {
        return TradeAmount;
    }

    public void setTradeAmount(Long tradeAmount) {
        TradeAmount = tradeAmount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
