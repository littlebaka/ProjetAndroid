package fr.exia.puydufou.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.ContentValues;

import fr.exia.puydufou.provider.ParcContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Parc;
import fr.exia.puydufou.webservice.WebServiceManager;

public class DatasLoader implements DatasLoadable {

	
	private WebServiceManager webServiceManager;
	
	private final String TAG_PARC = "parc";
	
	public DatasLoader() {
		this.webServiceManager = new WebServiceManager();
	}
	
	@Override
	public void getAndStoreParcInformation(ContentResolver contentResolver) {
		
		try{
			JSONArray jsonArray = this.webServiceManager.getParcInformation().getJSONArray(TAG_PARC);
			
			
			for(int i = 0; i < jsonArray.length(); i++){
			
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues parc = new ContentValues();
				parc.put(Parc.NOM_PARC, p.getString(Parc.NOM_PARC));
				parc.put(Parc.INFO_PARC, p.getString(Parc.INFO_PARC));
				parc.put(Parc.VERSION_PARC, p.getString(Parc.VERSION_PARC));
				contentResolver.insert(ParcContentProvider.CONTENT_URI, parc);
				parc.clear();
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
		
	}

}
