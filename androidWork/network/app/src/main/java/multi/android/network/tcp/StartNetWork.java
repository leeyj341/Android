package multi.android.network.tcp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.network.R;
import multi.android.network.arduino.ArduinoLed;

public class StartNetWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_net_work);
    }
    public void btn_tcpChat(View view){
        Intent intent = new Intent(this, ChatClientActivity.class);
        startActivity(intent);
    }

    public void btn_arduino(View view) {
        Intent intent = new Intent(this, ArduinoLed.class);
        startActivity(intent);
    }
}
