package com.github.uugan.ebarimt.vat;

/// 
/// Худалдан авсан бараа үйлчилгээ
/// 
public class BillDetail
{
    /// 
    /// Авсан бараа үйлчилгээний код
    /// 
    public String code;
    /// 
    /// Авсан бараа үйлчилгээний дугаар
    /// 
    public String name;
    /// 
    /// Авсан бараа үйлчилгээний хэмжих нэгж
    /// 
    public String measureUnit;
    /// 
    /// Авсан бараа үйлчилгээний тоон хэмжээ
    /// 
    public String qty;
    /// 
    /// Авсан бараа үйлчилгээний нэгж үнэ
    /// 
    public String unitPrice;
    /// 
    /// Авсан бараа үйлчилгээний нийт үнэ
    /// 
    public String totalAmount;
    /// 
    /// Авсан бараа үйлчилгээний НӨАТ
    /// 
    public String vat;
    /// 
    /// Барааны 1D баркод
    /// 
    public String barCode;
    /// 
    /// Нийслэл хотын албан татвар
    /// 
    public String cityTax;
}
