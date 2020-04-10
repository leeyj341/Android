package multi.android.datamanagermentpro.filesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import multi.android.datamanagermentpro.R;

public class InternalFileMgr extends AppCompatActivity {
    TextView internalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internalTxt = findViewById(R.id.fileTxt);
    }

    public void saveInternalFile(View v) {
        //내부저장소는 데이터를 저장하거나 데이터를 읽어올 때 스트림을 직접 생성하지 않는다.
        //openFileOutput을 이용
        //매개변수 name은 파일명
        //mode => MODE_APPEND : 기존 파일에 내용을 추가
        //        MODE_PRIVATE : 기존 파일을 덮어쓰겠다는 의미
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            dos = new DataOutputStream(fos);
            dos.writeUTF("테스트 중");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dos != null)
                    dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openInternalFile(View v) {
        FileInputStream fIs = null;
        DataInputStream dIs = null;
        try {
            fIs = openFileInput("myfile.txt");
            dIs = new DataInputStream(fIs);
            String data = dIs.readUTF();
            internalTxt.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(dIs != null)
                    dIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
