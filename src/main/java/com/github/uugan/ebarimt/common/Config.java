package com.github.uugan.ebarimt.common;

import java.util.List;

public final class Config {
    private List<Service> service;
    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }
    public Service getService(String company) {
        return service.stream().filter(el->company.equals(el.getCompany())).findAny().orElse(null);
    }

    @Override
    public String toString() {
        return "Config{" +
                "service=" + service +
                '}';
    }
}
