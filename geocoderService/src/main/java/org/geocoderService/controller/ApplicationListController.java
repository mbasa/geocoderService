/**
 * パッケージ名：org.geocoderService.controller
 * ファイル名  ：ApplicationListController.java
 * 
 * @author mbasa
 * @since May 28, 2021
 */
package org.geocoderService.controller;

import org.geocoderService.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

/**
 * 説明：
 *
 */
@RestController
@RequestMapping("/list")
public class ApplicationListController {

    /**
     * コンストラクタ
     *
     */
    public ApplicationListController() {
    }

    @Autowired
    CustomRepository custRepository;
    
    @GetMapping(value="/",produces = "application/json;charset=UTF-8")
    public String listTodofuken() {
        
        StringBuffer sb = new StringBuffer();
        sb.append("{\"result\":");
        sb.append((String) custRepository.listTodofuken());
        sb.append("}");
        
        return sb.toString();        
    }
    
    @GetMapping(value="/{todofuken}",produces = "application/json;charset=UTF-8")
    public String listShikuchoson(@PathVariable @ApiParam(
            required=true,value="都道府県")String todofuken) {
        
        StringBuffer sb = new StringBuffer();
        String ret = (String) custRepository.listShikuchoson(todofuken);
        
        sb.append("{\"result\":");
        sb.append(ret != null ? ret : "[]");
        sb.append("}");
        
        return sb.toString();        
    }
    
    @GetMapping(value="/{todofuken}/{shikuchoson}",
            produces = "application/json;charset=UTF-8")
    public String listOoaza(
            @PathVariable @ApiParam(required=true,value="都道府県")String todofuken,
            @PathVariable @ApiParam(required=true,value="市区町村")String shikuchoson) {
        
        StringBuffer sb = new StringBuffer();
        String ret = (String) custRepository.listOoaza(todofuken,shikuchoson);
        
        sb.append("{\"result\":");
        sb.append( ret != null ? ret : "[]");
        sb.append("}");
        
        return sb.toString();        
    }
    
    @GetMapping(value="/{todofuken}/{shikuchoson}/{ooaza}",
            produces = "application/json;charset=UTF-8")
    public String listBanchi(
            @PathVariable @ApiParam(required=true,value="都道府県")String todofuken,
            @PathVariable @ApiParam(required=true,value="市区町村")String shikuchoson,
            @PathVariable @ApiParam(required=true,value="大字名")String ooaza) {
        
        StringBuffer sb = new StringBuffer();
        String ret = (String) custRepository.listBanchi(todofuken,
                shikuchoson,ooaza);
        
        sb.append("{\"result\":");
        sb.append(ret != null ? ret : "[]");
        sb.append("}");
        
        return sb.toString();        
    }
}
