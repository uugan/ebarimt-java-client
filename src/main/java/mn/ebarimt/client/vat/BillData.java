package mn.ebarimt.client.vat;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

///
/// Баримтын мэдээлэл
/// 
public class BillData extends Bill {
    /// 
    /// Баримтын НӨАТ агуулаагүй хөлийн дүн
    /// 
    public String amount;
    /// 
    /// Баримтын НӨАТ дүн
    /// 
    public String vat;
    /// 
    /// Бэлэн төлбөрийн дүн
    /// 
    public String cashAmount;
    /// 
    /// Бэлэн бус төлбөрийн дүн
    /// 
    public String nonCashAmount;
    /// 
    /// Баримтын дугаарыг давхцуулахгүйн тулд хэрэглэх залгавар /тухайн өдөрийн хэд дэхь баримт гэм/ : 6 оронтой
    /// 
    public String billIdSuffix;
    /// 
    /// Авсан бараа үйлчилгээ
    /// 
    public List<BillDetail> stocks;
    /// 
    /// Нийслэл хотын албан татвар
    /// 
    public String cityTax;
    /// 
    /// Бэлэн бус гүйлгээний мэдээлэл
    /// 
    public List<BillBankTransaction> bankTransactions;
    /// 
    /// Аймгийн код
    /// 
    public String districtCode;
    /// 
    /// Хэрэглэгчийн системийн дахин давтагдашгүй дугаар : 6 оронтой тоон утга
    /// 
    public String posNo;
    /// 
    /// Баримтын төрөл : 1-Дэлгүүр 2-Худалдан авалт 3-Борлуулалт
    /// 
    public String billType;
    /// 
    /// Худалдан авагч байгууллагын ТТД эсвэл Иргэний регистерийн дугаар
    /// 
    public String customerNo;
    /// 
    /// Ажилтны мэдээлэл
    /// 
    public WorkerInfo worker_info;

    private transient boolean isVatCount;
    private transient String _url;

    public BillData() {
        stocks = new ArrayList<>();
        bankTransactions = new ArrayList<>();
        billType = "1";
        customerNo = "";
        amount = "0.00";
        cashAmount = "0.00";
        nonCashAmount = "0.00";
        vat = "0.00";
        cityTax = "0.00";
        billIdSuffix = "";
        posNo = "000000";
        districtCode = "25"; // Sukhbaatar district by default
        isVatCount = true;

    }

    public String get_url() {
        return _url;
    }

    public Bill set_url(String _url) {
        this._url = _url;
        return this;
    }


    @Override
    public Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total) {
        DecimalFormat df = new DecimalFormat("#0.00");
        BillDetail billDetail = new BillDetail();
        billDetail.code = bunaCode;
        billDetail.name = productName;
        billDetail.measureUnit = "ш";
        billDetail.qty = df.format(qty);
        billDetail.unitPrice = df.format(unitPrice);
        billDetail.totalAmount = df.format(total);
        if (isVatCount) {
            billDetail.vat = df.format((total - total / 1.1));
        }
        else {
            billDetail.vat = "0.00";
        }
        billDetail.barCode = barcode;
        billDetail.cityTax = "0.00";
        stocks.add(billDetail);

        Double ttl = Double.parseDouble(amount);
        ttl += total;


        amount = df.format(ttl);
        if (isVatCount) {
            Double tvat = ttl - ttl / 1.1;
            vat = df.format(tvat);
        }
        else { vat = "0.00"; }
        cashAmount = amount;
        return this;
    }


    @Override
    public Bill setCorporate(String customerNo) {
        this.billType = "3";
        this.customerNo = customerNo;
        return this;
    }

    @Override
    public String getURL() {
        return _url;
    }


    @Override
    public Bill setCountVat(boolean countVat) {
        isVatCount = countVat;
        return this;
    }


    @Override
    public Bill setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src) {
        WorkerInfo ui = new WorkerInfo();
        ui.username = workerName;
        ui.departmentname = departmentName;
        ui.userID = userID;
        ui.paymenttype = paymentType;
        ui.sourcename = src;
        worker_info = ui;
        return this;
    }


    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    @Override
    public Bill setReturnBillId(String billId) {
        return null;
    }


}

