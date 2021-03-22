package com.github.uugan.ebarimt.bean;

public abstract class ReturnRequest implements VatMessage {
    public abstract String toJsonStr();

    /**
     * @param workerName     worker name
     * @param departmentName worker departmentname
     * @param userID         userId
     * @param paymentType    paymentType
     * @param src            source
     * @return
     */
    public abstract ReturnRequest setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src);

    /**
     * @param workerInfo workerInfo
     * @return
     */
    public abstract ReturnRequest setWorkerInfo(WorkerInfo workerInfo);

    /**
     * @param billId billId
     * @return Bill
     */
    public abstract ReturnRequest setReturnBillId(String billId);
}
