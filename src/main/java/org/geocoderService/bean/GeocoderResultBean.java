/**
 * パッケージ名：org.geocoderService.bean
 * ファイル名  ：GeocoderResultBean.java
 * 
 * @author mbasa
 * @since Apr 28, 2021
 */
package org.geocoderService.bean;
/**
 * 説明：
 *
 */

public class GeocoderResultBean {

    private int code;
    private String address       = "";
    private String todofuken     = "";
    private String shikuchoson   = "";
    private String ooaza         = "";
    private String chiban        = "";
    private String go            = "";

    private CoordinatesBean coordinates = new CoordinatesBean();
    
    /**
     * コンストラクタ
     *
     */
    public GeocoderResultBean() {
    }

    /**
     * @return code を取得する
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code code を設定する
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return address を取得する
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address address を設定する
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return todofuken を取得する
     */
    public String getTodofuken() {
        return todofuken;
    }

    /**
     * @param todofuken todofuken を設定する
     */
    public void setTodofuken(String todofuken) {
        this.todofuken = todofuken;
    }

    /**
     * @return shikuchoson を取得する
     */
    public String getShikuchoson() {
        return shikuchoson;
    }

    /**
     * @param shikuchoson shikuchoson を設定する
     */
    public void setShikuchoson(String shikuchoson) {
        this.shikuchoson = shikuchoson;
    }

    /**
     * @return ooaza を取得する
     */
    public String getOoaza() {
        return ooaza;
    }

    /**
     * @param ooaza ooaza を設定する
     */
    public void setOoaza(String ooaza) {
        this.ooaza = ooaza;
    }

    /**
     * @return chiban を取得する
     */
    public String getChiban() {
        return chiban;
    }

    /**
     * @param chiban chiban を設定する
     */
    public void setChiban(String chiban) {
        this.chiban = chiban;
    }

    /**
     * @return go を取得する
     */
    public String getGo() {
        return go;
    }

    /**
     * @param go go を設定する
     */
    public void setGo(String go) {
        this.go = go;
    }

    /**
     * @return coordinates を取得する
     */
    public CoordinatesBean getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates coordinates を設定する
     */
    public void setCoordinates(CoordinatesBean coordinates) {
        this.coordinates = coordinates;
    }
    
    public void setX( double x ) {
        this.coordinates.setX(x);
    }
    
    public void setY( double y ) {
        this.coordinates.setY(y);
    }
    /*
    public double getX() {
        return this.coordinates.getX();
    }
    
    public double getY() {
        return this.coordinates.getY();
    }
    */

}
