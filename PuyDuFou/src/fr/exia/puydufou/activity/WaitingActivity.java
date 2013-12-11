package fr.exia.puydufou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import fr.exia.puydufou.R;

public class WaitingActivity extends Activity{
	
	private final String EXTRA_TITLE = "title_show";
	private final String EXTRA_INFO = "info_show";
	private final String EXTRA_EVEN = "event_hist";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);
		
		Intent intent = getIntent();
		TextView title = (TextView) findViewById(R.id.textView_waiting_titleShow);
		TextView info = (TextView) findViewById(R.id.textView_waiting_text_infoShow);
		TextView even_hist = (TextView) findViewById(R.id.textView_waiting_text_evenHist);
		
		if(intent != null)
		{
			title.setText(intent.getStringExtra(EXTRA_TITLE));
			info.setText(intent.getStringExtra(EXTRA_INFO));
			even_hist.setText(intent.getStringExtra(EXTRA_EVEN));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wait, menu);
		return true;
	}

}
