package multi.android.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView clientInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientInfo = findViewById(R.id.textInfo);

    }
    public void btn_connect(View v){
       new networkClient().start();
    }
  
    class networkClient extends Thread {
        @Override
        public void run() {
            Socket socket;
            InputStream is = null;
            DataInputStream dis = null;
            OutputStream os = null;
            DataOutputStream dos = null;
            try {
                socket = new Socket("70.12.116.62", 12345);
                System.out.println("서버접속완료...." + socket);

                is = socket.getInputStream();
                dis = new DataInputStream(is);

                os = socket.getOutputStream();
                dos = new DataOutputStream(os);

                final String data = dis.readUTF();
                Log.d("mynetwork","서버가 전송하는 메시지 1 : " + data);
                final int intdata = dis.readInt();
                Log.d("mynetwork", "서버가 전송하는 메시지 2 : " + intdata);

                dos.writeUTF("안녕하세요 서버님 클라이언트입니다.");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clientInfo.append(data + "\n");
                        clientInfo.append(intdata + "\n");
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
