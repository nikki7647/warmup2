package cs169.MinYongJia.warmup2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	TextView messageBox;
	Button logoutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		messageBox= (TextView)findViewById(R.id.message_box);
		logoutButton = (Button)findViewById(R.id.logout_button);
		Intent i = getIntent();
		String user = i.getStringExtra("user");
		String count = i.getStringExtra("count");
		messageBox.setText("Welcome "+user+"!\nYou have logged in "+count+" times.");
		logoutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
