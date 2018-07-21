package com.hand.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class XmlRoot {

    private Share share;

    public XmlRoot() {
    }

    public XmlRoot(Share share) {
        this.share = share;
    }

    @XmlElement(name="stock")
    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }
}
