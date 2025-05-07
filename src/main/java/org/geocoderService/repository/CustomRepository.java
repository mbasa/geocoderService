/**
 * パッケージ名：org.geocoderService.repository
 * ファイル名  ：CustomRepository.java
 * 
 * @author mbasa
 * @since Apr 30, 2021
 */
package org.geocoderService.repository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.geocoderService.bean.GeocoderResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 説明：
 *
 */
@Repository
public class CustomRepository {

    /**
     * コンストラクタ
     *
     */
    public CustomRepository() {
    }

    private final Logger logger = LoggerFactory.getLogger(CustomRepository.class);

    @Autowired 
    private EntityManager entityManager;

    public GeocoderResultBean geocodeAddress( String inAddress ) {

        try {
            Object[] result = (Object[])  
                    entityManager.createNativeQuery("select * from geocoder(?1)")
                    .setParameter(1, inAddress)
                    .getSingleResult();

            logger.debug("Result Code:" + result[0]);

            return objToGrb(result) ;
        }
        catch(Exception e) {
            ;
        }

        return new GeocoderResultBean();
    }


    public GeocoderResultBean reverseGeocode( double mLon, 
            double mLat, double mDist) {

        String sql = "select * from reverse_geocoder("
                + "cast(?1 as numeric),cast(?2 as numeric),cast(?3 as numeric))";

        try {

            Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                    .setParameter(1, mLon)
                    .setParameter(2, mLat)
                    .setParameter(3, mDist)
                    .getSingleResult();

            logger.debug("Result Code:" + result[0]);

            return objToGrb(result);
        } catch (Exception e) {
            ;
        }

        return new GeocoderResultBean();
    }

    public GeocoderResultBean reverseGeocode(double mLon,
            double mLat, double mDist, boolean useAddr, boolean details,
            String category, String owner ) {

        String sql = "select * from reverse_geocoder("
                + "cast(?1 as numeric),cast(?2 as numeric),cast(?3 as numeric),"
                + "cast(?4 as bool),cast(?5 as text),cast(?6 as text) )";

        if( details ) {
            sql = "select a.*,cast(details as text) details "
                    + "from reverse_geocoder("
                    + "cast(?1 as numeric),cast(?2 as numeric),cast(?3 as numeric),"
                    + "cast(?4 as bool),cast(?5 as text),cast(?6 as text)"
                    + ") a, places b "
                    + "where a.address = b.name";
        }

        try {

            Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                    .setParameter(1, mLon)
                    .setParameter(2, mLat)
                    .setParameter(3, mDist)
                    .setParameter(4, useAddr)
                    .setParameter(5, category)
                    .setParameter(6, owner)
                    .getSingleResult();            

            logger.debug("Result Code:" + result[0]);

            return objToGrb(result) ;
        } catch (Exception e) {
            ;
        }

        return new GeocoderResultBean();
    }

