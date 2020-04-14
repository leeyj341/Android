package multi.android.support_lib.fragment.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import multi.android.support_lib.R;

public class ExamFragmentActivity extends AppCompatActivity {
    ViewFragment1 viewFragment1 = new ViewFragment1();
    ViewFragment2 viewFragment2 = new ViewFragment2();
    ViewFragment3 viewFragment3 = new ViewFragment3();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear02);

        /*Button btn1 = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment("first");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment("second");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment("third");
            }
        });*/


    }

    public void changeFragment(String name) {
        FragmentManager manager = getSupportFragmentManager();
        //프래그먼트의 변화를 관리하는 객체
        FragmentTransaction transaction = manager.beginTransaction();

        switch (name) {
            case "first":
                transaction.replace(R.id.container, viewFragment1);
                break;
            case "second":
                transaction.replace(R.id.container, viewFragment2);
                break;
            case "third":
                transaction.replace(R.id.container, viewFragment3);
                break;
        }

        transaction.commit();
    }
}
