package com.example.myproj;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class UpdateList extends ActionBarActivity {
	
	DatabaseHelper db_obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_list);
		
		db_obj = new DatabaseHelper(this);
		db_obj.open();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_list, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_update_list,
					container, false);
			return rootView;
		}
	}
	
	public void GetOldValues(View view)
	{
		Intent intent = getIntent();
		long id_to = intent.getLongExtra("ID", 0);
		
		Cursor c = db_obj.getRow(id_to);
		
		if (c.moveToFirst()) {
			long set_idDB = c.getLong(db_obj.COL_ID);
			String desc = c.getString(db_obj.COL_Description);
			String priority = c.getString(db_obj.COL_Priority);
			String date = c.getString(db_obj.COL_Date);
			String time = c.getString(db_obj.COL_Time);
			String location = c.getString(db_obj.COL_Location);

			//location += "!";
			
			EditText _desc = (EditText) findViewById(R.id.edit_desc);
			_desc.setText(desc);
			
			EditText _priority = (EditText) findViewById(R.id.edit_pri);
			_priority.setText(priority);
			
			EditText _date = (EditText) findViewById(R.id.edit_date);
			_date.setText(date);
			
			EditText _time = (EditText) findViewById(R.id.edit_time);
			_time.setText(time);
			
			EditText _location = (EditText) findViewById(R.id.edit_location);
			_location.setText(location);
			
			//db_obj.updateRow(set_idDB, desc, priority, date,time,location);
		
		}
		c.close();
	}
	
	public void updateRow(View view)
	{
		Intent intent = getIntent();
		long id_to = intent.getLongExtra("ID", 0);
		
		Cursor c = db_obj.getRow(id_to);
		
		if (c.moveToFirst()) {
			long set_idDB = c.getLong(db_obj.COL_ID);
			String desc = c.getString(db_obj.COL_Description);
			String priority = c.getString(db_obj.COL_Priority);
			String date = c.getString(db_obj.COL_Date);
			String time = c.getString(db_obj.COL_Time);
			String location = c.getString(db_obj.COL_Location);

			//location += "!";
			
			EditText _desc = (EditText) findViewById(R.id.edit_desc);
			desc = _desc.getText().toString();
			
			EditText _priority = (EditText) findViewById(R.id.edit_pri);
			priority = _priority.getText().toString();
			
			EditText _date = (EditText) findViewById(R.id.edit_date);
			date = _date.getText().toString();
			
			EditText _time = (EditText) findViewById(R.id.edit_time);
			time = _time.getText().toString();
			
			EditText _location = (EditText) findViewById(R.id.edit_location);
			location = _location.getText().toString();
			
			db_obj.updateRow(set_idDB, desc, priority, date,time,location);
		
		}
		c.close();
//		populate_ListView();
		System.out.print(id_to);
	}

}
