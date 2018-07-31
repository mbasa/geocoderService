package org.georepublic.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeName;

@XmlRootElement(name = "result")

@JsonTypeInfo(include=As.WRAPPER_OBJECT, use=Id.NAME)
@JsonRootName(value = "result")
@JsonTypeName(value = "result")

@JsonPropertyOrder({"code","todofuken","shikuchoson",
    "ooaza","chiban","go","address","coodinates","details"})

public class GeocoderResultBean {
    private int code;
    private String address       = "";
    private String todofuken     = "";
    private String shikuchoson   = "";
    private String ooaza         = "";
    private String chiban        = "";
    private String go            = "";
    private Coordinates coordinates = new Coordinates();
    @JsonRawValue
    private String details = "";
    @JsonIgnore 
    private double x = -9999.999;
    @JsonIgnore
    private double y = -9999.999;
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTodofuken() {
        return todofuken;
    }
    public void setTodofuken(String todofuken) {
        this.todofuken = todofuken;
    }
    public String getShikuchoson() {
        return shikuchoson;
    }
    public void setShikuchoson(String shikuchoson) {
        this.shikuchoson = shikuchoson;
    }
    public String getOoaza() {
        return ooaza;
    }
    public void setOoaza(String ooaza) {
        this.ooaza = ooaza;
    }
    public String getChiban() {
        return chiban;
    }
    public void setChiban(String chiban) {
        this.chiban = chiban;
    }
    public String getGo() {
        return go;
    }
    public void setGo(String go) {
        this.go = go;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        coordinates.setX(x);
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        coordinates.setY(y);
        this.y = y;
    }
    /**
     * @return coordinates を取得する
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * @param coordinates coordinates を設定する
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    /**
     * @return details を取得する
     */
    public String getDetails() {
        return details;
    }
    /**
     * @param details details を設定する
     */
    public void setDetails(String details) {
        this.details = details;
    }
        
}

class Coordinates {
    private double x = -99999.99999;
    private double y = -99999.99999;
    /**
     * @return x を取得する
     */
    public double getX() {
        return x;
    }
    /**
     * @param x x を設定する
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * @return y を取得する
     */
    public double getY() {
        return y;
    }
    /**
     * @param y y を設定する
     */
    public void setY(double y) {
        this.y = y;
    }
}
