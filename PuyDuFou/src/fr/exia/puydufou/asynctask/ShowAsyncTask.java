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

public class ShowAsyncTask extends AsyncTask<String, String, String> {

	
	private List<String> schedule;
	private ListView listView;
	
	private HorizontalScrollView horizontalScrollView;
	//private LinearLayout linearLayout;
	private Context context;
	private Activity view;
	private String idShow;
	

	private String showDate;
	private String showSchedule;
	private String showActors;
	private String showDescription;
	private String showEvent;
	
	private ShowLoadable showLoadable;

	public ShowAsyncTask(Context context,Activity view, String idShow, ListView listView) {
		this.view = view;
		this.idShow = idShow;
		this.listView = listView;
		//this.linearLayout = linearLayout;
		this.context = context;
		
	}

	@Override
	protected String doInBackground(String... params) {

		this.showLoadable = new ShowLoader(context);
		Cursor cursor = this.showLoadable.getShowById(idShow);
		
		while(cursor.moveToNext()){
			this.showDate = cursor.getString(0);
			this.showActors = cursor.getString(1)+ " acteurs";
			this.showSchedule = cursor.getString(2) + " min";
			this.showDescription = cursor.getString(3);
			this.showEvent = cursor.getString(4);
		}
		
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
//		schedule = new ArrayList<String>();
//		schedule.add("10h30");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//		schedule.add("10h50");
//	
//		
//		LinearLayout linearLayout = new LinearLayout(context);
//		
//		for (String s : schedule) {
//			TextView textView = new TextView(context);
//			textView.setText(s);
//			linearLayout.addView(textView);
//
//		}
	//	horizontalScrollView.addView(linearLayout);
		((TextView)view.findViewById(R.id.ddcspecddc)).setText(this.showDate);
		((TextView)view.findViewById(R.id.dureenb)).setText(this.showSchedule);
		((TextView)view.findViewById(R.id.actspecnb)).setText(this.showActors);
		((TextView)view.findViewById(R.id.descspecdesc)).setText(this.showDescription);
		((TextView)view.findViewById(R.id.activity_show_eventValue)).setText(this.showEvent);
	}

}
