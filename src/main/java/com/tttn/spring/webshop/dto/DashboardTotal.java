package com.tttn.spring.webshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DashboardTotal {

    private String date;

    private Integer total;

    @JsonProperty("ngay")
    public String getDate() {
        return date;
    }

    public DashboardTotal setDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("dashboardTotal")
    public Integer getTotal() {
        return total;
    }

    public DashboardTotal setTotal(Integer total) {
        this.total = total;
        return this;
    }
}
