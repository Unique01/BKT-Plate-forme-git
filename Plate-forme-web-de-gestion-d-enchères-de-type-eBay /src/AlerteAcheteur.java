
public interface AlerteAcheteur {
	public boolean prixReserveAtteint(Objet a);
	public boolean enchereAnnule(int objet,Enchere enchere);
	public boolean surEnchere(float prix , Objet objet);
	public boolean activationAlerte(boolean rep);
}
