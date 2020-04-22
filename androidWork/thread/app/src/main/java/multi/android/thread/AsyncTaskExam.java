package multi.android.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskExam extends AppCompatActivity {
    Button button1;
    Button button2;
    TextView result;
    ProgressBar progressBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_exam);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        result = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);

    }
    public void ck_bt(View view){
        if(view.getId() == R.id.button) {
            MyAsyncTask asyncTask = new MyAsyncTask();
            asyncTask.execute();
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            button1.setEnabled(false);
            button2.setEnabled(true);
            progressBar.setMax(50);
        }

        //멀티쓰레드로 작업할 내용을 이곳에 정의
        @Override                       //가변
        protected Integer doInBackground(Void... voids) {
            int sum = 0;
            for (int i = 1; i < 50; i++) {
                sum += i;
                SystemClock.sleep(100);
                publishProgress(i);
            }
            return sum;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            result.setText(Integer.toString(values[0]));
            progressBar.setProgress(values[0]);
            if(values[0] % 2 == 0) {
                imageView.setImageResource(R.drawable.d1);
            } else {
                imageView.setImageResource(R.drawable.d2);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Integer sum) {
            super.onPostExecute(sum);
            result.setText("Result:" + sum);
            button1.setEnabled(true);
            button2.setEnabled(false);
        }
    }
}
