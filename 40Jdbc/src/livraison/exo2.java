package livraison;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class exo2 {

	public static void main(String[] args) {
        Connection conn = null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int nb;
		
		/**
		 *  connection
		 */
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","livraison","livraison");
        	conn.setAutoCommit(false);
        	
            /**
             * select
             */
        	ps=conn.prepareStatement("select * from client where noclient>40");
            //ps.setInt(1, id);
            rs=ps.executeQuery();
            
            while(rs.next()) {
            	System.out.println(rs.getLong("noclient")+"\t"+ 
            						rs.getString("nomclient")+"\t"+
            						rs.getString("notelephone")
            			);
            }        	
        	
            conn.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
	}

}
