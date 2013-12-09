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
}
