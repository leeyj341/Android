package multi.android.material_design_pro.circleexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro.R;

public class CircleImageRecyclerTest extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_recycler_test);
        recyclerView = findViewById(R.id.exam_recycler_view);

        List<CircleItem> imgCodeList = new ArrayList<CircleItem>();
        imgCodeList.add(new CircleItem(R.drawable.lee));
        imgCodeList.add(new CircleItem(R.drawable.gong));
        imgCodeList.add(new CircleItem(R.drawable.jung));
        imgCodeList.add(new CircleItem(R.drawable.jang));
        imgCodeList.add(new CircleItem(R.drawable.so));

        RecyclerCircleAdapter adapter = new RecyclerCircleAdapter(this,
                                                                R.layout.circle_item,
                                                                imgCodeList);

        /*LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
