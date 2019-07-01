package com.ef.dto;

import java.util.Date;

public class IpLogDto extends BaseDto {


    private Date date;
    private String ip;
    private String request;
    private int status;
    private String agent;


    public IpLogDto(Date date, String ip, String request, int status, String agent) {
        this.date = date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.agent = agent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}

