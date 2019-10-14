package mn.ebarimt.client.vat;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReturnBill extends Bill {
    /// 
    /// Буцаалтын гүйлгээний дугаар
    /// 
    public String returnBillId;

    public String date;
    ///
    /// Ажилтны мэдээлэл
    /// 
    public WorkerInfo worker_info;
    private transient String _url;
    public ReturnBill() {
        returnBillId = "";
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        ;
    }
    public String get_url() {
        return _url;
    }

    public Bill set_url(String _url) {
        this._url = _url;
        return this;
    }

    @Override
    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total) {
        return null;
    }
    @Override
    public Bill setReturnBillId(String billId) {
        returnBillId = billId;
        return this;
    }

    @Override
    public Bill setCountVat(boolean countVat) {
        return null;
    }

    @Override
    public Bill setCorporate(String customerNo) {
        return null;
    }

    @Override
    public String getURL() {
        return _url;
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
}