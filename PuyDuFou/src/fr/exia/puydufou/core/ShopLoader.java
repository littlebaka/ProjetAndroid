package fr.exia.puydufou.core;

import android.content.Context;
import android.database.Cursor;
import fr.exia.puydufou.provider.ServiceContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.TypeService;

public class ShopLoader implements ShopLoadable {

	private Context context;
	
	public ShopLoader(Context context){
		this.context = context;
	}
	
	public Cursor getAllBoutiques(){
		String projection[] = new String[] {Service.ID_SERVICE, Service.NOM_SERVICE };
		String selection = Service.ID_TYPE_SERVICE + " =  2";
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

	@Override
	public Cursor getBoutiqueById(String id) {
		String projection[] = new String[] {Service.NOM_SERVICE, Service.INFORMATION };
		String selection = Service.ID_SERVICE + " = " + id;
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}
	
}
