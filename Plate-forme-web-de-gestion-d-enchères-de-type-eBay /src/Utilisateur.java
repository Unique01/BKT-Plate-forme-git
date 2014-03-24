import java.util.HashMap;
import java.util.Map;

public abstract class Utilisateur {
	protected String login;
	protected String nom;
	protected String prenom;
	protected Objet objet;
	
	public static Map<String,Objet> encheresDispoMap = new HashMap<String,Objet>();		//Encheres disponibles aux vendeur
}
