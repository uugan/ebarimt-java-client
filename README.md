[![Build Status](https://travis-ci.org/uugan/ebarimt-java-client.svg?branch=master)](https://travis-ci.org/uugan/ebarimt-java-client)

# ebarimt-java-client
A java client library for getting/returning ebarimt's VAT bill. 

(only for cash payment)

## Config file
Config file contains multiple companies configuration.
By default library will seek config.yml file from VM arguments. 
Example: 
    #java -jar prod.jar -Dconf=userconfig.yml

```yaml
service:
  -
    put_url: "http://192.168.1.2:8080/put"
    return_url: "http://192.168.1.2:8080/return"
    company: "company1"
    register: "000001"
  -
    put_url: "http://192.168.1.2:8081/put"
    return_url: "http://192.168.1.2:8081/return"
    company: "company2"
    register: "000002"
```
## Download
### Maven
Already placed in github packages.
Maven settings looks like below: 
```xml
 <servers>
        <server>
            <id>github</id>
            <username>yourname</username>
            <password>your token</password>
        </server>
 </servers>
```
```xml
<repositories>
   <repository>
        <id>github</id>
        <name>GitHub OWNER Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/uugan/ebarimt-java-client</url>
   </repository>
</repositories>
```
In pom.xml under dependencies add: 
```xml
<dependency>
  <groupId>com.github.uugan.ebarimt</groupId>
  <artifactId>ebarimt-java-client</artifactId>
  <version>1.1</version>
</dependency>
```
## Example
### Get vat example
```java
   final Ebarimt ebarimt = Ebarimt.create();
   Service svc = ebarimt.initVAT("company1");
   BillRequest bd = new BillRequestData()
                    .addStock(BunaCode.unit.getBunaCode(), "barcode", "ItemName", 2.0, 1.0, 2.0) //buna code, barcode, itemname, qty, unitprice, total
                    //.setCorporate("{corporate registerno}")
                    .setWorkerInfo("staffName", "departmentName", "002", "Payment", "app");

   System.out.println(bd.toJsonStr());
   BillResponseData br = VatUtil.putVat(svc, bd, BillResponseData.class, 20000);

```
```java
public abstract Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total);

public abstract Bill setWorkerInfo(String userName, String departmentName, String msisdn, String paymentType, String src);

```

- BunaCode contains example product codes.
- *addstock* can be multiple times.
- if you need to give vat to company then call *setCorporate* with regno.
- if you need to give bill without VAT then call *setCountVat(false)* 
- Result is a deserialized json result, which contains vat information
### Return vat example
```java
   final Ebarimt ebarimt = Ebarimt.create();
   Service svc = ebarimt.initVAT("company2");
   ReturnRequest rb = new ReturnRequestData()
                    .setWorkerInfo("Worker1", "SalesTeam", "001", "PaymentReturn", "erp")
                    .setReturnBillId("00000xxxxxxxxxxxxxxxxxxxxxxxxxxx");
   ReturnResponseData resp = VatUtil.returnVat(svc, rb, ReturnResponseData.class, 20000);
```
### Grouped vat example
```java
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
        } catch (Exception e) {
            e.printStackTrace();
        }
```
## Requirements
* Java 8+
* slf4j
* Gson
* Junit
* jackson-dataformat-yaml
* jackson-databind
* commons-lang
## Todo
* Generator invoice
