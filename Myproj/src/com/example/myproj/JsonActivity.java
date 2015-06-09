package com.example.myproj;

import org.json.JSONException;
import org.json.JSONObject;

//import com.example.myorganizer.JsonActivity;
//import com.example.myorganizer.R;
//import com.example.myorganizer.ServiceHandler;
//import com.example.myorganizer.JsonActivity.GetJson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JsonActivity extends Activity {
	
private static String url ="http://echo.jsontest.com/reminder/do_something/priority/high";
	
    private static final String TAG_REMINDER = "reminder";
    private static final String TAG_PRIORITY = "priority";
    String jsonStr="";
    String reminder_str="";
    String priority_str="";
    
    private ProgressDialog _pDialog;
    
    JSONObject _reminders = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		
		new GetJson().execute();
	}
	
	private class GetJson extends AsyncTask<Void, Void, Void> 
	{
		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            _pDialog = new ProgressDialog(JsonActivity.this);
	            _pDialog.setMessage("Please wait while data is being loaded...");
	            _pDialog.setCancelable(false);
	            _pDialog.show();
	 
	        }
	 
	        @Override
	        protected Void doInBackground(Void... arg0) {
	    		ServiceHandler s_handler = new ServiceHandler();
	    		
	    		jsonStr = s_handler.makeServiceCall(url, ServiceHandler.GET);
	    		Log.d("Response: ", "> " + jsonStr);
	    		
	    		if (jsonStr != null) 
	    		{
	                try {
	                    _reminders = new JSONObject(jsonStr);
	                     
	                    // Getting JSON Array node
	                    //contacts = jsonObj.getJSONArray(TAG_CONTACTS);
	                    reminder_str = _reminders.getString(TAG_REMINDER);
	                    priority_str = _reminders.getString(TAG_PRIORITY);
	                    
	                    Log.d("reminder_str",reminder_str);

	                    
	                    }
	                 catch (JSONException e) 
	                 {
	                    e.printStackTrace();
	                 }
	            } 
	    		else 
	    		{
	                Log.e("ServiceHandler", "Couldn't get any data from the url");
	            }
	            
	 
	            return null;
	        }
	 
	        @Override
	        protected void onPostExecute(Void result) {
	            super.onPostExecute(result);
	            if (_pDialog.isShowing())
	            {
	                _pDialog.dismiss();
	            }
	            
                TextView tv1 = (TextView)findViewById(R.id.tableCol1);
                tv1.setText(TAG_REMINDER);
                
                TextView tv2 = (TextView)findViewById(R.id.tableCol2);
                tv2.setText(reminder_str);
                
                TextView tv3 = (TextView)findViewById(R.id.tableCol3);
                tv3.setText(TAG_PRIORITY);
                
                TextView tv4 = (TextView)findViewById(R.id.tableCol4);
                tv4.setText(priority_str);
	        }
	 
	    
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
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
}
