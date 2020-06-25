package com.github.uugan.ebarimt.vat;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

///
/// Баримтын мэдээлэл
///
@SuperBuilder
public class BillData extends Bill {

    private final transient DecimalFormat df = new DecimalFormat("#0.00");
    /// 
    /// Баримтын НӨАТ агуулаагүй хөлийн дүн
    ///
    @Builder.Default
    public String amount = "0.00";
    /// 
    /// Баримтын НӨАТ дүн
    ///
    @Builder.Default
    public String vat = "0.00";
    /// 
    /// Бэлэн төлбөрийн дүн
    ///
    @Builder.Default
    public String cashAmount = "0.00";
    /// 
    /// Бэлэн бус төлбөрийн дүн
    ///
    @Builder.Default
    public String nonCashAmount = "0.00";
    /// 
    /// Баримтын дугаарыг давхцуулахгүйн тулд хэрэглэх залгавар /тухайн өдөрийн хэд дэхь баримт гэм/ : 6 оронтой
    ///
    @Builder.Default
    public String billIdSuffix = "";
    /// 
    /// Авсан бараа үйлчилгээ
    ///
    @Builder.Default
    public List<BillDetail> stocks = new ArrayList<>();
    /// 
    /// Нийслэл хотын албан татвар
    ///
    @Builder.Default
    public String cityTax = "0.00";
    /// 
    /// Бэлэн бус гүйлгээний мэдээлэл
    ///
    @Builder.Default
    public List<BillBankTransaction> bankTransactions = new ArrayList<>();
    /// 
    /// Аймгийн код
    ///
    @Builder.Default
    public String districtCode = "25"; //SBD by default
    /// 
    /// Хэрэглэгчийн системийн дахин давтагдашгүй дугаар : 6 оронтой тоон утга
    ///
    @Builder.Default
    public String posNo = "000000";
    /// 
    /// Баримтын төрөл : 1-Дэлгүүр 2-Худалдан авалт 3-Борлуулалт
    ///
    @Builder.Default
    public String billType = "1";
    /// 
    /// Худалдан авагч байгууллагын ТТД эсвэл Иргэний регистерийн дугаар
    ///
    @Builder.Default
    public String customerNo = "";
    /// 
    /// Ажилтны мэдээлэл
    /// 
    public WorkerInfo worker_info;

    @Builder.Default
    private transient boolean isVatCount = true;
    private transient String url;


    public String getUrl() {
        return url;
    }

    public Bill setUrl(String url) {
        this.url = url;
        return this;
    }


    @Override
    public Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total) {

        BillDetail billDetail = BillDetail.builder()
                .code(bunaCode)
                .name(productName)
                .measureUnit("ш")
                .qty(df.format(qty))
                .unitPrice(df.format(unitPrice))
                .totalAmount(df.format(total))
                .barCode(barcode)
                .cityTax("0.00")
                .build();

        if (isVatCount) {
            billDetail.setVat(df.format((total - total / 1.1)));
        } else {
            billDetail.setVat("0.00");
        }
        stocks.add(billDetail);

        calcTotal(total);

        return this;
    }

    public Bill addStock(BillDetail stock) {
        stocks.add(stock);
        calcTotal(Double.parseDouble(stock.getTotalAmount()));
        return this;
    }

    private void calcTotal(double total) {
        Double ttl = Double.parseDouble(amount);
        ttl += total;
        amount = df.format(ttl);
        if (isVatCount) {
            Double tvat = ttl - ttl / 1.1;
            vat = df.format(tvat);
        } else {
            vat = "0.00";
        }
        cashAmount = amount;
    }

    @Override
    public Bill setCorporate(String customerNo) {
        this.billType = "3";
        this.customerNo = customerNo;
        return this;
    }

    @Override
    public String getURL() {
        return url;
    }


    @Override
    public Bill setCountVat(boolean countVat) {
        isVatCount = countVat;
        return this;
    }


    @Override
    public Bill setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src) {
        worker_info = WorkerInfo.builder()
                .userID(userID)
                .username(workerName)
                .departmentname(departmentName)
                .paymenttype(paymentType)
                .sourcename(src)
                .build();
        return this;
    }

    @Override
    public Bill setWorkerInfo(WorkerInfo workerInfo) {
        worker_info = workerInfo;
        return this;
    }


    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    @Override
    public Bill setReturnBillId(String billId) {
        return this;
    }


}

