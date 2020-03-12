package livraison;

import java.math.BigDecimal;
import java.util.Date;

public class Commande {
	private Long nocommande;   
	private Date datecommande; 
	private Long noclient;
	private BigDecimal prixHt;
	private BigDecimal prixTtc;
	
	public Long getNocommande() {
		return nocommande;
	}
	public void setNocommande(Long nocommande) {
		this.nocommande = nocommande;
	}
	public Date getDatecommande() {
		return datecommande;
	}
	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}
	public Long getNoclient() {
		return noclient;
	}
	public void setNoclient(Long noclient) {
		this.noclient = noclient;
	}
	public BigDecimal getPrixHt() {
		return prixHt;
	}
	public void setPrixHt(BigDecimal prixHt) {
		this.prixHt = prixHt;
	}
	public BigDecimal getPrixTtc() {
		return prixTtc;
	}
	public void setPrixTtc(BigDecimal prixTtc) {
		this.prixTtc = prixTtc;
	}
	@Override
	public String toString() {
		return "Commande [nocommande=" + nocommande + ", datecommande=" + datecommande + ", noclient=" + noclient
				+ ", prixHt=" + prixHt + ", prixTtc=" + prixTtc + "]";
	}

}
