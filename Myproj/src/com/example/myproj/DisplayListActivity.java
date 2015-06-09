package com.example.myproj;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

public class DisplayListActivity extends ActionBarActivity {
	
	DatabaseHelper db_obj;
	SwipeRefreshLayout swipeContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_list);
		//Intent intent = getIntent();
		
		db_obj = new DatabaseHelper(this);
		db_obj.open();
		
		//add code here
		//setListView();
		//populate_ListView();
		
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
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_list, menu);
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
				Bundle savedInstanceState) 
		{
			View rootView = inflater.inflate(R.layout.fragment_display_list,
					container, false);
			
			//populate_ListView();
			//return rootView;
			
			RelativeLayout ll = (RelativeLayout)inflater.inflate(R.layout.fragment_display_list, container, false);
            //some code
            return ll;
		}
	}
	
	public void populate_ListView()
	{
		Cursor c = db_obj.getAllRows();
		String[] DB_columns = new String[] {DatabaseHelper.Description,DatabaseHelper.Priority,DatabaseHelper.Date,DatabaseHelper.Time,DatabaseHelper.Location};
		int[] LV_id = new int[] {R.id.textView_desc,R.id.textView_priority,R.id.textView_date,R.id.textView_time,R.id.textView_location};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
				R.layout.display_list_xml,
				c ,DB_columns,LV_id,0);
		
		
		ListView LV = (ListView)findViewById(R.id.listViewDisplayList);
		LV.setAdapter(adapter);
		
		registerListClickCallback();
	}
	
	public void setListView(View view)
	{
		 Cursor c = db_obj.getAllRows();
	
		//startManagingCursor(c);
		
		String[] DB_columns = new String[] {DatabaseHelper.Description,DatabaseHelper.Priority,DatabaseHelper.Date,DatabaseHelper.Time,DatabaseHelper.Location};
		int[] LV_id = new int[] {R.id.textView_desc,R.id.textView_priority,R.id.textView_date,R.id.textView_time,R.id.textView_location};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
				R.layout.display_list_xml,
				c ,DB_columns,LV_id,0);
		
		ListView LV = (ListView)findViewById(R.id.listViewDisplayList);
		LV.setAdapter(adapter);
		
		registerListClickCallback();
		
		swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

		swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                populate_ListView();
				swipeContainer.setRefreshing(false);

            } 
         });


	}
	
	public void registerListClickCallback() {
		ListView LV = (ListView) findViewById(R.id.listViewDisplayList);
		
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, 
					int position, long id_DB) {

				updateItemForId(id_DB);
				//displayToastForId(idInDB);
			}
		});
	}
	
	public void updateItemForId(long id_DB) {
		
//		Cursor c = db_obj.getRow(id_DB);
//		
//		if (c.moveToFirst()) {
//			long set_idDB = c.getLong(db_obj.COL_ID);
//			String desc = c.getString(db_obj.COL_Description);
//			String priority = c.getString(db_obj.COL_Priority);
//			String date = c.getString(db_obj.COL_Date);
//			String time = c.getString(db_obj.COL_Time);
//			String location = c.getString(db_obj.COL_Location);
//
//			location += "!";
//			db_obj.updateRow(set_idDB, desc, priority, date,time,location);
//		}
//		c.close();
//		populate_ListView();	
		Intent intent = new Intent(this, UpdateList.class);
		intent.putExtra("ID", id_DB);
		startActivity(intent);
	}
	
	public void DeleteList(View view)
	{
		db_obj.deleteAll();
		populate_ListView();
	}

}




