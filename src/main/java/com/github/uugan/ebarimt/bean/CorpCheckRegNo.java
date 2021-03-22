package com.github.uugan.ebarimt.bean;

import lombok.Data;

@Data
public class CorpCheckRegNo {
    /// 
    /// Байгууллагын регистер шалгах
    /// 
    private String citypayer;

    private String vatpayer;

    private String name;

    private String found;

    private String vatpayerRegisteredDate;

    private String receiptFound;

    private String lastReceiptDate;

}
