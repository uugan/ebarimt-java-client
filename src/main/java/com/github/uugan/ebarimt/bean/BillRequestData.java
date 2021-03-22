package com.github.uugan.ebarimt.bean;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

///
/// Баримтын мэдээлэл
///
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class BillRequestData extends BillRequest {

    private final transient DecimalFormat df = new DecimalFormat("#0.00");
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
    public String districtCode; //SBD by default
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

    //
    //
    //
    public String internalId;
    //
    //
    //
    public String registerNo;
    public Boolean group;
    public List<BillRequestData> bills;
    //for parsing
    public String taxType;
    public String billId;
    public String macAddress;
    public String date;
    public String lottery;
    public String qrData;

    private transient boolean isVatCount;


    public BillRequestData() {
        stocks = new ArrayList<>();
        bankTransactions = new ArrayList<>();
        bills = new ArrayList<>();
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

    @Override
    public BillRequest addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total) {

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
    @Override
    public BillRequest addStock(BillDetail stock) {
        stocks.add(stock);
        recalcAmount(Double.parseDouble(stock.getTotalAmount()));
        return this;
    }

    @Override
    public BillRequest addBill(BillRequestData billData) {
        this.bills.add(billData);
        recalcAmount(Double.parseDouble(billData.amount));
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
    public BillRequest setCorporate(String customerNo) {
        this.billType = "3";
        this.customerNo = customerNo;
        return this;
    }

    @Override
    public BillRequest setCountVat(boolean countVat) {
        isVatCount = countVat;
        return this;
    }


    @Override
    public BillRequest setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src) {
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
    public BillRequest setWorkerInfo(WorkerInfo workerInfo) {
        worker_info = workerInfo;
        return this;
    }

    @Override
    public BillRequest setGroup(boolean group) {
        this.group = group;
        return this;
    }


    @Override
    public BillRequest setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
        return this;
    }

    @Override
    public BillRequest setInternalId(String internalId) {
        this.internalId = internalId;
        return this;
    }

    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private void recalcAmount(Double totalAmount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        Double total = Double.parseDouble(amount);
        total += totalAmount;
        amount = df.format(total);
        if (isVatCount) {
            Double tvat = total - total / 1.1;
            vat = df.format(tvat);
        } else {
            vat = "0.00";
        }
        cashAmount = amount;
    }
}

