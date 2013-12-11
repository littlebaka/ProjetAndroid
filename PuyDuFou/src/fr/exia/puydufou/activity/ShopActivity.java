package fr.exia.puydufou.activity;

import fr.exia.puydufou.R;
import fr.exia.puydufou.asynctask.ShopAsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ShopActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		
		ShopAsyncTask shopAsyncTask = new ShopAsyncTask(this,(TextView) findViewById(R.id.descboutdesc));


		shopAsyncTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop, menu);
		return true;
	}

}
