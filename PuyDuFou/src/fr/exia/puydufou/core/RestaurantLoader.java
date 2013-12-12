package fr.exia.puydufou.core;

import fr.exia.puydufou.provider.MenuContentProvider;
import fr.exia.puydufou.provider.ServiceContentProvider;
import fr.exia.puydufou.provider.Service_MenuContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Menu;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.Service_Menu;
import android.content.Context;
import android.database.Cursor;

public class RestaurantLoader implements RestaurantLoable {

private Context context;
	
	public RestaurantLoader(Context context){
			this.context = context;
	}
	
	@Override
	public Cursor getAllRestaurant() {
		String projection[] = new String[] {Service.ID_SERVICE, Service.NOM_SERVICE };
		String selection = Service.ID_TYPE_SERVICE + " =  1";
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

	@Override
	public Cursor getRestaurantById(String id) {
		String projection[] = new String[] {Service.INFORMATION };
		String selection = Service.ID_SERVICE + " = " + id ;
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

	@Override
	public Cursor getMenuServiceById(String id) {
		String projection[] = new String[] {Service_Menu.ID_MENU};
		String selection = Service_Menu.ID_SERVICE + " = " + id ;
		Cursor cursor = this.context.getContentResolver().query(Service_MenuContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

	@Override
	public Cursor getMenuById(String id) {
		String projection[] = new String[] {Menu.NOM_MENU, Menu.PRIX_MENU, Menu.DESCRIPTION_MENU};
		String selection = Menu.ID_MENU + " = " + id ;
		Cursor cursor = this.context.getContentResolver().query(MenuContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

}
