package fr.exia.puydufou.core;

import fr.exia.puydufou.provider.ServiceContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.Spectacle;
import fr.exia.puydufou.provider.SharedInformation.Spectacle_Horaire;
import fr.exia.puydufou.provider.SpectacleContentProvider;
import android.content.Context;
import android.database.Cursor;

public class ShowLoader implements ShowLoadable {
	private Context context;
	
	public ShowLoader(Context context){
		this.context = context;
	}

	@Override
	public Cursor getAllShow() {
		String projection[] = new String[] {Spectacle.ID_SPECTACLE, Spectacle.NOM_SPECTACLE};
		Cursor cursor = this.context.getContentResolver().query(SpectacleContentProvider.CONTENT_URI,
				projection,
				null,
				null,
				null);
		return cursor;
	}

	@Override
	public Cursor getShowById(String id) {
		String projection[] = new String[] {Spectacle.DATE_CREATION, Spectacle.NB_ACTEUR, Spectacle.DUREE, Spectacle.INFORMATION_SPECTACLE, Spectacle.EVEN_HIST};
		
		String selection = Spectacle.ID_SPECTACLE + " = " + id;
		
		Cursor cursor = this.context.getContentResolver().query(SpectacleContentProvider.CONTENT_URI,
				projection,
				selection,
				null,
				null);
		return cursor;
	}

	@Override
	public Cursor getSchedule(String id) {
		String projection[] = new String[] {Spectacle_Horaire.ID_HORAIRE};
		
		return null;
	}
}
