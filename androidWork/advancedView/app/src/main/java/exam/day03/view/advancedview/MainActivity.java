package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    ImageView iv2;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.img01);
        iv2 = findViewById(R.id.img02);


    }
    //버튼이 클릭될 때 호출되는 메서드
    public void myClick(View v) {
        imageChange();
    }
    public void imageChange() {
        //이미지가 교체돼서 보이도록 구현
        if(index == 0) {
            //0번에 해당하는 이미지를 화면에 보이도록 설정
            iv1.setVisibility(View.VISIBLE);
            //1번은 화면에 보이지 않도록 설정
            iv2.setVisibility(View.INVISIBLE);
            index = 1;
        } else if(index == 1){
            iv2.setVisibility(View.VISIBLE);
            iv1.setVisibility(View.INVISIBLE);
            index= 0;
        }
        Log.d("value","현재 indedx값  =====" + index);


    }
}
