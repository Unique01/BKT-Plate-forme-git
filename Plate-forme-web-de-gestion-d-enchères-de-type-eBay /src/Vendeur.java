import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vendeur extends Utilisateur implements AlerteVendeur {

	private String description;
	private float prixMin;
	private Date dateLimite;
	private float prixDeReserve;
	private int ID = 0;
	
	public Map<String,Objet> encheresMap = new HashMap<String,Objet>();

	public Vendeur(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String creeEnchere(Vendeur vendeur,int IDobjet, String description, float prixMin, float prixDeReserve, Date dateLimite) {				//Permet de créer une enchere
		this.description = description;
		this.prixMin = prixMin;
		this.dateLimite = dateLimite;
		this.prixDeReserve = prixDeReserve;
		
		new Enchere(vendeur,IDobjet, ID, description, prixMin, prixDeReserve, dateLimite);
		ID++;
		encheresDispoMap.put(description,new Objet(ID,description, prixMin, dateLimite));

		System.out.println("<--  Enchere créé  --> \n description : " + description
				+ "\n Prix minimum : " + prixMin
				+ "\n Date Limite de l'enchere : " + dateLimite + "\n");

		return description;
	}

	public void publierEnchere(String description) {														//Permet de publier une enchere
		this.description = description;
		Objet ObjetPublic = encheresDispoMap.get(description);
		encheresDispoMap.put(description, ObjetPublic);
		System.out.println("L'enchere "+ description +" a été publié.\n");
	}

	public String annulerEnchere(String description) {														//Permet d'annuler une enchere
		this.description = description;
		
		Objet objetPublic = encheresDispoMap.get(description);
		
		if (this.prixDeReserve < objetPublic.offreActuelle) {												
			encheresDispoMap.remove(objetPublic);
			encheresMap.remove(objetPublic);
			
			return "L'enchere a été annulé.\n";
		} else {
			return "L'enchere ne peut etre annulée car le prix de réserve a été atteint par un acheteur.\n";
		}
	}

	@Override
	public boolean enchereEffectue(Enchere enchere) {														//Permet d'avertir qu'une offre à été proposé par un acheteur		
		if(encheresDispoMap.containsKey(enchere.description) && prixMin < enchere.prixMin)
		{
			System.out.println("De nouvelles offres ont été proposées pour vos produits.\n");
			return true;
		}
		else
		{
			return false;
		}
	}

}
