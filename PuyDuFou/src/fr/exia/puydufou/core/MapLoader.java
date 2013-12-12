package fr.exia.puydufou.core;

import fr.exia.puydufou.provider.ServiceContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.Spectacle;
import fr.exia.puydufou.provider.SpectacleContentProvider;
import android.content.Context;
import android.database.Cursor;

public class MapLoader implements MapLoadable {
	private Context context;
	
	public MapLoader(Context context) {
		this.context = context;
	}

	@Override
	public Cursor getLocationSpectacle() {
		String projection[] = new String[] {Spectacle.ID_SPECTACLE, Spectacle.NOM_SPECTACLE, Spectacle.LONGITUDE, Spectacle.LATITUDE, Spectacle.INFORMATION_SPECTACLE };
		
		Cursor cursor = this.context.getContentResolver().query(SpectacleContentProvider.CONTENT_URI,
				projection,
				null,
				null,
				null);
		
		return cursor;
	}


	@Override
	public Cursor getLocationBoutiques() {
		String projection[] = new String[] {Service.ID_SERVICE, Service.NOM_SERVICE, Service.LONGITUDE, Service.LATITUDE, Service.INFORMATION };
		String selection = Service.ID_TYPE_SERVICE + " =  2";
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
	}

	@Override
	public Cursor getLocationRestaurants() {
		String projection[] = new String[] {Service.ID_SERVICE, Service.NOM_SERVICE, Service.LONGITUDE, Service.LATITUDE, Service.INFORMATION };
		String selection = Service.ID_TYPE_SERVICE + " =  1";
		Cursor cursor = this.context.getContentResolver().query(ServiceContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		
		return cursor;
		
	}
}