    public List<GeocoderResultBean> reverseGeocodeArr( double mLon, 
            double mLat, double mDist, boolean useAddr, boolean details,
            String category, String owner, int limit ) {
        
        List<GeocoderResultBean> retVal = new ArrayList<GeocoderResultBean>();
        
        String sql = "select * from reverse_geocoder("
                + "cast(?1 as numeric),cast(?2 as numeric),cast(?3 as numeric),"
                + "cast(?4 as bool),cast(?5 as text),cast(?6 as text),"
                + "cast(?7 as numeric) )";
        
        if( details ) {
            sql = "select a.*,cast(details as text) details "
                    + "from reverse_geocoder("
                    + "cast(?1 as numeric),cast(?2 as numeric),cast(?3 as numeric),"
                    + "cast(?4 as bool),cast(?5 as text),cast(?6 as text),"
                    + "cast(?7 as numeric) ) a, places b "
                    + "where a.address = b.name";
        }
        
        List<?> result = (List<?>)entityManager.createNativeQuery(sql)
                .setParameter(1, mLon)
                .setParameter(2, mLat)
                .setParameter(3, mDist)
                .setParameter(4, useAddr)
                .setParameter(5, category)
                .setParameter(6, owner)
                .setParameter(7, limit)
                .getResultList();
                
        for( int i=0; i < result.size(); i++ ) {
            GeocoderResultBean gcb = objToGrb( (Object[]) result.get(i) );
            retVal.add(gcb);
        }
        
        return retVal;
    }
    
    
    private GeocoderResultBean objToGrb(Object[] result) {

        GeocoderResultBean gcb = new GeocoderResultBean();

        if( result[0] != null )
            gcb.setCode((int)result[0]);
        if( result[1] != null )
            gcb.setX((double)result[1]);        
        if( result[2] != null )
            gcb.setY((double)result[2]);
        if( result[3] != null )
            gcb.setAddress((String)result[3]);
        if( result[4] != null )
            gcb.setTodofuken((String)result[4]);
        if( result[5] != null )
            gcb.setShikuchoson((String)result[5]);
        if( result[6] != null )
            gcb.setOoaza((String)result[6]);
        if( result[7] != null )
            gcb.setChiban((String)result[7]);
        if( result[8] != null )
            gcb.setGo((String)result[8]);
        if (result[9] != null)
            gcb.setMeshcode((String) result[9]);

        return gcb ;
    }
    
    public Object listTodofuken() {
        
        String sql = "with tt as (select code,todofuken,lat,lon from "
                + "pggeocoder.address_t order by code) "
                + "select cast(array_to_json(array_agg(tt)) as text) from tt";
        
        Object retVal = entityManager.createNativeQuery(sql).getSingleResult();
        
        return retVal;
    }
    
    public Object listShikuchoson( String todofuken ) {
        String sql = "with tt as (select code,todofuken,shikuchoson,lat,lon "
                + "from pggeocoder.address_s where todofuken = ? order by code) "
                + "select cast(array_to_json(array_agg(tt)) as text) from tt";
        
        Object retVal = entityManager.createNativeQuery(sql)
                .setParameter(1, todofuken)
                .getSingleResult();
        
        return retVal;
    }
    
    public Object listOoaza( String todofuken, String shikuchoson ) {
        String sql = "with tt as (select code,todofuken,shikuchoson,ooaza,lat,lon "
                + "from pggeocoder.address_o where todofuken = ? and shikuchoson = ? "
                + "order by code) select cast(array_to_json(array_agg(tt)) "
                + "as text) from tt;";
        
        Object retVal = entityManager.createNativeQuery(sql)
                .setParameter(1, todofuken)
                .setParameter(2, shikuchoson)
                .getSingleResult();
        
        return retVal;
    }
    
    public Object listBanchi( String todofuken, 
            String shikuchoson,String ooaza ) {
        
        String sql = "with tt as (select todofuken,shikuchoson,"
                + "ooaza,chiban as banchi, lat,lon "
                + "from pggeocoder.address_c where todofuken = ? and shikuchoson = ? "
                + "and ooaza = ? order by chiban::::numeric) "
                + "select cast(array_to_json(array_agg(tt)) "
                + "as text) from tt;";

        Object retVal = entityManager.createNativeQuery(sql)
                .setParameter(1, todofuken)
                .setParameter(2, shikuchoson)
                .setParameter(3, ooaza)
                .getSingleResult();

        return retVal;
    }

    public Object listGo(String todofuken,
            String shikuchoson, String ooaza, String banchi) {

        String sql = "with tt as (select todofuken,shikuchoson,"
                + "ooaza,chiban as banchi, go, lat,lon "
                + "from pggeocoder.address_g where "
                + "todofuken = ? and shikuchoson = ? "
                + "and ooaza = ? and chiban = ? order by chiban::::numeric) "
                + "select cast(array_to_json(array_agg(tt)) "
                + "as text) from tt;";
        
        Object retVal = entityManager.createNativeQuery(sql)
                .setParameter(1, todofuken)
                .setParameter(2, shikuchoson)
                .setParameter(3, ooaza)
                .setParameter(4, banchi)
                .getSingleResult();
        
        return retVal;
    }
}
