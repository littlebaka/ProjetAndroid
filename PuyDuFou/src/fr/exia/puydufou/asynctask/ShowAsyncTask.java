package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import fr.exia.puydufou.R;
import fr.exia.puydufou.core.ShopLoadable;
import fr.exia.puydufou.core.ShopLoader;
import fr.exia.puydufou.core.ShowLoadable;
import fr.exia.puydufou.core.ShowLoader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowAsyncTask extends AsyncTask<String, String, ListAdapter> {

	
	private List<Map<String, String>> schedule;
	private ListView listView;
	
	private HorizontalScrollView horizontalScrollView;
	//private LinearLayout linearLayout;
	private Context context;
	private Activity view;
	private String idShow;
	
	private List<Map<String, String>> show;
	private String showDate;
	private String showTiming;
	private String showActors;
	private String showDescription;
	private String showEvent;
	private String showSchedule;
	
	private ShowLoadable showLoadable;

	public ShowAsyncTask(Context context,Activity view, String idShow, ListView listView) {
		this.view = view;
		this.idShow = idShow;
		this.listView = listView;
		//this.linearLayout = linearLayout;
		this.context = context;
		
	}

	@Override
	protected ListAdapter doInBackground(String... params) {

		show = new ArrayList<Map<String, String>>();
		this.showLoadable = new ShowLoader(context);
		Cursor cursor = this.showLoadable.getShowById(idShow);
		
		while(cursor.moveToNext()){
			this.showDate = cursor.getString(0);
			this.showActors = cursor.getString(1)+ " acteurs";
			this.showTiming = cursor.getString(2) + " min";
			this.showDescription = cursor.getString(3);
			this.showEvent = cursor.getString(4);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("showSchedule", cursor.getString(1));
		}
		
		ListAdapter adapter = new SimpleAdapter(context,
				show, R.layout.show_list_item, new String[] { "showSchedule" },
				new int[] { R.id.show_name });
		return adapter;
	}
	@Override
	protected void onPostExecute(ListAdapter result) {
	
		
		listView.setAdapter(result);
		
		
		((TextView)view.findViewById(R.id.ddcspecddc)).setText(this.showDate);
		((TextView)view.findViewById(R.id.dureenb)).setText(this.showTiming);
		((TextView)view.findViewById(R.id.actspecnb)).setText(this.showActors);
		((TextView)view.findViewById(R.id.descspecdesc)).setText(this.showDescription);
		((TextView)view.findViewById(R.id.activity_show_eventValue)).setText(this.showEvent);
	}

}
