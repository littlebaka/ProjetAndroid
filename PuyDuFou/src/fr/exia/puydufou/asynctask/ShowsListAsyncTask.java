package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.ShopActivity;
import fr.exia.puydufou.activity.ShowActivity;
import fr.exia.puydufou.core.ShowLoadable;
import fr.exia.puydufou.core.ShowLoader;

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

public class ShowsListAsyncTask extends AsyncTask<String, String, ListAdapter> {
	private List<Map<String, String>> shows;
	private ListView listView;
	private Context context;

	private ShowLoadable showLoable;
	
	public ShowsListAsyncTask(Context context, ListView listView){
		this.listView = listView;
		this.context = context;
	}

	@Override
	protected ListAdapter doInBackground(String... params) {
		
		shows = new ArrayList<Map<String, String>>();
		this.showLoable = new ShowLoader(context);
		Cursor cursor = this.showLoable.getAllShow();
		
		while(cursor.moveToNext()){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("idShow", cursor.getString(0));
			map.put("titleShow", cursor.getString(1));
			shows.add(map);
		}
		ListAdapter adapter = new SimpleAdapter(context,
				shows, R.layout.show_list_item, new String[] { "titleShow" },
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
				
				Intent intent = new Intent(context, ShowActivity.class);
				intent.putExtra("idShow", shows.get(+position).get("idShow"));
				context.startActivity(intent);
			}
		});

	}
}
