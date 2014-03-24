
public class Acheteur extends Utilisateur implements AlerteAcheteur {

	protected float prix;
	protected boolean rep;

	public Acheteur(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public String emettreOffre(float prix ,Enchere enchere) 							//Emettre des offres pour une enchère publiée par un autre utilisateur que lui
	{	
		this.prix=prix;
		if(encheresDispoMap.containsKey(enchere.description)==true){
			if(this.login.equals( enchere.vendeur.login)==false)
				{			
				this.objet = this.encheresDispoMap.get(enchere.description);
					
				new Offre(login, prix, objet); 
					
					return "Votre offre de " + prix + "au nom de" + login + "a bien été pris en compte.\n";
				}
				else
				{			
					return "Vous ne pouvez pas emettre d'offre sur une de vos encheres.\n";	
				}
			}
		else{
			return "Objet Inexistant";
		}
	}
	
	@Override
	public boolean activationAlerte(boolean rep){
		return rep;
	}

	@Override
	public boolean prixReserveAtteint(Objet a) {														//Verifie si le prix de reserve a été atteint
		if (a.offreActuelle > a.prixMin && rep== true) 
		{
			if (a.offreActuelle == a.prixMin) {
				System.out.println( "Vous avez atteint le prix de réserve.\n");
				return true;
			}
			else
			{
				System.out.println( "Le prix de réserve a été atteint par un autre acheteur.\n");
				return true;
			}
		} 
		else
		{
			System.out.println( "Aucun acheteur n'a atteint le prix de réserve.\n");

			return false;
		}
	}

	@Override
	public boolean enchereAnnule(int objet, Enchere enchere) {											//Alerte l'acheteur lorsqu'une enchere est annulé
		if(objet == enchere.IDobjet && enchere.etatEnchere == EtatEnchere.ANNULEE &&rep ==true)
		{
			System.out.println("L'enchere : " +enchere.description + " a été annulée.\n");
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean surEnchere(float prix, Objet objet) {												//Alerte l'acheteur lorsqu'une sur enchere à lieu
		if(prix<objet.offreActuelle &&rep==true)
		{
		System.out.println("Un autre acheteur à sur encherie sur l'objet : " + objet.description + ".\n");
		return true;
		}
		else
		{
		return false;	
		}

	}
	
}
