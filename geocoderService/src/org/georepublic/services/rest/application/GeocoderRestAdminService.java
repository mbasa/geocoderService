package org.georepublic.services.rest.application;

import javax.ws.rs.*;
import org.georepublic.db.DBProc;
import org.georepublic.beans.*;


@Path("/")
public class GeocoderRestAdminService {

	@GET
	public String emptyReq() {
		return "error: empty Request";
	}

	@GET
    @Produces("application/json; charset=UTF-8")
    @Path("/reversegeocode/json/{lon},{lat}")
    public GeocoderResultBean revGeocodeJson(
            @PathParam("lon") double lon, @PathParam("lat") double lat) {
	    
	    DBProc db  = new DBProc();
	    GeocoderResultBean gcb = db.reverseGeocode(lon, lat);
	    
	    return gcb;
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/geocode/json/{address}")
	public GeocoderResultBean geocodeJson(@PathParam("address") String inAddr) {

	    DBProc db  = new DBProc();
	    GeocoderResultBean gcb = db.geocodeAddress(inAddr);
	    
	    return gcb;

	    /*
	    StringBuffer jaddr = new StringBuffer();
	    
	    if( gcb != null ) {
	        jaddr.append("{\"result\":{");
	        jaddr.append("\"code\":").append(gcb.getCode()).append(",");
	        
	        jaddr.append("\"address\":\"").
	            append(gcb.getnAddress()).append("\",");
	        jaddr.append("\"todofuken\":\"").
                append(gcb.getTodofuken()).append("\",");
	        jaddr.append("\"shikuchoson\":\"").
                append(gcb.getShikuchoson()).append("\",");
	        jaddr.append("\"ooaza\":\"").
                append(gcb.getOoaza()).append("\",");
	        jaddr.append("\"chiban\":\"").
                append(gcb.getChiban()).append("\",");
	        jaddr.append("\"go\":\"").
                append(gcb.getGo()).append("\",");
	        jaddr.append("\"coordinates\":{");
	        jaddr.append("\"x\":").append(gcb.getX()).append(",");
	        jaddr.append("\"y\":").append(gcb.getY()).append("}");
	        jaddr.append("},\"status\":\"OK\"}");
	    }
	    else {
	        jaddr.append("{\"result\":{},\"status\":\"NG\"}");
	    }
	    
	    return jaddr.toString();
	    */
	}

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/geocode/geojson/{address}")
    public String geocodeGeoJson(@PathParam("address") String inAddr) {
        
        DBProc db = new DBProc();
        GeocoderResultBean gcb = db.geocodeAddress(inAddr);

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
            jaddr.append("\"coordinates\": [").append(gcb.getX()).append(",");
            jaddr.append(gcb.getY()).append("] }");
            jaddr.append("}");
        }
        else {
            jaddr.append("{\"type\":,\"properties\":{},\"geometry\":{} }");
        }
        
        return jaddr.toString();
    }

}
