package fr.exia.puydufou.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.exia.puydufou.R;
import fr.exia.puydufou.activity.MapActivity;
import fr.exia.puydufou.activity.PlanningActivity;
import fr.exia.puydufou.activity.RestaurantsListActivity;
import fr.exia.puydufou.activity.ShopsListActivity;
import fr.exia.puydufou.activity.ShowsListActivity;
import fr.exia.puydufou.activity.SocialActivity;
import fr.exia.puydufou.asynctask.MainAsyncTask;

public class MainActivity extends Activity {

	private Button button_programme;
	private Button button_boutique;
	private Button button_planning;
	private Button button_restaurant;
	private Button button_map;
	private Button button_social;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.button_programme = (Button)findViewById(R.id.button_programme);
        this.button_programme.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_social = new Intent(MainActivity.this, ShowsListActivity.class);
				startActivity(intent_social);
			}
		});
        
        this.button_boutique = (Button)findViewById(R.id.button_boutique);
        this.button_boutique.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_social = new Intent(MainActivity.this, ShopsListActivity.class);
				startActivity(intent_social);
			}
		});
        
        this.button_planning = (Button)findViewById(R.id.button_planning);
        this.button_planning.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_social = new Intent(MainActivity.this, PlanningActivity.class);
				startActivity(intent_social);
			}
		});
        
        this.button_restaurant = (Button)findViewById(R.id.button_restaurant);
        this.button_restaurant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_social = new Intent(MainActivity.this, RestaurantsListActivity.class);
				startActivity(intent_social);
			}
		});
        
        this.button_map = (Button)findViewById(R.id.button_map);
        this.button_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_map = new Intent(MainActivity.this, MapActivity.class);
				startActivity(intent_map);
			}
		});
        
        this.button_social = (Button)findViewById(R.id.button_social);
        this.button_social.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_social = new Intent(MainActivity.this, SocialActivity.class);
				startActivity(intent_social);
			}
		});
        
        MainAsyncTask mainAsyncTask = new MainAsyncTask(MainActivity.this);
        mainAsyncTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }
    
}
