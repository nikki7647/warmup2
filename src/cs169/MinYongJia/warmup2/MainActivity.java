package cs169.MinYongJia.warmup2;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText usernameEdit, passwordEdit;
	Button loginButton, addButton;
	TextView messageBox;
	//AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		usernameEdit = (EditText)findViewById(R.id.username_edit);
		passwordEdit = (EditText)findViewById(R.id.password_edit);
		loginButton = (Button)findViewById(R.id.login_button);
		addButton = (Button)findViewById(R.id.add_user_button);
		messageBox = (TextView)findViewById(R.id.message_box);
		//builder = new AlertDialog.Builder(this);
		//builder.setMessage("Yo mama so fat she blocks the connection!");
		//builder.setPositiveButton("OK", null);
		
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					JSONObject postData = new JSONObject();
					postData.put("user", usernameEdit.getText().toString());
					postData.put("password", passwordEdit.getText().toString());
					JSONObject obj = SimpleHTTPPOSTRequester.makeHTTPPOSTRequest("http://stark-everglades-2919.herokuapp.com/users/login", postData);
					if(obj.get("errCode").toString().equals("1")){
						Intent launchSuccessIntent = new Intent(getBaseContext(), LoginActivity.class);
						launchSuccessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						launchSuccessIntent.putExtra("user", usernameEdit.getText().toString());
						launchSuccessIntent.putExtra("count", obj.get("count").toString());
						getBaseContext().startActivity(launchSuccessIntent);
					}
					else if(obj.get("errCode").toString().equals("-1")) {
						messageBox.setText("Invalid user password combination.");
					}
					else if(obj.get("errCode").toString().equals("-2")) {
						messageBox.setText("User already exists.");
					}
					else if(obj.get("errCode").toString().equals("-3")) {
						messageBox.setText("Invalid username.");
					}
					else if(obj.get("errCode").toString().equals("-4")) {
						messageBox.setText("Invalid password.");
					}
				} catch (RuntimeException e) {
					messageBox.setText("Yo mama so fat she blocks the connection!");
					throw e;
				} catch (JSONException e) {
					messageBox.setText("Yo mama so fat she flattens JSON!");
				} catch (Exception e) {
					messageBox.setText("Yo mama so fat something else is wrong!");
				} 
				
			}
			
		});
		
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					JSONObject postData = new JSONObject();
					postData.put("user", usernameEdit.getText().toString());
					postData.put("password", passwordEdit.getText().toString());
					JSONObject obj = SimpleHTTPPOSTRequester.makeHTTPPOSTRequest("http://stark-everglades-2919.herokuapp.com/users/add", postData);
					if(obj.get("errCode").toString().equals("1")){
						Intent launchSuccessIntent = new Intent(getBaseContext(), LoginActivity.class);
						launchSuccessIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						launchSuccessIntent.putExtra("user", usernameEdit.getText().toString());
						launchSuccessIntent.putExtra("count", obj.get("count").toString());
						getBaseContext().startActivity(launchSuccessIntent);
					}
					else if(obj.get("errCode").toString().equals("-1")) {
						messageBox.setText("Invalid user password combination.");
					}
					else if(obj.get("errCode").toString().equals("-2")) {
						messageBox.setText("User already exists.");
					}
					else if(obj.get("errCode").toString().equals("-3")) {
						messageBox.setText("Invalid username.");
					}
					else if(obj.get("errCode").toString().equals("-4")) {
						messageBox.setText("Invalid password.");
					}
				} catch (RuntimeException e) {
					messageBox.setText("Yo mama so fat she blocks the connection!");
				} catch (JSONException e) {
					messageBox.setText("Yo mama so fat she flattens JSON!");
				} catch (Exception e) {
					messageBox.setText("Yo mama so fat something else is wrong!");
				} 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
