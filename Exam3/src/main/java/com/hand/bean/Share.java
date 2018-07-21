package com.hand.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 股票实体类
 */
@XmlRootElement
@XmlSeeAlso({XmlRoot.class})
public class Share {

    private String name;

    private double open;

    private double close;

    private double current;

    private double high;

    private double low;

    public Share() {
    }

    public Share(String name, double open, double close, double current, double high, double low) {
        this.name = name;
        this.open = open;
        this.close = close;
        this.current = current;
        this.high = high;
        this.low = low;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    @XmlElement
    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    @XmlElement
    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    @XmlElement
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @XmlElement
    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }
}
