package multi.android.material_design_pro.circleexam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro.R;

public class CardViewRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_recycler_test);
        recyclerView = findViewById(R.id.exam_recycler_view);

        List<CircleItem2> cardList = new ArrayList<CircleItem2>();
        cardList.add(new CircleItem2(R.drawable.lee, "이민호의 신의"));
        cardList.add(new CircleItem2(R.drawable.gong, "공유의 도깨비"));
        cardList.add(new CircleItem2(R.drawable.jung, "정우성의 비트"));
        cardList.add(new CircleItem2(R.drawable.jang, "검색어를 입력하세요"));
        cardList.add(new CircleItem2(R.drawable.so, "미안하다 사랑한다"));

        RecyclerCardAdapter adapter = new RecyclerCardAdapter(this,
                                                                R.layout.activity_card_view_exam,
                                                                cardList);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
