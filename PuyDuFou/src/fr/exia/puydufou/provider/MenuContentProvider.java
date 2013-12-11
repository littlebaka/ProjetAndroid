package fr.exia.puydufou.provider;

import fr.exia.puydufou.provider.SharedInformation.Menu;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MenuContentProvider extends ContentProvider {
	
	//URI de notre content provider
		public static final Uri CONTENT_URI = Uri.parse("content://fr.exia.puydufou.provider.menucontentprovider");
		
		//MIME
		public static final String CONTENT_PROVIDER_MIME = "vnd.android.cursor.item/vnd.exia.puydufou.provider.menu";
		
		private DataBaseHelper dbHelper;

		@Override
		public int delete(Uri uri, String selection, String[] selectionArgs) {
			long id = this.getId(uri);
			SQLiteDatabase db = this.dbHelper.getWritableDatabase();
			
			try
			{
				if(id < 0)
				{
					return db.delete(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, selection, selectionArgs);
				}
				else
				{
					return db.delete(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, Menu.ID_MENU + "=" + id, selectionArgs);
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
				long id = db.insertOrThrow(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, null, values);
				
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
			this.dbHelper = new DataBaseHelper(getContext());
			return true;
		}


		@Override
		public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
				String setOrder) {
			long id = this.getId(uri);
			SQLiteDatabase db = this.dbHelper.getReadableDatabase();
			
			if(id < 0)
			{
				return db.query(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, projection, selection, selectionArgs, null, null, setOrder);
			}
			else
			{
				return db.query(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, projection, Menu.ID_MENU + "=" + id, null, null, null, null);
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
					return db.update(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, values, selection, selectionArgs);
				}
				else
				{
					return db.update(DataBaseHelper.CONTENT_PROVIDER_TABLE_NAME_MENU, values, Menu.ID_MENU + "=" + id, null);
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
