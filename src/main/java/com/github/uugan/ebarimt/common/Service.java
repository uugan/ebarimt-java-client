package com.github.uugan.ebarimt.common;

public final class Service {
    private String put_url;
    private String return_url;
    private String company;
    private String register;

    public String getPut_url() {
        return put_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public String getCompany() {
        return company;
    }

    public String getRegister() {
        return register;
    }

    public void setPut_url(String put_url) {
        this.put_url = put_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "Service{" +
                "put_url='" + put_url + '\'' +
                ", return_url='" + return_url + '\'' +
                ", company='" + company + '\'' +
                ", register='" + register + '\'' +
                '}';
    }
}
