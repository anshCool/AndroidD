package com.example.mymovie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FetchActivity extends Activity {
    
    static String title=null;
    static String release=null;
    static String director=null;
    static String actors=null;
    static String rating=null;
    static String votes=null;
    static String genere=null;
    static String plot=null;
    static String writer=null;
    static String time=null;
    static String awards=null;
    static String language=null;
    static String pic=null;
    
    static String encodedUrl=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent getData=getIntent();
		String movie=getData.getStringExtra("t1");
		try {
			encodedUrl=URLEncoder.encode(movie, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		new AsyncParse().execute("http://www.omdbapi.com/?t="+encodedUrl);
		
	}
  
	public class AsyncParse extends AsyncTask<String, Void, Boolean> {
        ProgressDialog pd;
		protected void onPreExecute() {
			super.onPreExecute();
			pd=new ProgressDialog(FetchActivity.this);
			pd.setMessage("Please Wait..");
			pd.setTitle("Connecting..");
			pd.show();
			pd.setCancelable(false);
		}
		
		@Override
		protected Boolean doInBackground(String... urlf) {
			try
			{
				HttpGet httpGet=new HttpGet(urlf[0]);
				HttpClient client=new DefaultHttpClient();
				HttpResponse response=client.execute(httpGet);
				int status=response.getStatusLine().getStatusCode();
				if(status==200)
				{
					HttpEntity entity=response.getEntity();
					String data = EntityUtils.toString(entity);
					
					JSONObject jobj=new JSONObject(data);
					title=jobj.getString("Title");
					release=jobj.getString("Released");
					director=jobj.getString("Director");
					actors=jobj.getString("Actors");
					rating=jobj.getString("imdbRating");
					votes=jobj.getString("imdbVotes");
					genere=jobj.getString("Genre");
					plot=jobj.getString("Plot");
					writer=jobj.getString("Writer");
					time=jobj.getString("Runtime");
					awards=jobj.getString("Awards");
					language=jobj.getString("Language");
					pic=jobj.getString("Poster");
					return true;
				}
				
			}
			catch(ParseException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
			
			return false;
		}
		
		protected void onPostExecute(Boolean rst) {
			pd.cancel();
			if(rst==false)
			{
				Toast.makeText(getApplicationContext(), "Connection Error..!!Check Your Data Connection or Movie not found.", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(), DatatofetchActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
			if(rst==true)
			{
				Toast.makeText(getApplicationContext(), "Connection Established.Loading Information..", Toast.LENGTH_SHORT).show();
				Intent i =new Intent(getApplicationContext(), Movie_data.class);
				i.putExtra("t",title);
				i.putExtra("r",release);
				i.putExtra("d",director);
				i.putExtra("a",actors);
				i.putExtra("ir",rating);
				i.putExtra("iv",votes);
				i.putExtra("g",genere);
				i.putExtra("p",plot);
				i.putExtra("w",writer);
				i.putExtra("ti",time);
				i.putExtra("aw",awards);
				i.putExtra("l",language);
				i.putExtra("mpic", pic);
				startActivity(i);
			}
		}

	}
	public void show(View v)
	{   
		Intent i =new Intent(this,Movie_data.class);
		i.putExtra("t",title);
		startActivity(i);
	}
@Override
public void onBackPressed() {
	
}
}
