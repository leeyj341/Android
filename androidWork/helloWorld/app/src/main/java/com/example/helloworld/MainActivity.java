package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//Activity의 LifeCycle
public class MainActivity extends AppCompatActivity {
    //Activity가 생성될 때 자동으로 호출
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("onCreate호출~~~");
        Log.d("test", "onCreate호출");
        setContentView(R.layout.activity_main);
    }
    //onCreate 다음으로 호출되는 메서드
    //-(액티비티가 실행 : 2단, pause상태에 빠져나올때는 onStart가 호출)
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "onStart()호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test", "onResume()호출");
    }
    //일시정지 상태로 바뀔 때 호출되는 메서드
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test", "onPause()호출");
    }
    //일시정지나 종료 상태로 바뀔 때 onPause다음으로 호출
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test", "onStop()호출");
    }

    //앱이 종료될 때 호출되는 메서드
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy()호출");
    }





    // 버튼을 클릭했을 때 실행할 메서드를 정의
    // 메서드의 매개변수에 실행할 버튼을 정의
    // Button의 상위인 view타입으로 정의
    public void myclickMethod(View v) {
        //                                    메시지                 메시지 띄울 시간
        Toast.makeText(this, "확인버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void mycancelMethod(View v) {
        Toast.makeText(this,"취소버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
    }

    public void mydeleteMethod(View v) {
        Toast.makeText(this,"삭제버튼이 눌렸습니다.",Toast.LENGTH_LONG).show();
    }
}
