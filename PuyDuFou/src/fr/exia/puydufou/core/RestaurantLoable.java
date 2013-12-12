package fr.exia.puydufou.core;

import android.database.Cursor;

public interface RestaurantLoable {
	public Cursor getAllRestaurant();
	public Cursor getRestaurantById(String id);
	public Cursor getMenuServiceById(String id);
	public Cursor getMenuById(String id);
}
