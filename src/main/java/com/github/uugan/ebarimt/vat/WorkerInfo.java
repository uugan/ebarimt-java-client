package com.github.uugan.ebarimt.vat;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class WorkerInfo {
    /// 
    /// userID Эсвэл утасны дугаар
    /// 
    private String userID;
    /// 
    /// Ажилтны алба, хэлтэс
    /// 
    private String departmentname;
    /// 
    /// Ажилтны нэр
    /// 
    private String username;
    /// 
    /// Төлбөрийн төрөл
    /// 
    private String paymenttype;
    /// 
    /// Web, CRM, App etc
    /// 
    private String sourcename;

}