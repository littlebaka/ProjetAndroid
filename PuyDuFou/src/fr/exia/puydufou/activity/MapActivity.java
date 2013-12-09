package fr.exia.puydufou.activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import fr.exia.puydufou.R;
import android.app.Activity;
import android.os.Bundle;

public class MapActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
	    
	    GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.activity_map)).getMap();
	    
	    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	    map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(46.8921676,-0.9310186), 15.0f, 39.18f, 0f)));
	    
	}

}
