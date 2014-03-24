import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;


public class JUnitTest {

	private Objet objet1;
	private Objet objet2;
    private Enchere e1;
    private Enchere e2;
    private Horloge h1;
    private Horloge h2;
    private Acheteur a1;
    private Acheteur a4;
    private Acheteur a5;
    private Vendeur v1;
    private Vendeur v2;
    private int id1;
    private int id2;
	
	@Before
	public void setUp() throws Exception {
		id1 = 1;
		id2 = 2;
		v1 = new Vendeur("utilisateur1", "Jean", "Dupont");
		a1 = new Acheteur("utilisateur1", "Jean", "Dupont");
		v2 = new Vendeur("utilisateur2", "Jean", "Dupont");
		a4 = new Acheteur("Toto", "Jean", "Dupont");
		a5 = new Acheteur("utilisateur2", "Jean", "Dupont");
		objet1 = new Objet(id1,"Tres belle voiture", 5000,  new SimpleDateFormat("yyyy-MM-dd").parse("2015-05-29"));
		objet2 = new Objet(id2,"Tres bonne console de jeux", 200,new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-29"));		
		e1 = new Enchere(v1,objet1.ID,1, "Tres belle voiture", 5000, 5000, new SimpleDateFormat("yyyy-MM-dd").parse("2015-05-29"));
		e2 = new Enchere(v2,objet2.ID,2, "Tres bonne console de jeux", 200, 200, new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-29"));
		e1.etatEnchere = EtatEnchere.ANNULEE;
		e2.etatEnchere = EtatEnchere.CREE;
		h1 = new Horloge();
		h2 = new Horloge();
		a1.prix = 4000;
		a5.prix = 200;
		a1.rep=true;
		a5.rep=true;
		v1.creeEnchere(v1,objet1.ID,"Tres belle voiture", 5000, 5000, e1.datelimite);
		v2.creeEnchere(v2,objet2.ID,"Tres bonne console de jeux", 200, 200, e2.datelimite);		
	}

	@Test
	public void TestoffreEtDemande() {								//Test le bon fonctionnement des méthodes de base des classes Acheteur et Vendeur
		v1.publierEnchere("Tres belle voiture");
		a1.emettreOffre(a1.prix,e1);
		assertTrue(a1.prixReserveAtteint(objet1)== false);
		assertFalse(v1.encheresDispoMap.isEmpty());
		v1.annulerEnchere("Tres belle voiture");
		assertTrue(v1.encheresDispoMap.isEmpty()==false);
	}
	
	@Test
	public void TestexistanceObjet() {
		assertTrue(objet1 != null);
	}
	
	@Test
	public void TestexistanceLoginUtilisateur() { 					//Verifie l'existance d'un l'utilisateur
		assertTrue(a1.login != null);
		assertTrue(v1.login != null);
	}
	
	@Test
	public void TestLogins() { 										//Verifie l'unicité des utilisateurs
		assertTrue(a1.login.equals(v1.login));
		assertFalse(a1.login.equals(v2.login));
	}

	@Test
	public void testdatePerime() {									//Nous verifions notre capacité à verifier l'actualité d'une proposition d'enchere									
		try {
			e1.datelimite = new SimpleDateFormat("yyyy-MM-dd").parse("2015-05-29");
			e2.datelimite = new SimpleDateFormat("yyyy-MM-dd").parse("2014-05-29");
			h1.datehorloge = new SimpleDateFormat("yyyy-MM-dd").parse("2015-10-29");
			h2.datehorloge = new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-29");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue("Produit P�rim�",e1.datePerime(e1.datelimite, h1.datehorloge)==0);
		assertTrue("Produit valide",e1.datePerime(e1.datelimite, h2.datehorloge)==1);
		assertTrue("Produit valide",e1.datePerime(e2.datelimite, h1.datehorloge)==0);
		assertTrue("Produit valide",e1.datePerime(e2.datelimite, h2.datehorloge)==0);
	}
	
	@Test
	public void testbonPrixMin() {								//Nous verifions notre capacité à verifier la valeur d'une offre par rapport à la demande du vendeur
		e1.prixMin = 40;
		e2.prixMin = 20;
		a4.prix = 40;
		a5.prix = 20;
		assertTrue("Bon Prix min",e1.bonPrixMin(e1.prixMin, a4.prix)==0);
		assertTrue("Prix min trop grand",e1.bonPrixMin(e1.prixMin, a5.prix)==1);
		assertTrue("Prix min trop petit",e1.bonPrixMin(e2.prixMin, a4.prix)==2);
		assertTrue("Bon Prix min",e1.bonPrixMin(e2.prixMin, a5.prix)==0);
	}
	
	@Test
	public void TestEnchereAnnulee() { 							//annulation d'une enchere
		assertTrue(a1.enchereAnnule(objet1.ID ,e1)==true);
	}
	
	@Test
	public void TestSurenchere() { 								//Test la méthode verifiant la surenchere 
		assertTrue(a1.surEnchere(a1.prix, objet1)==true);	
	}
	
	@Test
	public void TestActivationAlerte() { 						//L'acheteur a1 a son alerte activé alors que que ce n'est pas le cas pour acheteur a4
		assertTrue(a1.activationAlerte(a1.rep)==true);
		assertTrue(a4.activationAlerte(a4.rep)==false);
	}
	
	@Test
	public void TestSurenchereAvecSansAlerte() { 				//L'alerte n'a pas lieu ici
		assertTrue(a1.surEnchere(a1.prix, objet1)==true);
		assertTrue(a4.surEnchere(a4.prix, objet1)==false);
	}
	
	@Test
	public void TestEnchereAnnuleeAvecSansAlerte() {			 //L'alerte n'a pas lieu ici
		assertTrue(a1.enchereAnnule(objet1.ID ,e1)==true);
		assertTrue(a4.enchereAnnule(objet1.ID ,e1)==false);
	}
	
	 @Test
	 public void testAutoOffreImpossible() {    				//Un acheteur ne peut emettre une offre sur une de ses encheres
		 v1.publierEnchere("Tres belle voiture");
		 assertEquals(a1.emettreOffre(a1.prix,e1),"Vous ne pouvez pas emettre d'offre sur une de vos encheres.\n" );
		 assertEquals(a5.emettreOffre(a5.prix,e1),"Votre offre de " + a5.prix + "au nom de" + a5.login + "a bien été pris en compte.\n" );				
	 }
	 
	 @Test
	 public void testUniciteObjet() {     						//Test l'unicité des objets 
		 assertTrue(objet1.ID!=objet2.ID);
	 }
	 
	 @Test
	 public void testAlerteVendeur() {    						 //Test l'alerte relative au vendeur
		 assertTrue(v1.enchereEffectue(e1)==false);
		 e1.prixMin=6000;
		 assertTrue(v1.enchereEffectue(e1)==true);
	 }
}
