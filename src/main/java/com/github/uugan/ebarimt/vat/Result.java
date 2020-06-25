package com.github.uugan.ebarimt.vat;

import com.google.gson.Gson;
import lombok.Data;

import java.util.List;

/**********RETURN BILL***************/
/// 
/// API - с буцаах сугалааны дугаар, баримтын дугаар, алдааны мессеж г.м
///
@Data
public class Result {
    /// 
    /// Баримтын дугаар
    /// 
    private String billId;
    /// 
    ///  Баримтын НӨАТ агуулаагүй хөлийн дүн
    /// 
    private String amount;
    /// 
    /// Бэлэн төлбөрийн дүн
    /// 
    private String cashAmount;
    /// 
    /// Бэлэн бус төлбөрийн дүн
    /// 
    private String nonCashAmount;
    /// 
    /// Баримтын НӨАТ дүн   
    /// 
    private String vat;
    /// 
    /// Баримт хэвлэсэн огноо
    /// 
    private String date;
    /// 
    /// TicketId, lottery, merchantId, хөлийн дүн талбаруудыг агуулсан дотоод кодчилол
    /// 
    private String internalCode;
    /// 
    /// Сугалааны дугаар
    /// 
    private String lottery;
    /// 
    /// НӨАТУС-системд үүсгэсэн Merchant-ийн дугаар
    /// 
    private String merchantId;
    /// 
    /// Баримтан дээр хэвлэгдэх QR code
    /// 
    private String qrData;
    /// 
    /// Нийслэл хотын албан татвар
    /// 
    private String cityTax;
    /// 
    /// Бэлэн бус гүйлгээний мэдээлэл
    /// 
    private List<BillBankTransaction> bankTransactions;
    /// 
    /// Авсан бараа үйлчилгээ
    /// 
    private List<BillDetail> stocks;
    /// 
    /// Алдааны код
    /// 
    private String errorCode;
    /// 
    /// Алдааны мессеж
    /// 
    public String message;
    /// 
    /// Амжилттай татсан буюу Амжилттай буцаасан
    /// 
    private String success;
    /// 
    /// Алдаа болон анхрааруулгын мессеж
    /// 
    private String warningMsg;
    /// 
    /// Сугалаа өгөх боломжгүй болсон тухайн тайлбар анхааруулга мессеж
    /// 
    private String lotteryWarningMsg;

    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
