package com.example.myproj;

//import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;
import android.app.Activity;


public class DisplayAddListActivity extends Activity {
	
	DatabaseHelper db_obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_add_list);
		
		Intent intent = getIntent();
		
		db_obj = new DatabaseHelper(this);
		db_obj.open();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
		System.out.print("Application Paused!");
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		System.out.print("Application Resumed!");
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_display_add_list, container, false);
			return rootView;
		}
	}
	
	public void DisplayList(View view)
	{
		/*Cursor c = db_obj.getAllRows();
		
		String str = "";
		
		if (c.moveToFirst())
		{
			int id = c.getInt(0);
			String desc = c.getString(1);
			String priority = c.getString(2);
			String date = c.getString(3);
			String time = c.getString(4);
			String loc = c.getString(5);
			
			str = str + "id: " + id + ", Description: " + desc
					  + ", Priority: " + priority + ", date: " + date
					  + ", Time: " + time + ", location: " + loc;
			
			 TextView textView = (TextView) findViewById(R.id.text_Display);
		     textView.setText(str);
		}*/
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
		
	}

}
