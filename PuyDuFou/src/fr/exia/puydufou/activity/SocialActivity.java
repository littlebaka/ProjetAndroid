package fr.exia.puydufou.activity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import fr.exia.puydufou.R;

public class SocialActivity extends Activity {

	protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
	Camera camera;
	SurfaceHolder surface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social);
		Button button_photo = (Button) findViewById(R.id.button_photo);
		button_photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//define the file-name to save photo taken by Camera activity
				String fileName = "new-photo-name.jpg";
				//create parameters for Intent with filename
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.TITLE, fileName);
				values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
				//imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
				Uri imageUri = getContentResolver().insert(
				        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				//create new Intent
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		});
		
		Button button = (Button) findViewById(R.id.button_share_social);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_share = new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                intent_share.putExtra(Intent.EXTRA_SUBJECT, "Puy du Fou");
                intent_share.putExtra(Intent.EXTRA_TEXT, "Ce parc d'atraction est vraiment super ! Les spectacles sont les mieux que j'ai jamais vus ! Ce passage au Puy du Fou resteras à jamais dans ma mémoire.");
                startActivity(Intent.createChooser(intent_share, "Partager avec..."));
			}
		});
		
		Button button_shareimg = (Button) findViewById(R.id.button_shareimg);
		button_shareimg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				File pngDir = new   File(Environment.getExternalStorageDirectory(),"Android/data/Textures");
				   if (!pngDir.exists()) {
				pngDir.mkdirs();
				}
				File pngfile = new File(pngDir,"storage/sdcard0/Download/world-of-warcraft-warlords-of-draenor-pc-1383945461-044.jpg");
				Uri pngUri = Uri.fromFile(pngfile);
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("image/png");
				shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				                "Text to Share");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
				        "Context to share");
				shareIntent.putExtra(android.content.Intent.EXTRA_STREAM,
				                pngUri); //Share the image on Facebook
				PackageManager pm = getApplicationContext().getPackageManager();
				List<ResolveInfo> activityList = pm.queryIntentActivities(
				                    shareIntent, 0);
				for (final ResolveInfo app : activityList) {
				    if ((app.activityInfo.name).contains("facebook")) {
				        final ActivityInfo activity = app.activityInfo;
				        final ComponentName name = new ComponentName(
				                    activity.applicationInfo.packageName,
				                    activity.name);
				        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				        shareIntent.setComponent(name);
				        startActivity(shareIntent);
				        break;
				            }
				        }
			}
		});
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
		    if (resultCode == RESULT_OK) {
		        //use imageUri here to access the image
		 
		    } else if (resultCode == RESULT_CANCELED) {
		        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
		    } else {
		        Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
		    }
		}
		}
	
	public static File convertImageUriToFile (Uri imageUri, Activity activity)  {
		Cursor cursor = null;
		try {
		    String [] proj={MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.ImageColumns.ORIENTATION};
		    cursor = activity.managedQuery( imageUri,
		            proj, // Which columns to return
		            null,       // WHERE clause; which rows to return (all rows)
		            null,       // WHERE clause selection arguments (none)
		            null); // Order-by clause (ascending by name)
		    int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		    int orientation_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION);
		    if (cursor.moveToFirst()) {
		        String orientation =  cursor.getString(orientation_ColumnIndex);
		        return new File(cursor.getString(file_ColumnIndex));
		    }
		    return null;
		} finally {
		    if (cursor != null) {
		        cursor.close();
		    }
		}
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
		getMenuInflater().inflate(R.menu.social, menu);
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

}
