package livraison;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
	private Connection conn;
	
	public ClientDAO(Connection conn) {
		this.conn=conn;
	}
	
	public boolean insert(Client client) throws SQLException {
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int nb;
  
//        	st=conn.createStatement();
//        	nb=st.executeUpdate("insert into client (noclient,nomclient,notelephone) values(101,'G Lemoyne','911')");
//          System.out.println("nombre="+nb);        	
        //rs=st.executeQuery("select * from client where noclient>"+strId);           
    	ps=conn.prepareStatement("insert into client (noclient,nomclient,notelephone) values(?,?,?)");
        ps.setLong(1, client.getNoclient());
        ps.setString(2, client.getNomclient());
        ps.setString(3, client.getNotelephone());
    	nb=ps.executeUpdate();            
    	if(nb>0)
    		return true;
    	else
    		return false;
	}
	
	public Client findByKey(Long id) throws SQLException {
		Client client = null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
//        	st=conn.createStatement();
//        	nb=st.executeUpdate("insert into client (noclient,nomclient,notelephone) values(101,'G Lemoyne','911')");
//          System.out.println("nombre="+nb);        	
        //rs=st.executeQuery("select * from client where noclient>"+strId);           
    	ps=conn.prepareStatement("select * from client where noclient=?");
        ps.setLong(1, id);
        rs=ps.executeQuery();
            
        while(rs.next()) {
        	client=new Client();
        	client.setNoclient(rs.getLong("noclient"));
        	client.setNomclient(rs.getString("nomclient"));
        	client.setNotelephone(rs.getString("notelephone"));
        }        	
        	  				
		return client;
	}

	public List<Client> findAll() throws SQLException {
		List<Client> clients=new ArrayList<>();
		Client client = null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
//        	st=conn.createStatement();
//        	nb=st.executeUpdate("insert into client (noclient,nomclient,notelephone) values(101,'G Lemoyne','911')");
//          System.out.println("nombre="+nb);        	
        //rs=st.executeQuery("select * from client where noclient>"+strId);           
    	ps=conn.prepareStatement("select * from client");
        //ps.setLong(1, id);
        rs=ps.executeQuery();
            
        while(rs.next()) {
        	client=new Client();
        	client.setNoclient(rs.getLong("noclient"));
        	client.setNomclient(rs.getString("nomclient"));
        	client.setNotelephone(rs.getString("notelephone"));
        	clients.add(client);
        }        	
        	  				
		return clients;
	}
	
	public Commande sumLivraison(Long id) throws SQLException {
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Commande commande=null;

    	ps=conn.prepareStatement("select c.nocommande, c.datecommande, sum(lc.quantite*a.prixunitaire) as prixht, sum(lc.quantite*a.prixunitaire)*1.15  as prixttc" + 
				    			" from commande c,lignecommande lc,article a" + 
				    			" where c.nocommande=lc.nocommande" + 
				    			" and lc.noarticle=a.noarticle" + 
				    			" and c.nocommande=?" + 
				    			" group by c.nocommande, c.datecommande");
        ps.setLong(1, id);
        rs=ps.executeQuery();
            
        while(rs.next()) {
        	commande=new Commande();
        	commande.setNocommande(rs.getLong("nocommande"));
        	//commande.setNoclient(rs.getLong("noclient"));
        	commande.setDatecommande(rs.getDate("datecommande"));
        	commande.setPrixHt(rs.getBigDecimal("prixHt"));
        	commande.setPrixTtc(rs.getBigDecimal("prixTtc"));
        }   
		return commande;
	}
	
}
