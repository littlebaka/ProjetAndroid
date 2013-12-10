package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import fr.exia.puydufou.R;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowAsyncTask extends AsyncTask<String, String, String> {
	private TextView textViewDate;
	private TextView textViewActors;
	private TextView textViewTiming;
	private TextView textViewDescription;
	private TextView show_schedule_textView;
	private List<String> schedule;
	private ListView listView;
	private HorizontalScrollView horizontalScrollView;
	//private LinearLayout linearLayout;
	private Context context;

	public ShowAsyncTask(Context context,TextView textViewDate,TextView textViewActors, TextView textViewTiming, TextView textViewDescription, HorizontalScrollView horizontalScrollView,LinearLayout linearLayout, TextView show_schedule_textView) {
		this.textViewDate = textViewDate;
		this.textViewActors = textViewActors;
		this.textViewTiming = textViewTiming;
		this.textViewDescription = textViewDescription;
		this.horizontalScrollView =horizontalScrollView;
		this.show_schedule_textView = show_schedule_textView;
		//this.linearLayout = linearLayout;
		this.context = context;
		
	}

	@Override
	protected String doInBackground(String... params) {

		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		schedule = new ArrayList<String>();
		schedule.add("10h30");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
		schedule.add("10h50");
	
		
		LinearLayout linearLayout = new LinearLayout(context);
		
		for (String s : schedule) {
			TextView textView = new TextView(context);
			textView.setText(s);
			linearLayout.addView(textView);

		}
		horizontalScrollView.addView(linearLayout);
		String showDate = "15/20/2013";
		String showActors = "12 personnes";
		String showTiming = "60min";
		String showDescription = "BHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGDBHDFGUDSKFDHFUISDHFUDBNVGEDISJNKDSGYRUEJBTRVGCERVTEYCBEKDUSVGIJERDFBDFFGD";
		textViewDate.setText(showDate);
		textViewActors.setText(showActors);
		textViewTiming.setText(showTiming);
		textViewDescription.setText(showDescription);

		super.onPostExecute(result);
	}

}
