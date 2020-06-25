package com.github.uugan.ebarimt.vat;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuperBuilder
public class ReturnBill extends Bill {
    /// 
    /// Буцаалтын гүйлгээний дугаар
    ///
    @Builder.Default
    public String returnBillId = "";
    @Builder.Default
    public String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    ///
    /// Ажилтны мэдээлэл
    /// 
    public WorkerInfo worker_info;
    private transient String url;

    public String get_url() {
        return url;
    }


    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total) {
        return this;
    }

    @Override
    public Bill setReturnBillId(String billId) {
        returnBillId = billId;
        return this;
    }

    @Override
    public Bill setCountVat(boolean countVat) {
        return this;
    }

    @Override
    public Bill setCorporate(String customerNo) {
        return this;
    }

    @Override
    public String getURL() {
        return url;
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
}