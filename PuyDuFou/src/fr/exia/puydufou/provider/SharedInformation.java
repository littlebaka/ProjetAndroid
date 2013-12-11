package fr.exia.puydufou.provider;

import android.provider.BaseColumns;

public class SharedInformation {

	public static final class Parc implements BaseColumns{
		
		private Parc(){};
		
		public static final String ID_PARC = "ID_PARC";
		public static final String NOM_PARC = "NOM_PARC";
		public static final String INFO_PARC = "INFO_PARC";
		public static final String VERSION_PARC = "VERSION"; 
	}
	
	public static final class Horaire implements BaseColumns{
		
		private Horaire(){};
		
		public static final String ID_HORAIRE = "ID_HORAIRE";
		public static final String HORAIRE = "HORAIRE";
	}
	
	public static final class Menu implements BaseColumns{
		private Menu(){};
		
		public static final String ID_MENU = "ID_MENU";
		public static final String NOM_MENU = "NOM_MENU";
		public static final String DESCRIPTION_MENU = "DESCRIPTION_MENU";
		public static final String PRIX_MENU  = "PRIX_MENU";
	}
	
	public static final class Service implements BaseColumns{
		private Service(){};
		
		public static final String ID_SERVICE = "ID_SERVICE";
		public static final String ID_TYPE_SERVICE = "ID_TYPE_SERVICE";
		public static final String ID_PARC = "ID_PARC";
		public static final String NOM_SERVICE = "NOM_SERVICE";
		public static final String INFORMATION = "INFORMATION";
		public static final String LOCALISATION = "LOCALISATION";
	}
	
	public static final class Service_Menu implements BaseColumns{
		private Service_Menu(){};
		
		public static final String ID_SERVICE = "ID_SERVICE";
		public static final String ID_MENU = "ID_MENU";
	}
	
	public static final class Spectacle implements BaseColumns{
		private Spectacle(){};
		
		public static final String ID_SPECTACLE ="ID_SPECTACLE";
		public static final String ID_PARC = "ID_PARC";
		public static final String DUREE = "DUREE";
		public static final String DATE_CREATION = "DATE_CREATION";
		public static final String NB_ACTEUR = "NB_ACTEUR";
		public static final String EVEN_HIST = "EVEN_HIST";
		public static final String INFORMATION_SPECTACLE = "INFORMATION_SPECTACLE";
		public static final String NOM_SPECTACLE = "NOM_SPECTACLE";
	}
	
	public static final class Spectacle_Horaire implements BaseColumns{
		private Spectacle_Horaire(){};
		
		public static final String ID_SPECTACLE = "ID_SPECTACLE";
		public static final String ID_HORAIRE = "ID_HORAIRE";
	}
	
	public static final class TypeService implements BaseColumns{
		private TypeService(){};
		
		public static final String ID_TYPE_SERVICE = "ID_TYPE_SERVICE";
		public static final String TYPE_SERVICE = "TYPE_SERVICE";
	}
	
	public static final class Note implements BaseColumns{
		private Note(){};
		
		public static final String ID_NOTE = "ID_NOTE";
		public static final String NOTE = "NOTE";
	}
	
	public static final class NoteService implements BaseColumns{
		private NoteService(){};
		
		public static final String ID_NOTE = "ID_NOTE";
		public static final String ID_SERVICE = "ID_SERVICE";
	}
	
	public static final class NoteSpectacle implements BaseColumns{
		private NoteSpectacle(){};
		
		public static final String ID_SPECTACLE = "ID_SPECTACLE";
		public static final String ID_NOTE = "ID_NOTE";
	}
}
