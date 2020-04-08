package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FrameChangeExam extends AppCompatActivity {
    Button login;
    Button signup;
    Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_change_exam);
        login = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.btnsignup);
        info = findViewById(R.id.btninfo);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.frame1).setVisibility(View.VISIBLE);
                findViewById(R.id.frame2).setVisibility(View.INVISIBLE);
                findViewById(R.id.frame3).setVisibility(View.INVISIBLE);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.frame2).setVisibility(View.VISIBLE);
                findViewById(R.id.frame1).setVisibility(View.INVISIBLE);
                findViewById(R.id.frame3).setVisibility(View.INVISIBLE);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.frame3).setVisibility(View.VISIBLE);
                findViewById(R.id.frame1).setVisibility(View.INVISIBLE);
                findViewById(R.id.frame2).setVisibility(View.INVISIBLE);
            }
        });

        Button enroll = findViewById(R.id.btnenroll);
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = findViewById(R.id.infotxt);
                EditText name = findViewById(R.id.infoname);
                EditText id = findViewById(R.id.infoid);
                EditText pass = findViewById(R.id.infopass);
                txt.setText(name.getText().toString() + "\n");
                txt.append(id.getText().toString() + "\n");
                txt.append(pass.getText().toString() + "\n");
            }
        });
    }
    //버튼이 클릭될 때 호출되는 메서드 = View.OnClickListener의 onClick 메서드와 동일한 역할
    public void myClick(View v) {
        if(v.getId() == R.id.btnlogin) {
            findViewById(R.id.frame1).setVisibility(View.VISIBLE);
            findViewById(R.id.frame2).setVisibility(View.INVISIBLE);
            findViewById(R.id.frame3).setVisibility(View.INVISIBLE);
        } else if(v.getId() == R.id.btnsignup) {
            findViewById(R.id.frame2).setVisibility(View.VISIBLE);
            findViewById(R.id.frame1).setVisibility(View.INVISIBLE);
            findViewById(R.id.frame3).setVisibility(View.INVISIBLE);
        } else if(v.getId() == R.id.btninfo) {
            findViewById(R.id.frame3).setVisibility(View.VISIBLE);
            findViewById(R.id.frame1).setVisibility(View.INVISIBLE);
            findViewById(R.id.frame2).setVisibility(View.INVISIBLE);
        }
    }
}
