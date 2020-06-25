package com.github.uugan.ebarimt.vat;

import lombok.Data;
import lombok.ToString;

///
/// Бэлэн бус гүйлгээний мэдээлэл
///
@Data
@ToString
public class BillBankTransaction
{
    /// 
    /// Бэлэн бус гүйлгээний баримтын дугаар
    /// 
    private String rrn;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны код
    /// 
    private String bankId;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны нэр
    /// 
    private String bankName;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны терминалийн дугаар
    /// 
    private String terminalId;
    /// 
    /// Бэлэн бус гүйлгээний лавлах зөвшөөрлийн код
    /// 
    private String approvalCode;
    /// 
    /// Бэлэн бус гүйлгээний хөлийн дүн
    /// 
    private String amount;
    /// 
    /// Банкны ПОС терминалыг эзэмшигч банкны ID
    /// 
    private String acquiringBankId;
}

