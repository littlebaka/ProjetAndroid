package fr.exia.puydufou.activity;

import fr.exia.puydufou.R;
import fr.exia.puydufou.asynctask.ShowAsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		ShowAsyncTask showAsyncTask = new ShowAsyncTask(this,(TextView) findViewById(R.id.ddcspecddc),(TextView) findViewById(R.id.actspecnb),(TextView) findViewById(R.id.dureenb),(TextView) findViewById(R.id.descspecdesc),(HorizontalScrollView) findViewById(R.id.horairespecdesc),(LinearLayout) findViewById(R.id.show_schedule_linearLayout) ,(TextView) findViewById(R.id.show_schedule_textView));
		showAsyncTask.execute();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}

}
