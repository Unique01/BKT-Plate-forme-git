import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Horloge {
	
	GregorianCalendar calendar = new GregorianCalendar();
	Date datehorloge;
	
	public Horloge() { }
	
	public Horloge(DateFormat dateFormat) { }
	
	public void ajouterMinute(GregorianCalendar calendar, int valeur)			//Permet de modifier les minutes
	{
		calendar.add (calendar.MINUTE, valeur);
	}
	
	public void ajouterHeure(GregorianCalendar calendar, int valeur)            //Permet de modifier les heures
	{
		calendar.add (calendar.HOUR, valeur);
	}
	
	public void ajouterJour(GregorianCalendar calendar, int valeur)			    //Permet de modifier les jours
	{
		calendar.add (calendar.DAY_OF_MONTH, valeur);
	}
	
	public void ajouterMois(GregorianCalendar calendar, int valeur)			    //Permet de modifier les mois
	{
		calendar.add (calendar.MONTH, valeur);
	}
	
	public void ajouterAnnee(GregorianCalendar calendar, int valeur)			//Permet de modifier les années
	{
		calendar.add (Calendar.YEAR, valeur);
	}
}
