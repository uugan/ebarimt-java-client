package com.github.uugan.ebarimt.bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

///
/// Худалдан авсан бараа үйлчилгээ
///
@Data
@Builder
@ToString
public class BillDetail {
    /// 
    /// Авсан бараа үйлчилгээний код
    /// 
    private String code;
    /// 
    /// Авсан бараа үйлчилгээний дугаар
    /// 
    private String name;
    /// 
    /// Авсан бараа үйлчилгээний хэмжих нэгж
    /// 
    private String measureUnit;
    /// 
    /// Авсан бараа үйлчилгээний тоон хэмжээ
    /// 
    private String qty;
    /// 
    /// Авсан бараа үйлчилгээний нэгж үнэ
    /// 
    private String unitPrice;
    /// 
    /// Авсан бараа үйлчилгээний нийт үнэ
    /// 
    private String totalAmount;
    /// 
    /// Авсан бараа үйлчилгээний НӨАТ
    /// 
    private String vat;
    /// 
    /// Барааны 1D баркод
    /// 
    private String barCode;
    /// 
    /// Нийслэл хотын албан татвар
    /// 
    private String cityTax;
}
