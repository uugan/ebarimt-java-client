package com.github.uugan.ebarimt.vat;

/// 
/// Бэлэн бус гүйлгээний мэдээлэл
/// 
public class BillBankTransaction
{
    /// 
    /// Бэлэн бус гүйлгээний баримтын дугаар
    /// 
    public String rrn;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны код
    /// 
    public String bankId;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны нэр
    /// 
    public String bankName;
    /// 
    /// Бэлэн бус гүйлгээ хийсэн банкны терминалийн дугаар
    /// 
    public String terminalId;
    /// 
    /// Бэлэн бус гүйлгээний лавлах зөвшөөрлийн код
    /// 
    public String approvalCode;
    /// 
    /// Бэлэн бус гүйлгээний хөлийн дүн
    /// 
    public String amount;
    /// 
    /// Банкны ПОС терминалыг эзэмшигч банкны ID
    /// 
    public String acquiringBankId;
}

