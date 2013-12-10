package fr.exia.puydufou.activity;

import fr.exia.puydufou.R;
import fr.exia.puydufou.asynctask.RestaurantAsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class RestaurantActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		
		RestaurantAsyncTask restaurantAsyncTask = new RestaurantAsyncTask(this,(ListView) findViewById(R.id.menurestaudesc));
		restaurantAsyncTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant, menu);
		return true;
	}

}
