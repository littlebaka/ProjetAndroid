package fr.exia.puydufou.core;

import android.database.Cursor;

public interface ShopLoadable {

	public Cursor getAllBoutiques();
	
	public Cursor getBoutiqueById(String id);
}
