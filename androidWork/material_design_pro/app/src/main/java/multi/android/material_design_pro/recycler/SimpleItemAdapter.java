package multi.android.material_design_pro.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.material_design_pro.R;
//RecyclerView에서 사용하는 Adapter를 커스터마이징
//Adapter안에 ViewHolder포함 - 정의 (ListView사용할 때와 동일한 역할)
//          -----------
//              ^
//              Inner Class로 정의

public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ViewHolder> {
    Context context;
    int row_res_id; //row를 구성하는 layout
    List<SimpleItem> data; //RecyclerView에 출력될 전체 데이터

    public SimpleItemAdapter(Context context, int row_res_id, List<SimpleItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }

    //xml로 부터 뷰(한 row에 대한)를 만들어서 ViewHolder넘기는 작업
    //View를 구성하는 구성요소의 리소스를 가져오는 작업을 하는 객체
    //1. onCreateViewHolder에서 row에 대한 뷰를 inflate해서 생성
    //2. ViewHolder객체를 만들어서 1번에서 생성한 뷰를 넘긴다.
    //3. ViewHolder객체 안에서 onCreateViewHolder메서드에서 리턴받은 객체에서 데이터를 연결할 뷰를 찾아온다.
    //4. onBindViewHolder메서드에서 ViewHolder가 갖고 있는 구성요소에 데이터를 연결하기
    @NonNull
    @Override
    public SimpleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleItemAdapter.ViewHolder holder, int position) {
        Log.d("recycler", "onBindViewHolder" + position);
        //ViewHolder가 찾아놓은 TextView를 꺼내고
        //꺼낸 데이터 TextView에 연결
        holder.txt.setText(data.get(position).data);
        //TextView에 클릭 이벤트 연결
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "데이터 연결 완료", Toast.LENGTH_LONG).show();
            }
        });
    }

    //RecyclerView에 출력할 데이터의 갯수 리턴
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.itemview);
        }
    }
}
