package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SMSExamActivity extends AppCompatActivity {
    TextView txtoutput;
    EditText txtinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_exam);
        txtoutput = findViewById(R.id.output);
        txtinput = findViewById(R.id.input);

        Button btnSend = findViewById(R.id.btnsend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txtinput.getText().toString();
                txtoutput.append(msg + "\n");
                txtinput.setText("");
            }
        });
    }
}
