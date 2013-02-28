package org.georepublic.db;

import java.sql.*;
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
	    Connection conn = null;
	    
	    GeocoderResultBean gcb = new GeocoderResultBean();
                      
        try {
            if( this.dbConn != null ) {
                conn = this.dbConn.getConnection();
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
                    gcb.setnAddress( rs.getString("address") );
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
	

}
