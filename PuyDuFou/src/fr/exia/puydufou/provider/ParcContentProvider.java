package fr.exia.puydufou.provider;

import fr.exia.puydufou.provider.SharedInformation.Parc;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ParcContentProvider extends ContentProvider{

	//URI de notre content provider
	public static final Uri CONTENT_URI = Uri.parse("content://fr.exia.puydufou.provider.parccontentprovider");
	
	//Nom de notre base de données
	public static final String CONTENT_PROVIDER_DB_NAME = "puydufou.db";
	
	//Version de la base de données
	public static final int CONTENT_PROVIDER_DB_VERSION = 1;
	
	//Nom de la table
	public static final String CONTENT_PROVIDER_TABLE_NAME = "parc";
	
	//MIME
	public static final String CONTENT_PROVIDER_MIME = "vnd.android.cursor.item/vnd.exia.puydufou.provider.cours";
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, CONTENT_PROVIDER_DB_NAME, null, CONTENT_PROVIDER_DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "
					+ CONTENT_PROVIDER_TABLE_NAME 
					+ "(" 
					+ Parc.ID_PARC + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ Parc.NOM_PARC + " VARCHAR(255), "
					+ Parc.INFO_PARC + " VARCHAR(255), "
					+ Parc.VERSION_PARC + " VARCHAR(255)"
					+ ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXITS "
					+ CONTENT_PROVIDER_TABLE_NAME);
			onCreate(db);
		}
		
	}
	
	private DatabaseHelper dbHelper;

	@Override
	public int delete(Uri uri, String selection, String[] selectinArgs) {
		long id = this.getId(uri);
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		try
		{
			if(id < 0)
			{
				return db.delete(CONTENT_PROVIDER_TABLE_NAME, selection, selectinArgs);
			}
			else
			{
				return db.delete(CONTENT_PROVIDER_TABLE_NAME, Parc.ID_PARC + "=" + id, selectinArgs);
			}
		} finally {
			db.close();
		}
	}


	@Override
	public String getType(Uri arg0) {
		return CONTENT_PROVIDER_MIME;
	}


	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		try {
			long id = db.insertOrThrow(CONTENT_PROVIDER_TABLE_NAME, null, values);
			
			if(id == -1){
				throw new RuntimeException(String.format("%s : Failed to insert [%s] for unknown reasons.", "DatasContentProvider", values, uri));
			}
			else
			{
				return ContentUris.withAppendedId(uri, id);
			}
		} finally{
			db.close();
		}
	}


	@Override
	public boolean onCreate() {
		this.dbHelper = new DatabaseHelper(getContext());
		return true;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String setOrder) {
		long id = this.getId(uri);
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		
		if(id < 0)
		{
			return db.query(CONTENT_PROVIDER_TABLE_NAME, projection, selection, selectionArgs, null, null, setOrder);
		}
		else
		{
			return db.query(CONTENT_PROVIDER_TABLE_NAME, projection, Parc.ID_PARC + "=" + id, null, null, null, null);
		}
	}


	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

		long id = this.getId(uri);
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		try
		{
			if(id < 0)
			{
				return db.update(CONTENT_PROVIDER_DB_NAME, values, selection, selectionArgs);
			}
			else
			{
				return db.update(CONTENT_PROVIDER_TABLE_NAME, values, Parc.ID_PARC + "=" + id, null);
			}
		} finally{
			db.close();
		}
	}
	
	private long getId(Uri uri){
		String lastPathSegment = uri.getLastPathSegment();
		if(lastPathSegment != null)
		{
			try
			{
				return Long.parseLong(lastPathSegment);
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		return -1;
	}
	
}
