package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.ShopActivity;
import fr.exia.puydufou.core.ShopLoadable;
import fr.exia.puydufou.core.ShopLoader;

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
import android.widget.Toast;

public class ShopsListAsyncTask extends AsyncTask<String, String, ListAdapter> {

	private List<Map<String, String>> shop;
	private ListView listView;
	private Context context;
	
	private ShopLoadable shopLoadable;

	public ShopsListAsyncTask(Context context, ListView listView){
			this.listView = listView;
			this.context = context;
	}
	
	@Override
	protected ListAdapter doInBackground(String... params) {
		
		shop = new ArrayList<Map<String, String>>();
		this.shopLoadable = new ShopLoader(context);
		Cursor cursor = this.shopLoadable.getAllBoutiques();
		
		while(cursor.moveToNext()){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("idShop", cursor.getString(0));
			map.put("titleShop", cursor.getString(1));
			shop.add(map);
		}
		
		ListAdapter adapter = new SimpleAdapter(context,
				shop, R.layout.show_list_item, new String[] { "titleShop" },
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
				
				Intent intent = new Intent(context, ShopActivity.class);
				intent.putExtra("idShop", shop.get(+position).get("idShop"));
				context.startActivity(intent);
				
			}
		});

	}

}
