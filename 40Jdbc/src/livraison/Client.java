package livraison;

public class Client {
	//noclient    not null number(38)   
	//nomclient   not null varchar2(20) 
	//notelephone not null varchar2(15) 	
	private Long noclient;
	private String nomclient;
	private String notelephone;
	
	public Long getNoclient() {
		return noclient;
	}
	public void setNoclient(Long noclient) {
		this.noclient = noclient;
	}
	public String getNomclient() {
		return nomclient;
	}
	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}
	public String getNotelephone() {
		return notelephone;
	}
	public void setNotelephone(String notelephone) {
		this.notelephone = notelephone;
	}
	@Override
	public String toString() {
		return "Client [noclient=" + noclient + ", nomclient=" + nomclient + ", notelephone=" + notelephone + "]";
	}
	
}
