package com.example.myproj;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v4.widget.SwipeRefreshLayout;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;


//import com.example.myorganizer.JsonActivity;
//import com.example.myorganizer.DisplayAddListActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.splunk.mint.Mint;
public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener  {
	SwipeRefreshLayout srl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mint.initAndStartSession(MainActivity.this, "7cb0000c");
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
    public void opencrashfunc(View v){
    	int a;
    	a=0/0;
    	/*
    	try{
    	int a;
    	a=0/0;}
    	catch(Exception e){
    		Log.e("Exception: ",e.getMessage());
    		Intent intent = new Intent(Intent.ACTION_MAIN);
    		intent.addCategory(Intent.CATEGORY_HOME);
    		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity(intent);
    	} */
    }
    public void displayAd(View v){
    	Intent i=new Intent(this,AdActivity.class);
    	startActivity(i);
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


	@Override
	public void onRefresh() {
		srl.setRefreshing(true);
		//anything you need to update on refresh comes here
		srl.setRefreshing(false);
		
	}
	public void add_list_start_activity(View view)
    {
    	Intent intent = new Intent(this, DisplayAddListActivity.class);
    	startActivity(intent);
    }
	public void parseJson(View view)
    {
    	Intent I = new Intent(this,JsonActivity.class);
    	startActivity(I);
    }
    public void login_facebook(View view)
    {
    	Intent I = new Intent(this,FacebookActivity.class);
    	startActivity(I);
    }
}
