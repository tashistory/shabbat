package uz.shabbat.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
/**
 @author Jebrak Semyon
 @version 1.0
 */
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

    @XmlAttribute
    private String nameWebsite;
    @XmlAttribute
    private String tgToken;
    @XmlAttribute
    private String  chatId;
    @XmlAttribute
    private String driverPath;
    private String geoID[];

    public Config() {
    }

    public Config(String nameWebsite, String tgToken, String chatId, String driverPath, String... geoID) {
        this.nameWebsite = nameWebsite;
        this.tgToken = tgToken;
        this.chatId = chatId;
        this.driverPath = driverPath;
        this.geoID = geoID;
    }

    public String getNameWebsite() {
        return nameWebsite;
    }

    public String getTgToken() {
        return tgToken;
    }

    public String getChatId() {
        return chatId;
    }

    public String[] getGeoID() {
        return geoID;
    }

    public String getDriverPath() {
        return driverPath;
    }

    @Override
    public String toString() {
        return "Config{" +
                "nameWebsite='" + nameWebsite + '\'' +
                ", tgToken='" + tgToken + '\'' +
                ", chatId='" + chatId + '\'' +
                ", driverPath='" + driverPath + '\'' +
                ", geoID=" + Arrays.toString(geoID) +
                '}';
    }
}


