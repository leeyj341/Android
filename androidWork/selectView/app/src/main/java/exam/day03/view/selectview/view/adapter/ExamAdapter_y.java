package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExamAdapter_y extends ArrayAdapter<ActorItem_y> {
    private Context context;
    private int resId;
    private ArrayList<ActorItem_y> dataList;
    private HashMap<Integer, SaveActorState> saveData = new HashMap<Integer, SaveActorState>();

    public ExamAdapter_y(Context context, int resId, ArrayList<ActorItem_y> dataList) {
        super(context, resId, dataList);
        this.context = context;
        this.resId = resId;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public ActorItem_y getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        ActorItem_y actorItem = dataList.get(position);

        if(actorItem != null) {
            ImageView imageView = holder.imageView;
            TextView nameView = holder.nameView;
            TextView dateView = holder.dateView;
            TextView checkBoxText = holder.checkTextView;
            final CheckBox checkBox = holder.checkBox;

            imageView.setImageResource(actorItem.myImg);
            nameView.setText(actorItem.name);
            dateView.setText(actorItem.date);
            checkBoxText.setText(actorItem.checkBoxName);

            SaveActorState state = saveData.get(position);
            if(state != null) {
                checkBox.setChecked(state.isChecked);
            } else {
                checkBox.setChecked(false);
            }

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveData.put(position, new SaveActorState(checkBox.isChecked()));
                }
            });
            /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    boolean curChecked = isChecked;
                    saveData.put(position, new SaveActorState(curChecked));
                }
            });*/
        }

        return convertView;
    }
}
