package com.example.mymovie;



import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MovieList extends Activity implements OnItemClickListener {
	MyHelper helper;
	ListView lvmovie;
	ArrayList<String> myList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fav_movie);
		helper=new MyHelper(this);
		lvmovie=(ListView) findViewById(R.id.fav_infolv);
		SQLiteDatabase db=helper.getWritableDatabase();
		String[] cols={"moviename"};
		Cursor c=db.query("usertable",cols,null,null,null,null,null);
		while(c.moveToNext())
		{
			String p=c.getString(0);
			myList.add(p);
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList);
		lvmovie.setAdapter(adapter);
		lvmovie.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
		String clickedPlace=myList.get(pos);
		Intent i =new Intent(this,FetchActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("t1",clickedPlace);
		startActivity(i);
		
	}

}
