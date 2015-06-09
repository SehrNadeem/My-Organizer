package com.example.myproj;

//import com.example.myorganizer.DatabaseHelper;


//import com.example.myorganizer.DisplayListActivity;

//import com.example.myorganizer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
		
	}
}
