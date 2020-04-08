package multi.android.intent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnDataSecondActivity extends AppCompatActivity {
	String code;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second2);
	    Button bt1 = (Button)findViewById(R.id.btnClose1);
		final TextView txt = findViewById(R.id.secondTxt);
		final Intent intent = getIntent();
		code = intent.getStringExtra("code");
	    bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(code){
					case "call1" :
						String data = intent.getStringExtra("info_data");
						Toast.makeText(ReturnDataSecondActivity.this,data,Toast.LENGTH_LONG).show();
						break;
					case "call2" :
						String data2 = intent.getStringExtra("data");
						txt.setText(data2);
						intent.putExtra("second","두 번째 액티비티에서 실행 완료");
						//실행 후에 호출한 액티비티로 되돌아가기
						//되돌아갈 때 값을 공유하기 위해 intent 객체를 넘긴다.
						setResult(RESULT_OK ,intent);
						finish();
						break;
				}
			}
		});
	}

}
