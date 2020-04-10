package multi.android.datamanagermentpro.filesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import multi.android.datamanagermentpro.R;

public class ExternalFileMgr extends AppCompatActivity {
    boolean permission_state;
    TextView view;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file_mgr);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            permission_state = true;
        } else {
            permission_state = false;
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1002);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1002 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission_state = true;
            }
        }
    }

    public void saveExternalFileSystem(View v) {
        // 권한이 있으면 권한 설정 완료 Toast
        // 없으면 체크해야 한다고 Toast
        if(permission_state) {
            Toast.makeText(this,"권한이 설정되었습니다.", Toast.LENGTH_LONG).show();
            //외부저장소를 사용할 수 있는지 state를 추출
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)) { //현재 사용가능한 상태
                Toast.makeText(this,"사용가능", Toast.LENGTH_LONG).show();
                File external = Environment.getExternalStorageDirectory();
                //외부저장소/임의의디렉토리를 생성 - 앱을 삭제해도 데이터는 남아있다.
                //String dirPath = external.getAbsolutePath() + "/myApp";
                //외부저장소/android/data/앱의 패키지명으로 디렉토리 생성
                //=> 앱을 삭제하면 데이터가 같이 삭제된다.
                String dirPath = external.getAbsolutePath();
                String pkg = getPackageName();
                File dir = new File(dirPath + "/android/data/" + pkg);
                //디렉토리 없으면 생성
                //File dir = new File(dirPath);
                if(!dir.exists()) {
                    dir.mkdir();
                }
                FileWriter fw = null;
                try {
                    fw = new FileWriter(dir + "/test1.txt");
                    fw.write("외부저장소 테스트 중");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(fw != null)
                            fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(this,"사용불가능", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"권한을 설정해야 합니다.", Toast.LENGTH_LONG).show();
        }

    }

    public void openInternalFile(View v) {

    }
}
