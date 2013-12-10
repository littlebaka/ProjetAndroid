package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RestaurantAsyncTask extends AsyncTask<String, String, String> {
	private List<Map<String, String>> menu;
	private ListView listView;
	private Context context;

	public RestaurantAsyncTask(Context context,ListView listView){
		this.context = context;
		this.listView = listView;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		menu = new ArrayList<Map<String, String>>();
		
		String menuRestaurant= "Menu enfant";
		String menuPrice = "50 euros";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("menuRestaurant", menuRestaurant);
		map.put("menuPrice", menuPrice);
		
		menuRestaurant = "Menu Adulte";
		menuPrice = "60 euros";
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("menuRestaurant", menuRestaurant);
		map2.put("menuPrice", menuPrice);
		
		menu.add(map);
		menu.add(map2);
		
		ListAdapter adapter = new SimpleAdapter(context,
				menu, R.layout.show_list_item, new String[] { "menuRestaurant", "menuPrice" },
				new int[] { R.id.show_name });
		
		listView.setAdapter(adapter);

		super.onPostExecute(result);
	}

}
