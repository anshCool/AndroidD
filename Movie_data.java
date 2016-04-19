package com.example.mymovie;



import java.io.InputStream;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Movie_data extends Activity {
  MyHelper helper;
  TextView getTitle;
  TextView getRelease;
  TextView getDirector;
  TextView getActors;
  TextView getRating;
  TextView getVotes;
  TextView getGenre;
  TextView getPlot;
  TextView getWriter;
  TextView getTime;
  TextView getAwards;
  TextView getLanguage;
  ImageView getPoster;
  static String s=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_movie);
		getTitle=(TextView)findViewById(R.id.m_title);
		getRelease=(TextView)findViewById(R.id.m_release);
		getDirector=(TextView)findViewById(R.id.m_director);
		getActors=(TextView)findViewById(R.id.m_actors);
		getRating=(TextView)findViewById(R.id.m_rating);
		getVotes=(TextView)findViewById(R.id.m_votes);
		getGenre=(TextView)findViewById(R.id.m_genere);
		getPlot=(TextView)findViewById(R.id.m_plot);
		getWriter=(TextView)findViewById(R.id.m_writer);
		getTime=(TextView)findViewById(R.id.m_time);
		getAwards=(TextView)findViewById(R.id.m_awards);
		getLanguage=(TextView)findViewById(R.id.m_lang);
		getPoster=(ImageView)findViewById(R.id.m_poster);
		helper=new MyHelper(this);
		Intent getIntent=getIntent();
		String gtitle=getIntent.getStringExtra("t");
		String grelease=getIntent.getStringExtra("r");
		String gdirector=getIntent.getStringExtra("d");
		String gactors=getIntent.getStringExtra("a");
		String grating=getIntent.getStringExtra("ir");
		String gvotes=getIntent.getStringExtra("iv");
		String ggenre=getIntent.getStringExtra("g");
		String gplot=getIntent.getStringExtra("p");
		String gwriter=getIntent.getStringExtra("w");
		String gtime=getIntent.getStringExtra("ti");
		String gawards=getIntent.getStringExtra("aw");
		String glanguage=getIntent.getStringExtra("l");
		String gposter=getIntent.getStringExtra("mpic");
		s=gtitle;
		getTitle.setText(gtitle);
		getRelease.setText("Release Date: "+grelease);
		getDirector.setText("Director: "+gdirector);
		getActors.setText("Actors: "+gactors);
		getRating.setText("Imdb rating: "+grating);
		getVotes.setText("Imdb votes: "+gvotes);
		getGenre.setText("Genre: "+ggenre);
		getPlot.setText("Plot: "+gplot);
		getWriter.setText("Writer: "+gwriter);
		getTime.setText("Duration: "+gtime);
		getAwards.setText("Awards: "+gawards);
		getLanguage.setText("Language: "+glanguage);
		getPoster.setImageResource(R.drawable.ic_launcher);
		new DownloadImage(getPoster).execute(gposter);
		
	}

	public class DownloadImage extends AsyncTask<String, Void, Bitmap>
	{
		ImageView bitImg;
		
		public DownloadImage(ImageView bitImg)
		{
			this.bitImg=bitImg;
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			String urlImg=urls[0];
			Bitmap pIcon=null;
			try{
				InputStream inp=new java.net.URL(urlImg).openStream();
				pIcon=BitmapFactory.decodeStream(inp);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return pIcon;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			bitImg.setImageBitmap(result);
		}
		
		
		
	}
	
@Override
	public void onBackPressed() {
		Intent i =new Intent(getApplicationContext(), DatatofetchActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

    public void addtoList(View v) {
    	int count=0;
    	SQLiteDatabase fdb=helper.getReadableDatabase();
    	String[] ckmovie={"moviename"};
		Cursor c=fdb.query("usertable",ckmovie,null,null,null,null,null);
		   while(c.moveToNext())
		   {
			   String usermCheck=c.getString(0);
			   if(usermCheck.equals(s))
			   {
				   count=1;
				   break;
			   }
		   }
		 if(count==0)
		 {
			    ContentValues fcv=new ContentValues();
				fcv.put("moviename",s);
				long fid=fdb.insert("usertable", null, fcv);
				if(fid==-1)
				{
					Toast.makeText(this, "Some error occured try again.", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "Dear user, "+s+" has been added to your movie list.", Toast.LENGTH_SHORT).show();
				} 
		 }
		 
		 else if(count!=0)
		 {
			 Toast.makeText(this, s+" is already added to your movie list.", Toast.LENGTH_SHORT).show();
			 
		 }
    	
     }
}
