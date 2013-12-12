package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.core.RestaurantLoable;
import fr.exia.puydufou.core.RestaurantLoader;
import fr.exia.puydufou.core.ShopLoadable;
import fr.exia.puydufou.core.ShopLoader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RestaurantAsyncTask extends AsyncTask<String, String, ListAdapter> {
	private List<Map<String, String>> menu;
	//private ListView listView;
	private Activity view;
	private Context context;
	private String idRestaurant;
	
	private RestaurantLoable restaurantLoable;
	
	
	private String restaurantDescription;

	public RestaurantAsyncTask(Context context,Activity view, String idRestaurant){
		this.context = context;
		//this.listView = listView;
		this.view = view;
		this.idRestaurant = idRestaurant;
	}
	
	@Override
	protected ListAdapter doInBackground(String... params) {
		menu = new ArrayList<Map<String, String>>();
		this.restaurantLoable = new RestaurantLoader(context);
		Cursor cursor = this.restaurantLoable.getRestaurantById(idRestaurant);
		
		while(cursor.moveToNext()){
		
			this.restaurantDescription = cursor.getString(0);
			
		}
		Cursor cursorServiceMenu = this.restaurantLoable.getMenuServiceById(idRestaurant);
		if( cursorServiceMenu != null && cursorServiceMenu.moveToFirst() ){
			while(cursorServiceMenu.moveToNext()){
				Cursor cursorMenu = this.restaurantLoable.getMenuById(cursorServiceMenu.getString(0));
				while(cursorMenu.moveToNext())
				{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("nameMenu", cursor.getString(0));
					map.put("priceMenu", cursor.getString(1));
					map.put("descriptionMenu", cursor.getString(2));
					menu.add(map);
				}
			}
		}
		
		ListAdapter adapter = new SimpleAdapter(context,
				menu, R.layout.show_list_item, new String[] { "nameMenu","priceMenu","descriptionMenu" },
				new int[] { R.id.show_name, R.id.show_duration, R.id.show_schedule });
		
		return adapter;
	}
	
	@Override
	protected void onPostExecute(ListAdapter result) {

		((ListView) view.findViewById(R.id.menurestaudesc)).setAdapter(result);

		
		((TextView) view.findViewById(R.id.descrestaudesc)).setText(this.restaurantDescription);
	
	}

}
