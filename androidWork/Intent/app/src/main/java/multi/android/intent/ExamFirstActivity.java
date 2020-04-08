package multi.android.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExamFirstActivity extends AppCompatActivity {
    public static final int SUBMIT_BTN = 100;
    public static final int OBJECT_INTENT =200;
    TextView returnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstexam);

        Button btn1 = findViewById(R.id.Button01);
        Button btn2 = findViewById(R.id.Button02);
        final EditText nameView = findViewById(R.id.EditText01);
        final EditText telView = findViewById(R.id.EditText02);
        returnTxt = findViewById(R.id.first_return);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //명시적 인텐트 (클래스의 이름을 지정하는 경우)
                Intent intent = new Intent(ExamFirstActivity.this,
                        ExamSecondActivity.class);

                intent.putExtra("name", nameView.getText().toString());
                intent.putExtra("telNum", telView.getText().toString());

                startActivityForResult(intent, SUBMIT_BTN);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //액티비티를 호출하면서 인텐트에 객체를 공유
                Intent intent = new Intent(ExamFirstActivity.this,
                        ExamSecondActivity.class);
                User user = new User(nameView.getText().toString(), telView.getText().toString());
                intent.putExtra("dto", user);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == SUBMIT_BTN) {
            if(resultCode == RESULT_OK) {
                returnTxt.setText(intent.getStringExtra("m_state"));
            } else {
                returnTxt.setText("일반회원");
            }
        }
    }
}
