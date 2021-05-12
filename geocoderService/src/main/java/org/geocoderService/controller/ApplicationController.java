/**
 * パッケージ名：org.geocoderService.controller
 * ファイル名  ：ApplicationController.java
 * 
 * @author mbasa
 * @since Apr 28, 2021
 */
package org.geocoderService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geocoderService.bean.GeocoderResultBean;
import org.geocoderService.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

/**
 * 説明：
 *
 */

@RestController
@RequestMapping("/service")
public class ApplicationController {

    /**
     * コンストラクタ
     *
     */
    public ApplicationController() {
    }
    
    @Autowired
    CustomRepository custRepository;


    @GetMapping(value="/geocode/geojson/{address}",
            produces = "application/json;charset=UTF-8")
    public String geocodeGeoJson(@PathVariable
            @ApiParam(required=true,value="Address to be Geocoded") String address) {
        
        GeocoderResultBean gcb = custRepository.geocodeAddress(address);
        
        StringBuffer jaddr = new StringBuffer();
        
        if( gcb != null ) {
            jaddr.append("{\"type\":\"Feature\",");
            jaddr.append("\"properties\":{");
            jaddr.append("\"code\":").append(gcb.getCode()).append(",");            
            jaddr.append("\"address\":\"").
                append(gcb.getAddress()).append("\",");
            jaddr.append("\"todofuken\":\"").
                append(gcb.getTodofuken()).append("\",");
            jaddr.append("\"shikuchoson\":\"").
                append(gcb.getShikuchoson()).append("\",");
            jaddr.append("\"ooaza\":\"").
                append(gcb.getOoaza()).append("\",");
            jaddr.append("\"chiban\":\"").
                append(gcb.getChiban()).append("\",");
            jaddr.append("\"go\":\"").
                append(gcb.getGo()).append("\"},");
            jaddr.append("\"geometry\":{");
            jaddr.append("\"type\":\"Point\",");
            jaddr.append("\"coordinates\": [");
            jaddr.append(gcb.getCoordinates().getX()).append(",");
            jaddr.append(gcb.getCoordinates().getY()).append("] }");
            jaddr.append("}");
        }
        else {
            jaddr.append("{\"type\":,\"properties\":{},\"geometry\":{} }");
        }
        
        return jaddr.toString();
        
    }
    
    @GetMapping(value="/geocode/json/{address}",
            produces = "application/json;charset=UTF-8")
    public Map<String,Object> geocodeJson(@PathVariable
            @ApiParam(required=true,value="Address to be Geocoded") String address) {
        
        GeocoderResultBean grb = new GeocoderResultBean();
        grb = custRepository.geocodeAddress(address);
        
        Map<String,Object> obj = new HashMap<String,Object>();
        
        obj.put("result", grb);
        
        return obj;
        
    }

    @GetMapping(value="/reversegeocode/json/{lon},{lat}",
            produces = "application/json;charset=UTF-8")
    public Map<String,Object> revGeocodeJson(
            @PathVariable @ApiParam(required=true,value="Longitude") double lon,
            @PathVariable @ApiParam(required=true,value="Latitude") double lat) {
        
        GeocoderResultBean grb = new GeocoderResultBean();
        grb = custRepository.reverseGeocode(lon, lat, 50, true, 
                false, null, null);
        
        Map<String,Object> obj = new HashMap<String,Object>();
        
        obj.put("result", grb);
        
        return obj;
        
    }
    
    @GetMapping(value="/reversegeocodeDist/json/{lon},{lat},{dist}",
            produces = "application/json;charset=UTF-8")
    public Map<String,Object> revGeocodeJsonDist(
            @PathVariable @ApiParam(required=true,value="Longitude") double lon,
            @PathVariable @ApiParam(required=true,value="Latitude") double lat,
            @PathVariable @ApiParam(required=true,value="Distance(m)") double dist,
            @RequestParam(required=false,defaultValue="true" ) @ApiParam(required=false,example="true" ) boolean useaddr,
            @RequestParam(required=false,defaultValue="false") @ApiParam(required=false,example="false") boolean details,
            @RequestParam(required=false) @ApiParam(required=false ) String category,
            @RequestParam(required=false) @ApiParam(required=false ) String owner
            ) {
        
        GeocoderResultBean grb = new GeocoderResultBean();
        grb = custRepository.reverseGeocode(lon, lat, dist, useaddr, 
                details, category, owner);
        
        Map<String,Object> obj = new HashMap<String,Object>();
        
        obj.put("result", grb);
        
        return obj;
        
    }
    
    @GetMapping(value="/reversegeocodeDist/json_arr/{lon},{lat},{dist}",
            produces = "application/json;charset=UTF-8")
    public Map<String,Object> revGeocodeJsonDistArr(
            @PathVariable @ApiParam(required=true,value="Longitude") double lon,
            @PathVariable @ApiParam(required=true,value="Latitude") double lat,
            @PathVariable @ApiParam(required=true,value="Distance(m)") double dist,
            @RequestParam(required=false,defaultValue="true" ) @ApiParam(required=false,example="true" ) boolean useaddr,
            @RequestParam(required=false,defaultValue="false") @ApiParam(required=false,example="false") boolean details,
            @RequestParam(required=false) @ApiParam(required=false ) String category,
            @RequestParam(required=false) @ApiParam(required=false ) String owner,
            @RequestParam(required=false,defaultValue = "5") @ApiParam(required=false ) int limit
            ) {
        
        List<GeocoderResultBean> grb = custRepository.reverseGeocodeArr(
                lon, lat, dist, useaddr, 
                details, category, owner,limit);
        
        Map<String,Object> obj = new HashMap<String,Object>();
        
        obj.put("result", grb);
        
        return obj;
        
    }
}
