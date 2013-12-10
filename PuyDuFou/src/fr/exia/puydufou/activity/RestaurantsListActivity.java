package fr.exia.puydufou.activity;

import fr.exia.puydufou.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class RestaurantsListActivity extends Activity {
	
	private String[] ItemsList;
	private ListView myList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_list);
		// Show the Up button in the action bar.
		ItemsList = getResources().getStringArray(R.array.items);
		myList = (ListView) findViewById(R.id.listerestau);
		myList.setAdapter(new ArrayAdapter<String>(this, R.layout.listrestaurant, ItemsList));
		myList.setOnItemClickListener(new ItemClickListener());
		
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurants_list, menu);
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
    		
    		//String clickedItem = (String) adapter.getAdapter().getItem(pos);
    		Intent intent_restau = new Intent(RestaurantsListActivity.this, RestaurantActivity.class);
			startActivity(intent_restau);
    	}
    }

}
