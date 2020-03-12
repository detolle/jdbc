package livraison;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class exo4 {

	public static void main(String[] args) {
        Connection conn = null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id;
		
		/**
		 *  connection
		 */
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","livraison","livraison");
        	conn.setAutoCommit(false);
        	
            /**
             * select
             */
			id=Ui.saisieId("Entrer l'ID de l'exemplaire :");

//        	st=conn.createStatement();
//        	nb=st.executeUpdate("insert into client (noclient,nomclient,notelephone) values(101,'G Lemoyne','911')");
//          System.out.println("nombre="+nb);        	
            //rs=st.executeQuery("select * from client where noclient>"+strId);
           
        	ps=conn.prepareStatement("select * from client where noclient>?");
            ps.setInt(1, id);
            rs=ps.executeQuery();
            
            while(rs.next()) {
            	System.out.println(rs.getLong("noclient")+"\t"+ 
            						rs.getString("nomclient")+"\t"+
            						rs.getString("notelephone")
            			);
            }        	
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.rollback();
                    conn.close();
                    System.exit(0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
	}

}
