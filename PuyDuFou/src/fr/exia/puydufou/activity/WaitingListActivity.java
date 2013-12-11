package fr.exia.puydufou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.exia.puydufou.R;

public class WaitingListActivity extends Activity{
	
	private String[] ItemsList;
	private ListView myList;
	private final String EXTRA_TITLE = "title_show";
	private final String EXTRA_INFO = "info_show";
	private final String EXTRA_EVEN = "event_hist";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting_list);
		ItemsList = getResources().getStringArray(R.array.items);
		myList = (ListView) findViewById(R.id.listView_waitList);
		myList.setAdapter(new ArrayAdapter<String>(this, R.layout.list_wait_list, ItemsList));
		myList.setOnItemClickListener(new ItemClickListener());
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private void setupActionBar() 
	{
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wait_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class ItemClickListener implements ListView.OnItemClickListener {
    	@Override
    	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
    		String clickedItem = (String) adapter.getAdapter().getItem(pos);
    		Intent intent_wait = new Intent(WaitingListActivity.this, WaitingActivity.class);
    		intent_wait.putExtra(EXTRA_TITLE, clickedItem.toString());
    		intent_wait.putExtra(EXTRA_INFO, "Information Spectacle");
    		intent_wait.putExtra(EXTRA_EVEN, "Evenment lié à ce superbe spectacle");
			startActivity(intent_wait);
    	}
    }
}
