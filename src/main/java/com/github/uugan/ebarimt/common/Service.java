package com.github.uugan.ebarimt.common;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public final class Service {
    private String put_url;
    private String return_url;
    private String company;
    private String register;
}
