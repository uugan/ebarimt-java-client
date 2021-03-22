package com.github.uugan.ebarimt.bean;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class ReturnRequestData extends ReturnRequest {
    /// 
    /// Буцаалтын гүйлгээний дугаар
    ///
    public String returnBillId;
    public String date;
    ///
    /// Ажилтны мэдээлэл
    /// 
    public WorkerInfo worker_info;

    public ReturnRequestData() {
        returnBillId = "";
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    @Override
    public ReturnRequest setWorkerInfo(String workerName, String departmentName, String userID, String paymentType, String src) {
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
    public ReturnRequest setWorkerInfo(WorkerInfo workerInfo) {
        worker_info = workerInfo;
        return this;
    }

    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public ReturnRequest setReturnBillId(String billId) {
        returnBillId = billId;
        return this;
    }

}