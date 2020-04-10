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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import multi.android.datamanagermentpro.R;

public class FileExam extends AppCompatActivity {
    EditText memo;
    boolean per_state;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_exam);
        memo = findViewById(R.id.txt_data);

        String[] Permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        checkPermissions(Permissions);
    }

    public void checkPermissions(String[] permissions) {
        ArrayList<String> checkList = new ArrayList<String>();
        int count = 0;
        for(int i = 0; i < permissions.length; i++) {
            if(ContextCompat.checkSelfPermission(this, permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                count++;
            } else {
                checkList.add(permissions[i]);
            }
        }
        if(count == 2) {
            per_state = true;
        } else {
            String[] arrCheck = new String[checkList.size()];
            checkList.toArray(arrCheck);

            ActivityCompat.requestPermissions(this, arrCheck, 1003);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1003 && grantResults.length > 0) {
            int count = 0;
            for(int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    count++;
                }
            }
            if(count == 2) {
                per_state = true;
            }
        }
    }

    public void openFile(View v) {
        if(getState().equals(Environment.MEDIA_MOUNTED)) {
            File external = Environment.getExternalStorageDirectory();
            String dirPath = external.getAbsolutePath();
            String pkg = getPackageName();
            File file = new File(dirPath + "/android/data/" + pkg + "/" + getToday() +"_memo.txt");
            BufferedReader br = null;
            if(file.exists()) {
                try {
                    br = new BufferedReader(new FileReader(file));
                    memo.setText("");
                    String line = br.readLine();
                    while(line != null) {
                        memo.append(line);
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(br != null)
                            br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(this, "해당 파일이 존재하지 않음", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this,"사용불가능", Toast.LENGTH_LONG).show();
        }
    }

    public void saveFile(View v) {
        if(getState().equals(Environment.MEDIA_MOUNTED)) {
            File external = Environment.getExternalStorageDirectory();
            String dirPath = external.getAbsolutePath();
            String pkg = getPackageName();
            File file = new File(dirPath + "/android/data/" + pkg);
            FileWriter fw = null;
            if(!file.exists()) {
                file.mkdir();
            }
            try {
                fw = new FileWriter(file + "/" + getToday() + "_memo.txt");
                fw.write(memo.getText().toString());
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
    }

    public void newFile(View v) {
        if(getState().equals(Environment.MEDIA_MOUNTED)) {
            File external = Environment.getExternalStorageDirectory();
            String dirPath = external.getAbsolutePath();
            String pkg = getPackageName();
            File file = new File(dirPath + "/android/data/" + pkg + "/" + getToday() + "_memo.txt");
            if(file.exists()) {
                file.delete();
            } else {
                Toast.makeText(this, "해당 파일이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getState() {
        String state = null;
        if(per_state) {
            state = Environment.getExternalStorageState();
        } else {
            Toast.makeText(this, "권한 설정이 필요합니다.", Toast.LENGTH_LONG).show();
        }
        return state;
    }

    public String getToday() {
        Date curTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String today = yearFormat.format(curTime) + monthFormat.format(curTime) + dayFormat.format(curTime);
        return today;
    }
}
