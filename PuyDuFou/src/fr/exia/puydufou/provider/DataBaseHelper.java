package fr.exia.puydufou.provider;

import fr.exia.puydufou.provider.SharedInformation.Horaire;
import fr.exia.puydufou.provider.SharedInformation.Menu;
import fr.exia.puydufou.provider.SharedInformation.Note;
import fr.exia.puydufou.provider.SharedInformation.NoteService;
import fr.exia.puydufou.provider.SharedInformation.NoteSpectacle;
import fr.exia.puydufou.provider.SharedInformation.Parc;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.Service_Menu;
import fr.exia.puydufou.provider.SharedInformation.Spectacle;
import fr.exia.puydufou.provider.SharedInformation.Spectacle_Horaire;
import fr.exia.puydufou.provider.SharedInformation.TypeService;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	public static final String CONTENT_PROVIDER_DB_NAME = "puydufou.db";
	
	public static final int CONTENT_PROVIDER_DB_VERSION = 1;
	
	public static final String CONTENT_PROVIDER_TABLE_NAME_SERVICE = "service";
	public static final String CONTENT_PROVIDER_TABLE_NAME_SPECTACLE_HORAIRE = "spectacle_horaire";
	public static final String CONTENT_PROVIDER_TABLE_NAME_SPECTACLE = "spectacle";
	public static final String CONTENT_PROVIDER_TABLE_NAME_TYPE_SERVICE = "typeservice";
	public static final String CONTENT_PROVIDER_TABLE_NAME_HORAIRE = "horaire";
	public static final String CONTENT_PROVIDER_TABLE_NAME_MENU = "menu";
	public static final String CONTENT_PROVIDER_TABLE_NAME_NOTE = "note";
	public static final String CONTENT_PROVIDER_TABLE_NAME_NOTE_SERVICE = "noteservice";
	public static final String CONTENT_PROVIDER_TABLE_NAME_NOTE_SPECTACLE = "notespectacle";
	public static final String CONTENT_PROVIDER_TABLE_NAME_PARC = "parc";
	public static final String CONTENT_PROVIDER_TABLE_NAME_SERVICE_MENU = "service_menu";
	
	public DataBaseHelper(Context context) {
		super(context, CONTENT_PROVIDER_DB_NAME, null, CONTENT_PROVIDER_DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_SERVICE 
				+ "(" 
				+ Service.ID_SERVICE + " INTEGER PRIMARY KEY, "
				+ Service.ID_TYPE_SERVICE + " NUMERIC, "
				+ Service.ID_PARC + " NUMERIC, "
				+ Service.NOM_SERVICE + " TEXT, "
				+ Service.INFORMATION + " TEXT, "
				+ Service.LOCALISATION + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_SPECTACLE_HORAIRE 
				+ "(" 
				+ Spectacle_Horaire.ID_SPECTACLE + " NUMERIC, "
				+ Spectacle_Horaire.ID_HORAIRE + " NUMERIC "
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_SPECTACLE 
				+ "(" 
				+ Spectacle.ID_SPECTACLE + " INTEGER PRIMARY KEY, "
				+ Spectacle.ID_PARC + " NUMERIC, "
				+ Spectacle.DUREE + " NUMERIC, "
				+ Spectacle.DATE_CREATION + " TEXT, "
				+ Spectacle.NB_ACTEUR + " NUMERIC, "
				+ Spectacle.EVEN_HIST + " TEXT, "
				+ Spectacle.INFORMATION_SPECTACLE + " TEXT, "
				+ Spectacle.NOM_SPECTACLE + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_TYPE_SERVICE 
				+ "(" 
				+ TypeService.ID_TYPE_SERVICE + " INTEGER PRIMARY KEY, "
				+ TypeService.TYPE_SERVICE + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_HORAIRE
				+ "("
				+ Horaire.ID_HORAIRE + " INTEGER PRIMARY KEY, "
				+ Horaire.HORAIRE + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_MENU 
				+ "(" 
				+ Menu.ID_MENU + " INTEGER PRIMARY KEY, "
				+ Menu.NOM_MENU + " TEXT, "
				+ Menu.DESCRIPTION_MENU + " TEXT, "
				+ Menu.PRIX_MENU + " NUMERIC"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE 
				+ "(" 
				+ Note.ID_NOTE + " INTEGER PRIMARY KEY, "
				+ Note.NOTE + " NUMERIC"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE_SERVICE 
				+ "(" 
				+ NoteService.ID_NOTE + " NUMERIC, "
				+ NoteService.ID_SERVICE + " NUMERIC"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE_SPECTACLE 
				+ "(" 
				+ NoteSpectacle.ID_NOTE + " NUMERIC, "
				+ NoteSpectacle.ID_SPECTACLE + " NUMERIC"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_PARC 
				+ "(" 
				+ Parc.ID_PARC + " INTEGER PRIMARY KEY, "
				+ Parc.NOM_PARC + " TEXT, "
				+ Parc.INFO_PARC + " TEXT, "
				+ Parc.VERSION_PARC + " TEXT"
				+ ");");
		
		db.execSQL("CREATE TABLE "
				+ CONTENT_PROVIDER_TABLE_NAME_SERVICE_MENU 
				+ "(" 
				+ Service_Menu.ID_MENU + " NUMERIC, "
				+ Service_Menu.ID_SERVICE + " NUMERIC"
				+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_SERVICE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_SPECTACLE_HORAIRE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_SPECTACLE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_TYPE_SERVICE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_HORAIRE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_MENU);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE_SERVICE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_NOTE_SPECTACLE);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_PARC);
		db.execSQL("DROP TABLE IF EXITS "
				+ CONTENT_PROVIDER_TABLE_NAME_SERVICE_MENU);
		onCreate(db);
		
	}

}
