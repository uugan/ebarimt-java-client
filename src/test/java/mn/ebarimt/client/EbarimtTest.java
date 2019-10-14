package mn.ebarimt.client;

import mn.ebarimt.client.common.BunaCode;
import mn.ebarimt.client.common.RequestType;
import mn.ebarimt.client.vat.Bill;
import mn.ebarimt.client.vat.BillData;
import mn.ebarimt.client.vat.ReturnBill;
import org.junit.Test;

import static org.junit.Assert.*;

public class EbarimtTest {
    final Ebarimt ebarimt = Ebarimt.create();  // java ****.jar -Dconfig=config.yml

    @Test
    public void checkInit() {
        try {
            Bill bd = ebarimt.initVAT("company1", RequestType.PUT)
                    .addStock(BunaCode.unit.getBunaCode(), "", "Negj", 1.0, 1.0, 1.0)
                    .setWorkerInfo("staffName", "departmentName", "8*****", "test", "app");

            ReturnBill rb = (ReturnBill) ebarimt.initVAT("company2", RequestType.RETURN)
                    .setWorkerInfo("test", "test", "8*****", "test", "erp")
                    .setReturnBillId("00000xxxxxxxxxxxxxxxxxxxxxxxxxxx");

            // Result result = ebarimt.putVAT((BillData) bd);
            // System.out.println(ebarimt.returnVAT(rb));
            // System.out.println(bd.getURL());
            //System.out.println(rb.getURL());
            assertEquals("URL is wrong", "http://192.168.1.2:8080/put", bd.getURL());
            assertEquals("URL is wrong", "http://192.168.1.2:8081/return", rb.getURL());
            assertEquals("Total is wrong", "1.00", ((BillData) bd).amount);
            assertEquals("Total stock size is wrong", 1, ((BillData) bd).stocks.size());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void checkCorporate() {
        try {
            Bill bd = ebarimt.initVAT("company1", RequestType.PUT)
                    .addStock(BunaCode.unit.getBunaCode(), "", "Negj", 1.0, 1.0, 1.0)
                    .setWorkerInfo("test", "test", "userId", "Invoice payment", "web")
                    .setCorporate("123467");
            // System.out.println(bd.getURL());
            // System.out.println(bd.toJsonStr());
            assertEquals("BillType is wrong", "3", ((BillData) bd).billType);
            assertEquals("BillType is wrong", "123467", ((BillData) bd).customerNo);
            assertEquals("URL is wrong", "http://192.168.1.2:8080/put", bd.getURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}