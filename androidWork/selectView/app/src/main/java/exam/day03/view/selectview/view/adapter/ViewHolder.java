package exam.day03.view.selectview.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import exam.day03.view.selectview.R;

public class ViewHolder {
    ImageView imageView;
    TextView nameView;
    TextView dateView;
    TextView checkTextView;
    CheckBox checkBox;

    public ViewHolder(View parentView) {
        this.imageView = parentView.findViewById(R.id.examImg);
        this.nameView = parentView.findViewById(R.id.examName);
        this.dateView = parentView.findViewById(R.id.examDate);
        this.checkTextView = parentView.findViewById(R.id.examCheckText);
        this.checkBox = parentView.findViewById(R.id.examCheck);
    }
}
