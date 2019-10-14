package com.github.uugan.ebarimt.common;

public enum BunaCode {
    //example bunacodes DELETE
    unit("8413100"),
    device("4722300");

    private String _bunacode;
    private BunaCode(String bunacode){
        _bunacode = bunacode;
    }

    public String getBunaCode(){
        return _bunacode;
    }



}
