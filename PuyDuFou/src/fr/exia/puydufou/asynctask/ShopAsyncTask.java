package fr.exia.puydufou.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class ShopAsyncTask extends AsyncTask<String, String, String> {
	private Context context;
	private TextView textView_shopDescription;
	

	public ShopAsyncTask(Context context,TextView textView_shopDescription){
		this.context = context;
		this.textView_shopDescription = textView_shopDescription;
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		String shopDescription = "L'amour brille sous les étoile AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		textView_shopDescription.setText(shopDescription);
		super.onPostExecute(result);
	}
}
