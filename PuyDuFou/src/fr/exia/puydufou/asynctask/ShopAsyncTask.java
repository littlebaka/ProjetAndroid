package fr.exia.puydufou.asynctask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.exia.puydufou.R;
import fr.exia.puydufou.core.ShopLoadable;
import fr.exia.puydufou.core.ShopLoader;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShopAsyncTask extends AsyncTask<String, String, String> {
	private Context context;
	private Activity view;
	private String idShop;
	
	private ShopLoadable shopLoadable;
	
	private String shopName;
	private String shopDescription;
	private String shopLocation;
	

	public ShopAsyncTask(Context context,Activity view, String idShop){
		this.context = context;
		//this.textView_shopDescription = (TextView) view.findViewById(R.id.descboutdesc);
		this.view = view;
		this.idShop = idShop;
	}
	
	@Override
	protected String doInBackground(String... params) {
		this.shopLoadable = new ShopLoader(context);
		Cursor cursor = this.shopLoadable.getBoutiqueById(this.idShop);
		
		while(cursor.moveToNext()){
			this.shopName = cursor.getString(0);
			this.shopDescription = cursor.getString(1);
			this.shopLocation = cursor.getString(2);
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		
		((TextView) view.findViewById(R.id.descboutdesc)).setText(this.shopDescription);
	//	((TextView) view.findViewById(R.id.activity_shop_title)).setText(this.shopName);
		
		
		//String shopDescription = "L'amour brille sous les étoile AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		//((TextView) view.findViewById(R.id.descboutdesc)).setText(shopDescription);
	}
}
