package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExamSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_secondview);

        TextView resultView = findViewById(R.id.exam_result_txt);
        final CheckBox checkBox = findViewById(R.id.member_state);
        Button btnClose = findViewById(R.id.exam_close);

        final Intent intent = getIntent();
        String id = intent.getStringExtra("name");
        if(id == null) {
            User dto = intent.getParcelableExtra("dto");
            resultView.setText(dto.name + ", " + dto.telNum);
        } else {
            String tel = intent.getStringExtra("telNum");
            resultView.setText("입력한 id: " + id + ", 입력한 전화번호: " + tel);
        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    intent.putExtra("m_state", "우수회원설정");
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }

}
