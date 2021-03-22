package com.github.uugan.ebarimt;

import com.github.uugan.ebarimt.common.BunaCode;
import com.github.uugan.ebarimt.common.RequestType;
import com.github.uugan.ebarimt.bean.*;
import com.github.uugan.ebarimt.common.Service;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EbarimtTest {
    final Ebarimt ebarimt = Ebarimt.create();  // java ****.jar -Dconfig=config.yml

    @Test
    public void checkInit() {
        try {
            Service svc = ebarimt.initVAT("company1");
            BillRequest bd = new BillRequestData()
                    .addStock(BunaCode.unit.getBunaCode(), "", "Baraa1", 1.0, 1.0, 1.0)
                    .setWorkerInfo("staffName", "departmentName", "002", "Payment", "app");

            CorpCheckRegNo corp = VatUtil.checkRegNo("0000001");
            assertEquals("Wrong ebarimt check service", "false", corp.getCitypayer());
            assertEquals("URL is wrong", "http://192.168.1.2:8080/put", svc.getPut_url());

            Assert.assertEquals("Total is wrong", "1.00", ((BillRequestData) bd).amount);
            assertEquals("Total stock size is wrong", 1, ((BillRequestData) bd).stocks.size());

            //   System.out.println(bd.toJsonStr());
            //   BillResponseData br = VatUtil.putVat(svc, bd, BillResponseData.class, 20000);
            //   System.out.println(br.toJsonStr());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkReturn() {
        try {

            Service svc = ebarimt.initVAT("company2");
            ReturnRequest rb = new ReturnRequestData()
                    .setWorkerInfo("Worker1", "SalesTeam", "001", "PaymentReturn", "erp")
                    .setReturnBillId("00000xxxxxxxxxxxxxxxxxxxxxxxxxxx");
            //   ReturnResponseData resp = VatUtil.returnVat(svc, rb, ReturnResponseData.class, 20000);
            assertEquals("URL is wrong", "http://192.168.1.2:8081/return", svc.getReturn_url());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkCorporate() {
        try {
            Service svc = ebarimt.initVAT("company1");
            BillRequest bd = new BillRequestData()
                    .addStock(BunaCode.unit.getBunaCode(), "", "Baraa1", 1.0, 1.0, 1.0)
                    .setWorkerInfo("test", "test", "userId", "Invoice payment", "web")
                    .setCorporate("123467");
            // System.out.println(bd.getURL());
            // System.out.println(bd.toJsonStr());
            assertEquals("BillType is wrong", "3", ((BillRequestData) bd).billType);
            assertEquals("CustomerNo is wrong", "123467", ((BillRequestData) bd).customerNo);
            assertEquals("URL is wrong", "http://192.168.1.2:8080/put", svc.getPut_url());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void checkGroupedVat() {
        //First company
        BillRequestData bd1 = new BillRequestData();
        bd1.addStock(BunaCode.unit.getBunaCode(), "", "Бараа1", 1.0, 5.0, 5.0);
        bd1.setRegisterNo("registerNo1");
        bd1.setInternalId("1");
        //Second company
        BillRequestData bd2 = new BillRequestData();
        bd2.addStock(BunaCode.unit.getBunaCode(), "", "Бараа1", 1.0, 4.0, 4.0);
        bd2.setRegisterNo("registerNo2");
        bd2.setInternalId("2");


        try {
            Service svc = ebarimt.initVAT("company1");
            BillRequest real = new BillRequestData()
                    .setGroup(true) //bundled vat
                    .addBill(bd1)
                    .addBill(bd2)
                    .setWorkerInfo("Worker1", "SalesTeam", "001", "payment1", "system")
                    .setCorporate("1000000");
            System.out.println(real.toJsonStr());
            BillResponseData br = VatUtil.putVat(svc, real, BillResponseData.class, 20000);

            assertEquals("group is wrong", true, ((BillRequestData) real).group);
            assertEquals("bills is wrong", 2, ((BillRequestData) real).bills.size());

            //  Result result = ebarimt.putVAT(real);
            //  System.out.println(result.toJsonStr());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}