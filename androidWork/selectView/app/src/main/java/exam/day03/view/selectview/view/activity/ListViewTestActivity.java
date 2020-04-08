package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import exam.day03.view.selectview.R;

public class ListViewTestActivity extends AppCompatActivity {
    //1. ListView에 출력할 데이터
    String[] datalist ={"java","oracle","HTML5","CSS","javascript",
            "servlet","jsp","spring","hadoop","flume","sqoop","hive",
            "R","android"};
    ListView listview ;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_test);
        listview = findViewById(R.id.listview1);
        txt = findViewById(R.id.listTxt);
        /*
        리스트뷰에 출력할 데이터?
        어떤 디자인으로 출력? */
        //2. Adapter 객체를 선택해서 생성
        ArrayAdapter adapter = new ArrayAdapter(this,
                             android.R.layout.simple_list_item_1,datalist);
        //3. ListView에서 어댑터가 작업할 수 있도록 ListView에 어댑터셋팅
        listview.setAdapter(adapter);

        //이벤트연결
        MyListener listener = new MyListener();
        listview.setOnItemClickListener(listener);
    }
    class MyListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent,
                                View view, int position, long id) {
             txt.setText(datalist[position]);
        }
    }
}
