import java.util.Date;

public class Enchere {
	public Objet objet;
	protected Date datelimite;
	protected EtatEnchere etatEnchere;
	protected float prixMin;
	protected float prixDeReserve;
	protected Vendeur vendeur;
	private int ID;
	protected int IDobjet;
	String description;

	public Enchere(Vendeur vendeur,int IDobjet, int ID, String description, float prixMin, float prixDeReserve, Date dateLimite){
		this.ID = ID;
		this.IDobjet = IDobjet;
		this.description = description;
		this.prixMin = prixMin;
		this.prixDeReserve = prixDeReserve;
		this.datelimite = dateLimite;
		this.vendeur = vendeur;
	}
	
	public int datePerime(Date Dateenchere, Date Datelimite) {					//Verifie si la date de l'enchere est dépassé
		if (Dateenchere.after(Datelimite) == true) {
			System.out.println("La date limite de l'enchere a été depassé.\n");
			return 1;
		} else {
			System.out.println("La date limite de l'enchere est respecté.\n");
			return 0;
		}
	}

	public int bonPrixMin(float prixmin, float prixdonne) {        				  //Verifie si le prix minimal pour encherir est respecté
		if (prixdonne < prixmin) {
			return 1;
		}
		
		if (prixdonne > prixmin) {
			return 2;
		} else {
			return 0;
		}

	}
}
