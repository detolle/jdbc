package livraison;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class exo5 {

	public static void main(String[] args) {
        Connection conn = null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id;
		Client client=null;
		Commande commande=null;
		
		/**
		 *  connection
		 */
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1","livraison","livraison");
            conn.setAutoCommit(false);
        	
        	ClientDAO clientDAO=new ClientDAO(conn);
        	client=clientDAO.findByKey(10L);        	
        	System.out.println(client);
        	
        	client.setNoclient(101L);
        	clientDAO.insert(client);

        	client=clientDAO.findByKey(101L);        	
        	System.out.println(client);

        	commande=clientDAO.sumLivraison(1L);
        	System.out.println(commande);
        	
        	List<Client> clients=new ArrayList<>();
        	clients=clientDAO.findAll();
        		
        	for(Client c:clients) {
            	System.out.println(c);        		
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
