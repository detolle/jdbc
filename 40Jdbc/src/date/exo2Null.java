package date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class exo2Null {

	public static void main(String[] args) {
        Connection conn = null;
		Statement st=null;
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		ResultSet rs=null;
		Long id;
		int nb;
		
		/**
		 *  connection
		 */
        try {
        	/**
        	 * property
        	 */
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","biblio","biblio");
            //conn = DriverManager.getConnection(Messages.getString("url"),Messages.getString("user"),Messages.getString("pwd"));
        	conn.setAutoCommit(false);
        	
//        	st=conn.createStatement();
//        	nb=st.executeUpdate("CREATE TABLE letemps (" + 
//        			"	id NUMBER," + 
//        			"	ladate DATE," + 
//        			"	heure DATE," + 
//        			"	ladateetheure DATE," + 
//        			"	ladateetheure2 TIMESTAMP(3)" + 
//        			"	);");
//System.out.println("nombre="+nb);

			ps=conn.prepareStatement("update letemps set heure=? where id=?");
			ps.setNull(1, Types.DATE);
			ps.setInt(2, 1);
			nb=ps.executeUpdate();
System.out.println("nombre="+nb);

//        	
//            rs=st.executeQuery("select * from client");
        	ps2=conn.prepareStatement("select * from letemps");
            rs=ps2.executeQuery();
            while(rs.next()) {
            	System.out.println(rs.getLong("id")+"\t"+ 
            						rs.getDate("ladate")+"\t"+
            						rs.getTime("heure")+"\t"+
            						rs.getTimestamp("ladateetheure")+"\t"+
            						rs.getTimestamp("ladateetheure2")
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
