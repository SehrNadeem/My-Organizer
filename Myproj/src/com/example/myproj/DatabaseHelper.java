package com.example.myproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper {

	private static final String TAG = "DatabaseHelper";
	
	// DB Fields
	public static final String ListID = "_id";
	public static final String Description = "Description";
	public static final String Priority = "Priority";
	public static final String Date = "Date";
	public static final String Time = "Time";
	public static final String Location = "Location";
	public static final String Picture = "Picture";
	
	public static final int COL_ID = 0;
	public static final int COL_Description = 1;
	public static final int COL_Priority = 2;
	public static final int COL_Date = 3;
	public static final int COL_Time = 4;
	public static final int COL_Location = 5;
	public static final int COL_Picture = 6;

	
	public static final String[] ALL_KEYS = new String[] {ListID, Description, Priority, Date, Time,Location,Picture};
	
	public static final String DATABASE_NAME = "MyDatabase";
	public static final String DATABASE_TABLE = "ListTable";

	public static final int DATABASE_VERSION = 2;	
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + ListID + " integer primary key autoincrement, "
			+ Description + TEXT_TYPE + COMMA_SEP
			+ Priority + TEXT_TYPE + COMMA_SEP
			+ Date + TEXT_TYPE + COMMA_SEP
			+ Time + TEXT_TYPE + COMMA_SEP
			+ Location + TEXT_TYPE + COMMA_SEP
			+ Picture + " blob"
			+ ");";
	
	private final Context context;
	
	private DBHelper myDBHelper;
	private SQLiteDatabase db;
	
	public DatabaseHelper(Context ctx) {
		this.context = ctx;
		myDBHelper = new DBHelper(context);
	}
	
	// Open the database connection.
	public DatabaseHelper open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	public long insertRow(String desc, String _priority, String _date, String _time,String _loc) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(Description, desc);
		initialValues.put(Priority, _priority);
		initialValues.put(Date, _date);
		initialValues.put(Time, _time);
		initialValues.put(Location, _loc);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = ListID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(ListID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = ListID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId,String desc, String _priority, String _date, String _time,String _loc) 
	{
		String where = ListID + "=" + rowId;
		
		ContentValues newValues = new ContentValues();
		newValues.put(Description, desc);
		newValues.put(Priority, _priority);
		newValues.put(Date, _date);
		newValues.put(Time, _time);
		newValues.put(Location, _loc);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DBHelper extends SQLiteOpenHelper
	{
		DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);			
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}
