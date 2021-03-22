package com.github.uugan.ebarimt.bean;


public abstract class BillRequest implements VatMessage {

    /**
     * @param bunaCode    Item Buna 2.0 code
     * @param barcode     Item Barcode
     * @param productName Item name
     * @param qty         Item quantity
     * @param unitPrice   Item unit price
     * @param total       Item total price
     * @return Bill
     */
    public abstract BillRequest addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total);

    public abstract BillRequest addStock(BillDetail billDetail);

    /**
     * @param workerName     worker name
     * @param departmentName worker departmentname
     * @param userID         userId
     * @param paymentType    paymentType
     * @param src            source
     * @return
     */
    public abstract BillRequest setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src);

    /**
     * @param workerInfo workerInfo
     * @return
     */
    public abstract BillRequest setWorkerInfo(WorkerInfo workerInfo);

    public abstract String toJsonStr();

     /**
     * @param countVat calculate VAT for items
     * @return Bill
     */
    public abstract BillRequest setCountVat(boolean countVat);

    /**
     * @param customerNo Customer company's tax number
     * @return
     */
    public abstract BillRequest setCorporate(String customerNo);

    public abstract BillRequest setGroup(boolean group);

    public abstract BillRequest addBill(BillRequestData billData);

    public abstract BillRequest setRegisterNo(String registerNo);

    public abstract BillRequest setInternalId(String internalId);
}
