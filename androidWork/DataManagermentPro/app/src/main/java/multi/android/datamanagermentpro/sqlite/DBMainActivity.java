package multi.android.datamanagermentpro.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import multi.android.datamanagermentpro.R;

//SQL문을 이용해서 작업, SQL문을 문자열로 표현
public class DBMainActivity extends AppCompatActivity {
    EditText id;
    EditText name;
    EditText age;
    TextView result;
    DBHelper dbHelper ; //데이터베이스 파일 생성, 테이블 생성...
    SQLiteDatabase db ; //로컬디비 연동을 위한 핵심클래스
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbjob_main);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        result = findViewById(R.id.result);

        //1.DBHelper 생성
        dbHelper = new DBHelper(this);
        //2.SQLiteDatabase 객체 생성
        db = dbHelper.getWritableDatabase();
    }

    public void insert(View v) {
        String sql = "insert into member(id, name, age) values(?,?,?)";
        db.execSQL(sql, new String[] {
                id.getText().toString(),
                name.getText().toString(),
                age.getText().toString()});

        Toast.makeText(this, "삽입성공", Toast.LENGTH_LONG).show();
    }

    public void selectAll(View v) {
        result.setText("");
        String sql = "select * from member";
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount(); //레코드 개수 반환

        Toast.makeText(this, "조회된 row:" + count, Toast.LENGTH_LONG).show();

        while (cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            int age = cursor.getInt(3);

            result.append("번호: " + idx + "\n"
                    + "아이디: " + id + "\n"
                    + "이름: " + name + "\n"
                    + "나이: " + age + "\n" + "=========================\n");
        }
    }

    public void update(View v) {
        String sql = "update member set age = ? where id = ?";
        db.execSQL(sql, new String[] {
                age.getText().toString(),
                id.getText().toString()
        });

        Toast.makeText(this, "수정 완료", Toast.LENGTH_LONG).show();
    }

    public void delete(View v) {
        String sql = "delete from member where id = ?";
        db.execSQL(sql, new String[] {
                id.getText().toString()
        });

        Toast.makeText(this, "삭제 완료", Toast.LENGTH_LONG).show();
    }

    public void search(View v) {

    }
}
