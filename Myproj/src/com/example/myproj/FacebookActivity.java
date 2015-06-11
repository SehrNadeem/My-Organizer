package com.example.myproj;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacebookActivity extends Activity {
	
	private static SocialAuthAdapter adapter;
	private static EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook);
		
		adapter = new SocialAuthAdapter(new ResponseListener());
		
		Button facebook_button = (Button)findViewById(R.id.button1);
	    facebook_button.setBackgroundResource(R.drawable.facebook);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.facebook, menu);
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
	
	public void open(View view)
	{
		try {
			adapter.addConfig(Provider.FACEBOOK, "845133692242764","c2e3c197fd5574452bd7529fbe9c9683", "publish_actions");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        adapter.authorize(FacebookActivity.this, Provider.FACEBOOK);
        adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
	}
	
	public void post(View view)
	{
		edit = (EditText) findViewById(R.id.edit);
		adapter.updateStatus(edit.getText().toString(), new MessageListener(), false);
	}
	public void logout(View view)
	{
		if (adapter!=null)
		{
			try
			{
				adapter.signOut(this,Provider.FACEBOOK.toString());
			}
			catch (Exception e){
				// EMPTY
			}
		}
	}
	public static SocialAuthAdapter getSocialAuthAdapter() {
		return adapter;
	}
	private final class ResponseListener implements DialogListener {

		public void onComplete(Bundle values) {
			Log.d("Custom-UI", "Successful");
			edit = (EditText) findViewById(R.id.edit);
			edit.setText("DONE");
		    //adapter.updateStatus(edit.getText().toString(), new MessageListener(),true);                   
		   }

		   public void onError(SocialAuthError error) {
		     Log.d("Custom-UI" , "Error");
		   }

		   public void onCancel() {
		     Log.d("Custom-UI" , "Cancelled");
		   }

		@Override
		public void onBack() {
			// TODO Auto-generated method stub
			
		}

	}
	private final class MessageListener implements SocialAuthListener<Integer> {
	    @Override
	    public void onExecute(String provider,Integer t) {
	    	//super.onExecute();

	    Integer status = t;
	    if (status.intValue() == 200 || status.intValue() == 201 ||status.intValue() == 204)
	     Toast.makeText(FacebookActivity.this, "Message posted",Toast.LENGTH_LONG).show();
	    else
	    Toast.makeText(FacebookActivity.this, "Message not posted",Toast.LENGTH_LONG).show();
	   }

	   @Override
	   public void onError(SocialAuthError e) {
	   }

	}
	
	public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
	
}
