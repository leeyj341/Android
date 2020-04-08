package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BasicAppRun extends AppCompatActivity {
    //승인받을 권한의 목록
    String[] permission_list = {
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_app_run);
        //권한체크 메서드를 호출
        getPermission();

    }
    //구글 맵 실행
    public void runGoogleMap(View v) {
        Uri uri = Uri.parse("geo:37.497989, 127.027560");
        // 안드로이드 내부에서 정의되어 있는 액션명
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    //웹브라우저 생성
    public void runWeb(View v) {
        Uri uri = Uri.parse("https://www.naver.com");
        // 안드로이드 내부에서 정의되어 있는 액션명
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    //전화걸기화면 생성
    public void runDial(View v) {
        Uri uri = Uri.parse("tel:01000000000");
        // 안드로이드 내부에서 정의되어 있는 액션명
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }
    //권한을 체크 - 승인처리
    public void getPermission() {
        //하위 버전이면 실행되지 않도록 처리
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        //모든 권한을 셀프체크
        for(String permission : permission_list) {
            int chk = checkCallingOrSelfPermission(permission);
            if(chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permission_list, 0);
                break;
            }
        }
    }
    //실제 전화를 걸기 위한 메서드
    public void runCallPhone(View v) {
        Intent intent = null;
        int chk = PermissionChecker.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        if(chk == PackageManager.PERMISSION_GRANTED) {
            Log.d("tel","성공");
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5556"));
        } else {
            Log.d("tel","실패");
            return;
        }
        startActivity(intent);
    }
}
