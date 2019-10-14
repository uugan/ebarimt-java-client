package mn.ebarimt.client.vat;

import com.google.gson.Gson;

import java.util.List;

/**********RETURN BILL***************/
/// 
/// API - с буцаах сугалааны дугаар, баримтын дугаар, алдааны мессеж г.м
/// 
public class Result
{
    /// 
    /// Баримтын дугаар
    /// 
    public String billId;
    /// 
    ///  Баримтын НӨАТ агуулаагүй хөлийн дүн
    /// 
    public String amount;
    /// 
    /// Бэлэн төлбөрийн дүн
    /// 
    public String cashAmount;
    /// 
    /// Бэлэн бус төлбөрийн дүн
    /// 
    public String nonCashAmount;
    /// 
    /// Баримтын НӨАТ дүн   
    /// 
    public String vat;
    /// 
    /// Баримт хэвлэсэн огноо
    /// 
    public String date;
    /// 
    /// TicketId, lottery, merchantId, хөлийн дүн талбаруудыг агуулсан дотоод кодчилол
    /// 
    public String internalCode;
    /// 
    /// Сугалааны дугаар
    /// 
    public String lottery;
    /// 
    /// НӨАТУС-системд үүсгэсэн Merchant-ийн дугаар
    /// 
    public String merchantId;
    /// 
    /// Баримтан дээр хэвлэгдэх QR code
    /// 
    public String qrData;
    /// 
    /// Нийслэл хотын албан татвар
    /// 
    public String cityTax;
    /// 
    /// Бэлэн бус гүйлгээний мэдээлэл
    /// 
    public List<BillBankTransaction> bankTransactions;
    /// 
    /// Авсан бараа үйлчилгээ
    /// 
    public List<BillDetail> stocks;
    /// 
    /// Алдааны код
    /// 
    public String errorCode;
    /// 
    /// Алдааны мессеж
    /// 
    public String message;
    /// 
    /// Амжилттай татсан буюу Амжилттай буцаасан
    /// 
    public String success;
    /// 
    /// Алдаа болон анхрааруулгын мессеж
    /// 
    public String warningMsg;
    /// 
    /// Сугалаа өгөх боломжгүй болсон тухайн тайлбар анхааруулга мессеж
    /// 
    public String lotteryWarningMsg;

    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
