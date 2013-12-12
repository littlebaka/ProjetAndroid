package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.RestaurantActivity;
import fr.exia.puydufou.activity.ShopActivity;
import fr.exia.puydufou.core.RestaurantLoable;
import fr.exia.puydufou.core.RestaurantLoader;
import fr.exia.puydufou.core.ShopLoadable;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RestaurantsListAsyncTask extends
		AsyncTask<String, String, ListAdapter> {
	private List<Map<String, String>> restaurants;
	private ListView listView;
	private Context context;
	private RestaurantLoable restaurantLoadable;

	public RestaurantsListAsyncTask(Context context, ListView listView){
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	protected ListAdapter doInBackground(String... params) {
		restaurants = new ArrayList<Map<String, String>>();
		this.restaurantLoadable = new RestaurantLoader(context);
		Cursor cursor = this.restaurantLoadable.getAllRestaurant();
		
		while(cursor.moveToNext()){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("idRestaurant", cursor.getString(0));
			map.put("titleRestaurant", cursor.getString(1));
			restaurants.add(map);
		}
		ListAdapter adapter = new SimpleAdapter(context,
				restaurants, R.layout.show_list_item, new String[] { "titleRestaurant" },
				new int[] { R.id.show_name });
		return adapter;
	}
	
	@Override
	protected void onPostExecute(ListAdapter result) {
		listView.setAdapter(result);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent intent = new Intent(context, RestaurantActivity.class);
				intent.putExtra("idRestaurant", restaurants.get(+position).get("idRestaurant"));
				context.startActivity(intent);
				
			}
		});

	}

}
