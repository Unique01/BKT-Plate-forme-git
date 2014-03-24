
public class Offre {
	public String emetteur;
	float prix;
	private Objet objet;
	public boolean nouvelleOffres = false;
	
	public Offre(String login, float prix, Objet objet){
		this.emetteur = login;
		this.prix = prix;
		this.objet = objet;
		
		if(objet.prixMin < this.prix){
			objet.offreMax = this;
		}
		
	}
}
