import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Objet {
	public int ID;
	public Vendeur proprietaire;
	public String description;
	public float offreActuelle = 0;
	public float prixMin;	
	public Date dateLimite;
	public Offre offreMax;
	public Map<String,Objet> objetMap = new HashMap<String,Objet>();
	
	public Objet(int ID,String description, float prixMin, Date dateLimite){
		this.ID = ID;
		this.description = description;
		this.prixMin = prixMin;
		this.offreActuelle=prixMin;
		this.dateLimite = dateLimite;
		
		objetMap.put(description, this);
	}
	
	public void offreMax(){						//Permet de déterminer l'offre maximum pour un objet particulier lorsque l'enchere se termine
		offreActuelle = offreMax.prix; 
		System.out.println("L'acheteur " + offreMax.emetteur + " a proposé l'offre la plus importante.\n");
	}
	
	public boolean existanceObjet(){
		if(objetMap.containsValue(this))
			return true;
		else
			return false;
	}
}	
