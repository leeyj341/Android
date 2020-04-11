package multi.android.datamanagermentpro.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//앱 별로 하나씩 만든다
//데이터베이스가 업데이트되거나
//DB를 처음 생성
public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 3;
    public DBHelper(Context context) {
        //파일형태로 데이터가 저장된다. - 이 자체가 데이터베이스를 오픈하고 연결
        super(context, "test.db", null, DB_VERSION);
    }
    //앱이 설치되고 SQLiteOpenHelper가 최초로 호출될때 한 버만 실행
    //테이블이 생성되고 필요하면 초기화 작업
    //앱을 최초로 다운받는 사람들을 위해 만들어놓은 메서드 - 항상 최신으로 유지
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbtest", "데이터베이스가 생성되었습니다.");

        //테이블 생성 - 스키마의 변경내용을 정의
        String sql = "create table if not exists member("
                + "idx integer primary key autoincrement,"
                + "id text,"
                + "name text,"
                + "age integer)";
        db.execSQL(sql);
    }

    //데이터베이스의 버전이 변경될 때마다 호출되는 메서드
    //스키마가 변경되면 호출되어 업데이트에 관련된 여러가지 처리를 구현
    //기존 사용자들이 변경된 내용을 반영하려 할 때 호출되는 메서드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest", "데이터베이스의 스키마가 변경되었습니다." +
                oldVersion + "==newVersion:" + newVersion);

        switch (oldVersion) {
            case 1:
                //1버전에서 2버전으로 넘어갈 때 처리해야 하는 일들을 구현
                Log.d("dbtest", "2버전으로 변경");
            case 2:
                //2버전에서 3버전으로 넘어갈 때 처리해야 하는 일들을 구현
                Log.d("dbtest", "3버전으로 변경");
            case 3:
                //3버전에서 4버전으로 넘어갈 때 처리해야 하는 일들을 구현
                Log.d("dbtest", "4버전으로 변경");
        }
    }
}
