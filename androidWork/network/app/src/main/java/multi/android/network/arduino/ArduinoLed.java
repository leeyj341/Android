package multi.android.network.arduino;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import multi.android.network.R;

public class ArduinoLed extends AppCompatActivity implements View.OnClickListener {

    Button btnOn;
    Button btnOff;
    Socket socket;
    OutputStream os;
    PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_led);
        btnOn = findViewById(R.id.btn_on);
        btnOff = findViewById(R.id.btn_off);
        btnOn.setOnClickListener(this);
        btnOff.setOnClickListener(this);

        new ArduinoAsync().execute();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_on) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    pw.println(1);
                    pw.flush();
                }
            }).start();
        } else if(v.getId() == R.id.btn_off) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    pw.println(0);
                    pw.flush();
                }
            }).start();
        }
    }

    class ArduinoAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                socket = new Socket("70.12.231.132", 5000);
                os = socket.getOutputStream();
                pw = new PrintWriter(os);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
