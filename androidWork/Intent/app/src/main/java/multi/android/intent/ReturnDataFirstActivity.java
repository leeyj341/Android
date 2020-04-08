package multi.android.intent;



import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReturnDataFirstActivity extends AppCompatActivity
			implements OnClickListener{
    public static final int SECOND_BTN = 10;
    /** Called when the activity is first created. */
	Button bt1;
	Button bt2;
    Button bt3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first2);
        bt1 = (Button)findViewById(R.id.call1);
        bt2 = (Button)findViewById(R.id.call2);
        bt3 = (Button)findViewById(R.id.btnExit);
        
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        Log.d("kim","onCreate()");
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnExit){
		    finish();
        }else if(v.getId()==R.id.call1){
		    Intent intent  = new Intent();
            intent.putExtra("info","첫 번째 액티비티가 넘기는 메시지");
            startActivity(intent);
        } else if (v.getId() == R.id.call2) {
		    //새로운 액티비티를 실행해서 작업을 완료한 후 되돌아오는 작업을 수행
            Intent intent = new Intent(this, ReturnDataSecondActivity.class);
            intent.putExtra("code","call2");
            intent.putExtra("data","첫 번째 액티비티가 넘기는 메시지");
            //되돌아올 때 사용되는 메서드가 startActivityForResult
            //인텐트 객체와 함께 request code를 넘긴다. (사용자 정의)
            //보통은 상수를 정해놓고 사용
            startActivityForResult(intent, SECOND_BTN);
        }
	}

	//인텐트를 통해서 액티비티를 호출하고 되돌아오는 경우 자동으로 onActivityResult 호출
    //- 되돌아와서 마무리해야 하므로
    //onActivityResult 를 오바라이딩해서 처리할 작업을 구현
    //requestCode - 요청을 했던 뷰를 구분하기 위한 코드
    //resultCode - 결과 코드
    //data - Intent 객체

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == SECOND_BTN) {
            if(resultCode == RESULT_OK) {
                String returnData = intent.getStringExtra("second");
                Toast.makeText(this, returnData, Toast.LENGTH_LONG).show();
            }
        }
    }
}