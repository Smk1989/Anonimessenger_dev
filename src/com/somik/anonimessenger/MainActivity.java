package com.somik.anonimessenger;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button register;
	JSONParser jsonParser=new JSONParser();
	com.somik.anonimessenger.JSONParser jsonparser= new com.somik.anonimessenger.JSONParser();
	EditText inputName;
	EditText inputPassword;
	EditText inputconfirmPassword;
	EditText inputPhonenumber;
	private static String url_register="http://localhost/index.php";
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
register=(Button)findViewById(R.id.button1);
inputName=(EditText)findViewById(R.id.editText1);
inputPhonenumber=(EditText)findViewById(R.id.editText2);
inputPassword=(EditText)findViewById(R.id.editText3);
inputconfirmPassword=(EditText)findViewById(R.id.editText4);




register.setOnClickListener(new View.OnClickListener() {
	
	
	@Override
	public void onClick(View v) {
		new Register().execute();
	
		// TODO Auto-generated method stub
		
	}
});
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public class Register extends AsyncTask<String,String,String>
	{
		protected void onPreExecute()
		{
			super.onPreExecute();
			ProgressDialog pDialog=new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Registering");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();	
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			String name=inputName.getText().toString();
			String phonenumber=inputPhonenumber.getText().toString();
			String password=inputPassword.getText().toString();
			String confirm_password=inputconfirmPassword.getText().toString();
			if (password.equals(confirm_password)==true)
			{List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("phonenumber", phonenumber));
			params.add(new BasicNameValuePair("password",password));
			JSONObject json=jsonparser.makeHttpRequest(url_register,"POST",params);
			}
			else
			{
				Context context=getApplicationContext();
				int duration=Toast.LENGTH_SHORT;
				String error_pass="Passwords do not match";
				Toast toast=Toast.makeText(context, error_pass, duration);
				toast.show();
			}
			return null;
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}