package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.RestaurantActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RestaurantsListAsyncTask extends
		AsyncTask<String, String, String> {
	private List<Map<String, String>> restaurants;
	private ListView listView;
	private Context context;

	public RestaurantsListAsyncTask(Context context, ListView listView){
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		restaurants = new ArrayList<Map<String, String>>();
		
		String titleRestaurants= "La fourchette";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("titleRestaurants", titleRestaurants);
		titleRestaurants = "Le caca mou";
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("titleRestaurants", titleRestaurants);
		
		restaurants.add(map);
		restaurants.add(map2);
		
		ListAdapter adapter = new SimpleAdapter(context,
				restaurants, R.layout.show_list_item, new String[] { "titleRestaurants" },
				new int[] { R.id.show_name });
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				restaurants.get(+position).get("titleRestaurants");
				Intent intent = new Intent(context, RestaurantActivity.class);
				context.startActivity(intent);
				
			}
		});

	}

}
