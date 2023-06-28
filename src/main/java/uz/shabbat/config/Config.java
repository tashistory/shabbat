package uz.shabbat.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

    @XmlAttribute
    private String nameWebsite;
    @XmlAttribute
    private String tgToken;
    @XmlAttribute
    private String  chatId;
    private String geoID[];

    public Config() {
    }

    public Config(String nameWebsite, String tgToken, String chatId, String... geoID) {
        this.nameWebsite = nameWebsite;
        this.tgToken = tgToken;
        this.chatId = chatId;
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

    @Override
    public String toString() {
        return "Config{" +
                "nameWebsite='" + nameWebsite + '\'' +
                ", tgToken='" + tgToken + '\'' +
                ", chatId='" + chatId + '\'' +
                ", geoID=" + Arrays.toString(geoID) +
                '}';
    }

    public static void main(String[] args) {

    }
}


