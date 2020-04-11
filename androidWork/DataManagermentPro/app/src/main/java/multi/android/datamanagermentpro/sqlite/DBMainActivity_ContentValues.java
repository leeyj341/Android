package multi.android.datamanagermentpro.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import multi.android.datamanagermentpro.R;

//ContentValues라는 값을 관리하는 객체를 이용해서 안드로이드의 메서드를 통해
//SQL문이 실행될 수 있도록 작업
//메서드를 호출하며 적절한 값을 넘겨주면 안드로이드OS에서 SQL문을 만들어 실행
//insert, query, delete, update
public class DBMainActivity_ContentValues extends AppCompatActivity {
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
        //컬럼에 저장할 값을 관리하는 ContentValues를 이용
        //Map 구조
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id.getText().toString());
        contentValues.put("name", name.getText().toString());
        contentValues.put("age", age.getText().toString());

        db.insert("member",null, contentValues);

        Toast.makeText(this, "ContentValues 삽입성공", Toast.LENGTH_LONG).show();
    }

    public void selectAll(View v) {
        result.setText("");
        String sql = "select * from member";
        // 테이블 명, 조회할 컬럼명(문자열 배열), 조건(where다음에 오는 문자열 : id = ?)
        //조건으로 정의된 ?에 바인딩할 값을 배열로 넘긴다.
        //group by절 뒤에 정의할 컬럼명
        //having에 정의할 조건
        //order by절에 정의할 정렬기준
        Cursor cursor = db.query("member", null, null, null, null, null, null);
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
        ContentValues contentValues = new ContentValues();
        //contentValues는 set절 - 변경할 데이터의 name과 value
        contentValues.put("age", age.getText().toString());
        String where = "id like ?";
        String[] whereVal = {"%" + id.getText().toString() + "%"};
        db.update("member", contentValues, where, whereVal);

        Toast.makeText(this, "ContentValues 수정 완료", Toast.LENGTH_LONG).show();
    }

    public void delete(View v) {
        db.delete("member", "id = ?", new String[] {
                id.getText().toString()
        });

        Toast.makeText(this, "ContentValues 삭제 완료", Toast.LENGTH_LONG).show();
    }

    public void search(View v) {

    }
}
