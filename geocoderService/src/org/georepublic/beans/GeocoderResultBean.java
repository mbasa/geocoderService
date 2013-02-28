package org.georepublic.beans;

public class GeocoderResultBean {
    private int code;
    private String nAddress    = "";
    private String todofuken   = "";
    private String shikuchoson = "";
    private String ooaza       = "";
    private String chiban      = "";
    private String go          = "";
    private double x           = -9999.999;
    private double y           = -9999.999;
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getnAddress() {
        return nAddress;
    }
    public void setnAddress(String nAddress) {
        this.nAddress = nAddress;
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
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    
    
}
