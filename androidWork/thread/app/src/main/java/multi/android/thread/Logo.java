package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Logo extends AppCompatActivity {
    Handler handler;
    //5초 후에 처리해야 하는 작업을 쓰레드 정의
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Logo.this, HandlerExam2.class);
            startActivity(intent);
            finish();
            //다른 액티비티로 전환될 때 애니메이션 효과를 추가

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        handler = new Handler();
        handler.postDelayed(runnable, 5000);

    }
}
