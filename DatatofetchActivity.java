package com.example.mymovie;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatatofetchActivity extends Activity implements OnClickListener {
	EditText movie_Name;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datatofetch);
		movie_Name=(EditText)findViewById(R.id.movieName1);
		
		
		
	}
	
	public void show(View v)
	{   String title=movie_Name.getText().toString();
	     if(title.equalsIgnoreCase("")||title.equalsIgnoreCase(" ")||title.equals(null))
	     {
	    	 Toast.makeText(getApplicationContext(), "Please provide movie name.", Toast.LENGTH_SHORT).show();
	     }
	     else
	     {
	    	Intent i =new Intent(this,FetchActivity.class);
	 		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	 		i.putExtra("t1",title);
	 		startActivity(i); 
	     }
		
	}
	
	public void openList(View v)
	{
		Intent i=new Intent(getApplicationContext(), MovieList.class);
		startActivity(i);
	}
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder ab=new AlertDialog.Builder(this);
		ab.setTitle("EXIT :");
		ab.setMessage("Are You Sure To Exit ?");
		ab.setIcon(R.drawable.icon);
		ab.setPositiveButton("Yes", this);
		ab.setNegativeButton("No", this);
		ab.setNeutralButton("Not Now", this);
		AlertDialog ad=ab.create();
		ad.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(which==-1)
		{
			finish();
		}
		
		if(which==-2)
		{
			
		}
		
		if(which==-3)
		{
			dialog.dismiss();
		}
		
	}

}
