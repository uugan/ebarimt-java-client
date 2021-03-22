package com.github.uugan.ebarimt.bean;

import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnResponseData extends BillResponse {
    private Boolean success;
    private Integer errorCode;
    private String message;

    public String toJsonStr() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
