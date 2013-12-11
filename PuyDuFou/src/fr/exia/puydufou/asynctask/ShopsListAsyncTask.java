package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.ShopActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShopsListAsyncTask extends AsyncTask<String, String, String> {
	 private final String EXTRA_TITLE = "title_show";
	 private final String EXTRA_INFO = "info_show";
	 private final String EXTRA_EVEN = "event_hist";
	private List<Map<String, String>> shop;
	private ListView listView;
	private Context context;

	public ShopsListAsyncTask(Context context, ListView listView){
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
		shop = new ArrayList<Map<String, String>>();
		
		String titleShop= "La croute";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("titleShop", titleShop);
		titleShop = "L'amour est né";
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("titleShop", titleShop);
		
		shop.add(map);
		shop.add(map2);
		
		ListAdapter adapter = new SimpleAdapter(context,
				shop, R.layout.show_list_item, new String[] { "titleShop" },
				new int[] { R.id.show_name });
		
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				shop.get(+position).get("titleShow");
//				String clickedItem = (String) parent.getAdapter().getItem(position);
			Intent intent = new Intent(context, ShopActivity.class);
//			      intent.putExtra(EXTRA_TITLE, clickedItem.toString());


				context.startActivity(intent);
				
			}
		});

	}

}
