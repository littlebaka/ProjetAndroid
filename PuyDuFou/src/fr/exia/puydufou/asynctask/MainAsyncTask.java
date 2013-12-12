package fr.exia.puydufou.asynctask;

import fr.exia.puydufou.core.DatasLoadable;
import fr.exia.puydufou.core.DatasLoader;
import fr.exia.puydufou.main.MainActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

public class MainAsyncTask extends AsyncTask<Object, String, Integer> {

	private ProgressDialog progressDialog;
	private MainActivity mainActivity;
	private DatasLoadable datasLoadable;
	
	public MainAsyncTask(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.datasLoadable = new DatasLoader();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mainActivity);
		progressDialog.setMessage("Vérification des données... 0%");
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		progressDialog.setMessage("Vérification des données... "+values[0]+"%");
	}
	
	@Override
	protected Integer doInBackground(Object... params) {
		
		if(isOnline()){
			publishProgress("5");
			try{

			this.datasLoadable.getAndStoreParcInformation(mainActivity.getContentResolver());
			publishProgress("50");
			this.datasLoadable.getAndStoreHoraireInformation(mainActivity.getContentResolver());
			publishProgress("55");
			this.datasLoadable.getAndStoreMenuInformation(mainActivity.getContentResolver());
			publishProgress("60");
			this.datasLoadable.getAndStoreServiceInformation(mainActivity.getContentResolver());
			publishProgress("65");
			this.datasLoadable.getAndStoreServiceMenuInformation(mainActivity.getContentResolver());
			publishProgress("70");
			this.datasLoadable.getAndStoreSpectacleInformation(mainActivity.getContentResolver());
			publishProgress("75");
			this.datasLoadable.getAndStoreSpectacleHoraireInformation(mainActivity.getContentResolver());
			publishProgress("80");
			this.datasLoadable.getAndStoreTypeServiceInformation(mainActivity.getContentResolver());
			publishProgress("85");
			this.datasLoadable.getAndStoreNoteServiceInformation(mainActivity.getContentResolver());
			publishProgress("90");
			this.datasLoadable.getAndStoreNoteSpectacleInformation(mainActivity.getContentResolver());
			publishProgress("95");
			this.datasLoadable.getAndStoreNoteInformation(mainActivity.getContentResolver());
			publishProgress("100");

			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			return 1;
		}
		return 0;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
		
		switch (result) {
		case 1:
			Toast.makeText(mainActivity, "Pas de connection internet.", Toast.LENGTH_LONG).show();
			break;
		default:
			Toast.makeText(mainActivity, "Données vérifiées.", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}
