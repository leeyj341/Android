package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import exam.day03.view.selectview.R;
import exam.day03.view.selectview.view.adapter.ActorItem_y;
import exam.day03.view.selectview.view.adapter.ExamAdapter_y;

public class SelectView_ExamActivity_y extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_exam_main);
        ListView listView = findViewById(R.id.exam_listView);

        ActorItem_y actorItem;
        ArrayList<ActorItem_y> actorlist = new ArrayList<ActorItem_y>();
        actorItem = new ActorItem_y(R.drawable.ansoccer,"안정환","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.chasoccer,"차범근","2020/04/06","아들~~");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.jjangkim,"김어준","2020/04/06","찐");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.kbr,"김범룡","2020/04/06","진짜가수");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.android,"김서연","2020/04/06","이뻐");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.kimdong,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.leemin,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.parksoccer,"박지성","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.ansoccer,"안정환","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.chasoccer,"차범근","2020/04/06","아들~~");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.jjangkim,"김어준","2020/04/06","찐");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.kbr,"김범룡","2020/04/06","진짜가수");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.android,"김서연","2020/04/06","이뻐");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.kimdong,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.leemin,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem_y(R.drawable.parksoccer,"박지성","2020/04/06","멋져");
        actorlist.add(actorItem);

        ExamAdapter_y examAdapter = new ExamAdapter_y(this, R.layout.select_exam_row_y, actorlist);

        listView.setAdapter(examAdapter);

    }
}
