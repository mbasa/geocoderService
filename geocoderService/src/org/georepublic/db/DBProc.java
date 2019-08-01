package org.georepublic.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.georepublic.beans.*;


/**
 * @author mbasa
 *
 */
public class DBProc {

	DBConn dbConn = null;

	
	public DBProc() {		
		this.dbConn = new DBConn();
	}

	public GeocoderResultBean geocodeAddress( String inAddress ) {
	    
	    String sql      = "select * from geocoder(?)";
	    Connection conn = this.dbConn.getConnection();
	    
	    GeocoderResultBean gcb = new GeocoderResultBean();
                      
        try {
            if( conn != null ) {

                PreparedStatement stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, inAddress);
                ResultSet rs = stmt.executeQuery();
                
                if( rs.next() ) {
                    gcb.setCode( rs.getInt("code") );
                    gcb.setTodofuken( rs.getString("todofuken") );
                    gcb.setShikuchoson( rs.getString("shikuchoson") );
                    gcb.setOoaza( rs.getString("ooaza") );
                    gcb.setChiban( rs.getString("chiban") );
                    gcb.setGo( rs.getString("go") );
                    gcb.setAddress( rs.getString("address") );
                    gcb.setX( rs.getDouble("x") );
                    gcb.setY( rs.getDouble("y") );
                }
                rs.close();
                stmt.close();
            }
                
        }
        catch( Exception ex ) {
            ex.printStackTrace();
        }
        finally {
            try {
                if( conn != null )
                    conn.close();
            }
            catch( Exception e ) {;}
        }
	    return gcb;
	}
	

	public GeocoderResultBean reverseGeocode( double mLon, 
	        double mLat, double mDist, boolean useAddr, boolean details,
	        String category, String owner ) {
        
        String sql = "select * from reverse_geocoder(?::numeric,?::numeric,?::numeric,"
                + "?::boolean,?::text,?::text)";
        
        if( details ) {
            sql = "select a.*,details from reverse_geocoder(?::numeric,"
                    + "?::numeric,?::numeric,"
                    + "?::boolean,?::text,?::text) a, places b "
                    + "where a.address = b.name";
        }
        
        Connection conn = this.dbConn.getConnection();
        
        GeocoderResultBean gcb = new GeocoderResultBean();
                      
        try {
            if( conn != null ) {

                PreparedStatement stmt = conn.prepareStatement(sql);
                
                stmt.setDouble( 1, mLon  );
                stmt.setDouble( 2, mLat  );
                stmt.setDouble( 3, mDist );
                stmt.setBoolean(4, useAddr);
                stmt.setString( 5, category);
                stmt.setString( 6, owner);

                ResultSet rs = stmt.executeQuery();
                
                if( rs.next() ) {
                    gcb.setCode( rs.getInt("code") );
                    gcb.setTodofuken( rs.getString("todofuken") );
                    gcb.setShikuchoson( rs.getString("shikuchoson") );
                    gcb.setOoaza( rs.getString("ooaza") );
                    gcb.setChiban( rs.getString("chiban") );
                    gcb.setGo( rs.getString("go") );
                    gcb.setAddress( rs.getString("address") );
                    gcb.setX( rs.getDouble("x") );
                    gcb.setY( rs.getDouble("y") );
                    
                    if( details )
                        gcb.setDetails(rs.getString("details"));
                }
                rs.close();
                stmt.close();
            }
                
        }
        catch( Exception ex ) {
            ex.printStackTrace();
        }
        finally {
            try {
                if( conn != null )
                    conn.close();
            }
            catch( Exception e ) {;}
        }
        return gcb;
    }

	public List<GeocoderResultBean> reverseGeocodeArr( double mLon, 
            double mLat, double mDist, boolean useAddr, boolean details,
            String category, String owner ) {
        
        String sql = "select * from reverse_geocoder(?::numeric,?::numeric,?"
                + "::numeric,"
                + "?::boolean,?::text,?::text)";
        
        if( details ) {
            sql = "select a.*,details from reverse_geocoder(?::numeric,"
                    + "?::numeric,?::numeric,"
                    + "?::boolean,?::text,?::text) a, places b "
                    + "where a.address = b.name";
        }
        
        Connection conn = this.dbConn.getConnection();
        
        List<GeocoderResultBean> gcbArr = new ArrayList<GeocoderResultBean>();
                      
        try {
            if( conn != null ) {

                PreparedStatement stmt = conn.prepareStatement(sql);
                                
                stmt.setDouble( 1, mLon  );
                stmt.setDouble( 2, mLat  );
                stmt.setDouble( 3, mDist );
                stmt.setBoolean(4, useAddr);
                stmt.setString( 5, category);
                stmt.setString( 6, owner);
                
                System.out.print(stmt.toString());
                
                ResultSet rs = stmt.executeQuery();
                
                while( rs.next() ) {
                    GeocoderResultBean gcb = new GeocoderResultBean();
                    
                    gcb.setCode( rs.getInt("code") );
                    gcb.setTodofuken( rs.getString("todofuken") );
                    gcb.setShikuchoson( rs.getString("shikuchoson") );
                    gcb.setOoaza( rs.getString("ooaza") );
                    gcb.setChiban( rs.getString("chiban") );
                    gcb.setGo( rs.getString("go") );
                    gcb.setAddress( rs.getString("address") );
                    gcb.setX( rs.getDouble("x") );
                    gcb.setY( rs.getDouble("y") );
                    
                    if( details )
                        gcb.setDetails(rs.getString("details"));
                    
                    gcbArr.add(gcb);
                }
                rs.close();
                stmt.close();
            }
                
        }
        catch( Exception ex ) {
            ex.printStackTrace();
        }
        finally {
            try {
                if( conn != null )
                    conn.close();
            }
            catch( Exception e ) {;}
        }
        return gcbArr;
    }

}
