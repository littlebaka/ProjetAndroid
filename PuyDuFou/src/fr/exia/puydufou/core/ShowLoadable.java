package fr.exia.puydufou.core;

import android.database.Cursor;

public interface ShowLoadable {

	public Cursor getAllShow();
	
	public Cursor getShowById(String id);
	public Cursor getSchedule(String id);
}
