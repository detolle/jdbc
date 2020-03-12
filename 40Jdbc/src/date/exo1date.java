package date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class exo1date {

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

			ps=conn.prepareStatement("insert into letemps (id,ladate,heure,ladateetheure,ladateetheure2) values(?,?,?,?,?)");
			//java.sql.Date date = new java.sql.Date(0000-00-00);
			Date now=new Date();
			
			ps.setInt(1, 1);
			ps.setDate(2, new java.sql.Date(now.getTime()));
			ps.setTime(3, new java.sql.Time(now.getTime()));
			ps.setTimestamp(4, new java.sql.Timestamp(now.getTime()));
			ps.setTimestamp(5, new java.sql.Timestamp(now.getTime()));
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
                    //conn.rollback();
                    conn.close();
                    System.exit(0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }      
	}

}
