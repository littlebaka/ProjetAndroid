package fr.exia.puydufou.activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.exia.puydufou.R;
import fr.exia.puydufou.core.MapLoadable;
import fr.exia.puydufou.core.MapLoader;
import fr.exia.puydufou.core.MapWrapperLayout;
import fr.exia.puydufou.core.OnInfoWindowElemenTouchListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity  {
	private ViewGroup infoWindow;
	private MapLoadable mapLoadable;
	private TextView infoTitle;
	private TextView infoSnippet;
	private Button infoButton;
	private Button infoButtonRedirect;
	private OnInfoWindowElemenTouchListener onInfoWindowClickListener;
	private OnInfoWindowElemenTouchListener onInfoWindowClickListener2;
	/** Called when the activity is first created. */
	private Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
		this.mapLoadable = new MapLoader(this);
		
		final MapWrapperLayout mapWrapperLayout = (MapWrapperLayout)findViewById(R.id.map_relative_layout);
		this.infoWindow = (ViewGroup)getLayoutInflater().inflate(R.layout.map_info, null);
		
		
	    GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.activity_map)).getMap();
	    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	    map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(46.8921676,-0.9310186), 15.0f, 39.18f, 0f)));
	    
	    
	    mapWrapperLayout.init(map, getPixelsFromDp(this, 39 + 20)); 

        // We want to reuse the info window for all the markers, 
        // so let's create only one class member instance
        this.infoTitle = (TextView)infoWindow.findViewById(R.id.title);
        this.infoSnippet = (TextView)infoWindow.findViewById(R.id.snippet);
        
        this.infoButton = (Button)infoWindow.findViewById(R.id.button);
        this.infoButtonRedirect = (Button)infoWindow.findViewById(R.id.map_info_showDescription);

        // Setting custom OnTouchListener which deals with the pressed state
        // so it shows up 
        this.onInfoWindowClickListener = new OnInfoWindowElemenTouchListener(infoButton,
                getResources().getDrawable(android.R.drawable.btn_default_small),
                getResources().getDrawable(android.R.drawable.btn_default_small)) 
        {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                // Here we can perform some action triggered after clicking the button
            	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + String.valueOf(marker.getPosition().latitude) + ","
            			+ String.valueOf(marker.getPosition().longitude + "&dirflg=w")));
            	startActivity(intent);
               // Toast.makeText(MapActivity.this, marker.getTitle() + "'s button clicked!", Toast.LENGTH_SHORT).show();
            }
        }; 
        this.onInfoWindowClickListener2 = new OnInfoWindowElemenTouchListener(infoButtonRedirect,
                getResources().getDrawable(android.R.drawable.btn_default_small),
                getResources().getDrawable(android.R.drawable.btn_default_small)) 
        {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                // Here we can perform some action triggered after clicking the button
            	/*Intent intent = new Intent(context, ShowActivity.class);
				intent.putExtra("idShow", infoButtonRedirect.get(+position).get("idShow"));*/
				Toast.makeText(MapActivity.this, marker.getId() + "'s button clicked!", Toast.LENGTH_SHORT).show();
				//context.startActivity(intent);
            }
        }; 
        
        
        this.infoButton.setOnTouchListener(onInfoWindowClickListener);
        this.infoButtonRedirect.setOnTouchListener(onInfoWindowClickListener2);
	    map.setInfoWindowAdapter(new InfoWindowAdapter() {
			
			@Override
			public View getInfoWindow(Marker marker) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public View getInfoContents(Marker marker) {
				infoTitle.setText(marker.getTitle());
				infoSnippet.setText(marker.getSnippet());
				onInfoWindowClickListener.setMarker(marker);
				onInfoWindowClickListener2.setMarker(marker);
				mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);
				
				return infoWindow;
			}
		});
	    
	    Cursor cursor = this.mapLoadable.getLocationSpectacle();
		  
	    while(cursor.moveToNext()){
	    	MarkerOptions marker = new MarkerOptions().position(new LatLng(Float.parseFloat(cursor.getString(2)), Float.parseFloat(cursor.getString(3)))).title(cursor.getString(1));
	    	marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.castle));
	    	marker.snippet(cursor.getString(4));
	    	map.addMarker(marker);
	    	
	    }
	    
	    Cursor cursorBoutiques = this.mapLoadable.getLocationBoutiques();
	    
	    while(cursorBoutiques.moveToNext()){
	    	MarkerOptions marker = new MarkerOptions().position(new LatLng(Float.parseFloat(cursorBoutiques.getString(2)), Float.parseFloat(cursorBoutiques.getString(3)))).title(cursorBoutiques.getString(1));
	    	marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.boutiques));
	    	marker.snippet(cursorBoutiques.getString(4));
	    	map.addMarker(marker);
	    	
	    }
	    
	    Cursor cursorRestaurants = this.mapLoadable.getLocationRestaurants();
	    
	    while(cursorRestaurants.moveToNext()){
	    	MarkerOptions marker = new MarkerOptions().position(new LatLng(Float.parseFloat(cursorRestaurants.getString(2)), Float.parseFloat(cursorRestaurants.getString(3)))).title(cursorRestaurants.getString(1));
	    	marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant));
	    	marker.snippet(cursorRestaurants.getString(4));
	    
	    	map.addMarker(marker);
	    	
	    }
	    
	   
	}
	  public static int getPixelsFromDp(Context context, float dp) {
	        final float scale = context.getResources().getDisplayMetrics().density;
	        return (int)(dp * scale + 0.5f);
	    }

}
