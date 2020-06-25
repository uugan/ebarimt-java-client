package com.github.uugan.ebarimt;

import com.github.uugan.ebarimt.common.BunaCode;
import com.github.uugan.ebarimt.common.RequestType;
import com.github.uugan.ebarimt.vat.Bill;
import com.github.uugan.ebarimt.vat.BillData;
import com.github.uugan.ebarimt.vat.CorpCheckRegNo;
import com.github.uugan.ebarimt.vat.ReturnBill;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EbarimtTest {
    final Ebarimt ebarimt = Ebarimt.create();  // java ****.jar -Dconfig=config.yml

    @Test
    public void checkInit() {
        try {
            Bill bd = ebarimt.initVAT("company1", RequestType.PUT)
                    .addStock(BunaCode.unit.getBunaCode(), "", "Baraa1", 1.0, 1.0, 1.0)
                    .setWorkerInfo("staffName", "departmentName", "002", "Payment", "app");
            System.out.println(bd.toJsonStr());
            Bill rb = ebarimt.initVAT("company2", RequestType.RETURN)
                    .setWorkerInfo("test", "test", "001", "test", "erp")
                    .setReturnBillId("00000xxxxxxxxxxxxxxxxxxxxxxxxxxx");

            //Result result = ebarimt.putVAT((BillData) bd);
            // System.out.println(ebarimt.returnVAT(rb));
            // System.out.println(bd.getURL());
            //System.out.println(rb.getURL());

            CorpCheckRegNo corp = ebarimt.checkRegNo("0000001");
            assertEquals("Wrong ebarimt check service", "false", corp.getCitypayer());
            assertEquals("URL is wrong", "http://192.168.1.2:8080/put", bd.getURL());
            assertEquals("URL is wrong", "http://192.168.1.2:8081/return", rb.getURL());
            Assert.assertEquals("Total is wrong", "1.00", ((BillData) bd).amount);
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