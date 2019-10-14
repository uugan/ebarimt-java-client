package mn.ebarimt.client.vat;

public abstract class Bill {

   /**
    * @param bunaCode Item Buna 2.0 code
    * @param barcode Item Barcode
    * @param productName Item name
    * @param qty Item quantity
    * @param unitPrice Item unit price
    * @param total Item total price
    * @return Bill
    */
    public abstract Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total);

   /**
    * @param workerName worker name
    * @param departmentName worker departmentname
    * @param userID userId
    * @param paymentType paymentType
    * @param src source
    * @return
    */
    public abstract Bill setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src);

    public abstract String toJsonStr();

   /**
    * @param billId billId
    * @return Bill
    */
    public abstract Bill setReturnBillId(String billId);

   /**
    * @param countVat calculate VAT for items
    * @return Bill
    */
    public abstract Bill setCountVat(boolean countVat);

   /**
    * @param customerNo Customer company's tax number
    * @return
    */
    public abstract Bill setCorporate(String customerNo);
    public abstract String getURL();
}
