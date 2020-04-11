package multi.android.datamanagermentpro.sqlite.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import multi.android.datamanagermentpro.R;


public class ReadActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Intent intent = getIntent();
		ProductData data = intent.getParcelableExtra("data");
		TextView t = (TextView)findViewById(R.id.show);
		t.setText(data._id + "," + data.name + "," + data.price);
	}

}
