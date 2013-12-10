package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.ShowActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShowsListAsyncTask extends AsyncTask<String, String, String> {
	private List<Map<String, String>> shows;
	private ListView listView;
	private Context context;

	public ShowsListAsyncTask(Context context, ListView listView){
		this.listView = listView;
		this.context = context;
		
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		shows = new ArrayList<Map<String, String>>();
		
		String titleShow= "La tortue";
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("titleShow", titleShow);
		titleShow = "Coco";
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("titleShow", titleShow);
		
		shows.add(map);
		shows.add(map2);
		
		ListAdapter adapter = new SimpleAdapter(context,
				shows, R.layout.show_list_item, new String[] { "titleShow" },
				new int[] { R.id.show_name });
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

		
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				shows.get(+position).get("titleShow");
				Intent intent = new Intent(context, ShowActivity.class);
				context.startActivity(intent);

				
			}
		});

	}
}
