package com.ef.dto;

import java.util.Date;

public class BlockedIpDto extends BaseDto{

    //--startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
    String ip;
    Date startTime;
    String duration;
    int frequency;
    Date endTime;

    public BlockedIpDto(String ip, Date startTime, String duration, int frequency, Date endTime) {
        this.ip = ip;
        this.startTime = startTime;
        this.duration = duration;
        this.frequency = frequency;
        this.endTime = endTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "BlockedIpDto{" +
                "ip='" + ip + '\'' +
                ", startTime=" + startTime +
                ", duration='" + duration + '\'' +
                ", frequency=" + frequency +
                ", endTime=" + endTime +
                '}';
    }
}
