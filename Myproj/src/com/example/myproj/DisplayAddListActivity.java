package com.example.myproj;

//import com.example.myorganizer.DatabaseHelper;


//import com.example.myorganizer.DisplayListActivity;

//import com.example.myorganizer.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayAddListActivity extends Activity {
	
	DatabaseHelper db_obj;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_add_list);
		
		Intent intent = getIntent();
		
		db_obj = new DatabaseHelper(this);
		db_obj.open();
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		db_obj.close();
	
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		Context context = getApplicationContext();
		CharSequence text = "Application Paused!";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_add_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void DisplayList(View view)
	{
		Intent intent = new Intent(this, DisplayListActivity.class);
    	startActivity(intent);
    	
	}
	public void AddListItem(View view)
	{
		EditText desc = (EditText) findViewById(R.id.editText_description);
		String desc_str = desc.getText().toString();
		
		EditText priority = (EditText) findViewById(R.id.edittext_priority);
		String priority_str = priority.getText().toString();
		
		EditText date = (EditText) findViewById(R.id.editText_date);
		String date_str = date.getText().toString();
		
		EditText time = (EditText) findViewById(R.id.editText_time);
		String time_str = time.getText().toString();
		
		EditText location = (EditText) findViewById(R.id.editText_location);
		String location_str = location.getText().toString();
		
		long id = db_obj.insertRow(desc_str, priority_str, date_str, time_str, location_str);
		
		Intent intent = new Intent(this, DisplayListActivity.class);
    	startActivity(intent);
		
	}
	  public void captureFromCamera(View v){
	    	dispatchTakePictureIntent() ;
	    }
	  String mCurrentPhotoPath;
		String mCurrentPhotoPathTemp;
		private File createImageFile() throws IOException {
			
		    // Create an image file name
		    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		    String imageFileName = "JPEG_" + timeStamp + "_";
		    
		    /*
		    File storageDir = Environment.getExternalStoragePublicDirectory(
		            Environment.DIRECTORY_PICTURES);
		    File image = File.createTempFile(
		        imageFileName,  /* prefix */
		    //    ".jpg",         /* suffix */
		    //    storageDir      /* directory */
		    //);
		    
		    File imagesFolder = new File(Environment.getExternalStorageDirectory(), "My Organizer");
		    imagesFolder.mkdirs();
		    
		    //File image = new File(imagesFolder, "photo.png");
		    File image = new File(imagesFolder,timeStamp + ".png");
		    // Save a file: path for use with ACTION_VIEW intents
		    mCurrentPhotoPathTemp=image.getAbsolutePath();
		    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		    

		    return image;
		}
		static final int REQUEST_TAKE_PHOTO = 2;

		private void dispatchTakePictureIntent() {
		    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    // Ensure that there's a camera activity to handle the intent
		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		        // Create the File where the photo should go
		        File photoFile = null;
		        try {
		            photoFile = createImageFile();
		        } catch (IOException ex) {
		            // Error occurred while creating the File
		        }
		        // Continue only if the File was successfully created
		        if (photoFile != null) {
		        	Log.d("ImagePath", Uri.fromFile(photoFile).toString());
		            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
		                    Uri.fromFile(photoFile));
		            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
		        }
		    }
		}
}
