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
In maven settings.xml in profile tag add repository in repositories
```xml
     <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray</name>
        <url>https://jcenter.bintray.com</url>
    </repository>
```
also in pluginRepositories
```xml
     <pluginRepository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>central</id>
        <name>bintray-plugins</name>
        <url>https://jcenter.bintray.com</url>
    </pluginRepository>

```
```xml
<dependency>
  <groupId>com.github.uugan.ebarimt</groupId>
  <artifactId>ebarimt-java-client</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
## Example
### Get vat example
```java
   final Ebarimt ebarimt = Ebarimt.create();
   Bill bd = ebarimt.initVAT("company1", RequestType.PUT)
                    .addStock("Buna 2.0 code", "BarCode", "ItemName", 2.0, 1.0, 2.0) //buna code, barcode, itemname, qty, unitprice, total
                    .setWorkerInfo("staffName", "departmentName", "userID", "PaymentType", "source");

    Result result = ebarimt.putVAT((BillData) bd);

```
public abstract Bill addStock(String bunaCode, String barcode, String productName, Double qty, Double unitPrice, Double total);

public abstract Bill setWorkerInfo(String userName, String departmentName, String msisdn, String paymentType, String src);

- BunaCode contains example product codes.
- addstock can be multiple times.
- if you need to give vat to company then call setCorporate with regno.
- if you need to give bill without VAT then call setCountVat(false) 
- Result is a deserialized json result, which contains vat information
### Return vat example
```java
   final Ebarimt ebarimt = Ebarimt.create();
     ReturnBill rb = (ReturnBill) ebarimt.initVAT("company2", RequestType.RETURN)
                     .setWorkerInfo("staffName", "departmentName", "userID", "PaymentType", "source")
                     .setReturnBillId("00000xxxxxxxxxxxxxxxxxxxxxxxxxxx");
   String resReturn = ebarimt.returnVAT(rb);
```
## Requirements
* Java 8+
* slf4j
* Gson
* Junit
* jackson-dataformat-yaml
* jackson-databind
* commons-lang
