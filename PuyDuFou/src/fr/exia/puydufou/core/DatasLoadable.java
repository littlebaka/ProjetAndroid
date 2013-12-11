package fr.exia.puydufou.core;

import android.content.ContentResolver;

public interface DatasLoadable {

	public void getAndStoreParcInformation(ContentResolver contentResolver);

	public void getAndStoreHoraireInformation(ContentResolver contentResolver);
	
	public void getAndStoreMenuInformation(ContentResolver contentResolver);
	
	public void getAndStoreServiceInformation(ContentResolver contentResolver);
	
	public void getAndStoreServiceMenuInformation(ContentResolver contentResolver);
	
	public void getAndStoreSpectacleInformation(ContentResolver contentResolver);
	
	public void getAndStoreSpectacleHoraireInformation(ContentResolver contentResolver);
	
	public void getAndStoreTypeServiceInformation(ContentResolver contentResolver);
	
	public void getAndStoreNoteServiceInformation(ContentResolver contentResolver);
	
	public void getAndStoreNoteSpectacleInformation(ContentResolver contentResolver);
	
	public void getAndStoreNoteInformation(ContentResolver contentResolver);
	
}
