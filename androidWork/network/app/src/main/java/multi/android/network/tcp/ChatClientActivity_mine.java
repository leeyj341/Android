package multi.android.network.tcp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import multi.android.network.R;

public class ChatClientActivity_mine extends AppCompatActivity {
    ListView msg_listview ;
    ListView user_listview ;
    EditText msg_edit;
    String nickname;
    Socket socket;
   /* ArrayList<ChatMessage> msg;*/
    ArrayList<String> msglist;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;

    OutputStream os;
    PrintWriter pw;
    Vector<String> userlist = new Vector<String>();
    StringTokenizer token;
    ArrayAdapter  msgadapter;
    ArrayAdapter  useradapter;
    AsyncTaskExam asyncTaskExam;

    Handler writeHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_client);
        msg_listview = findViewById(R.id.chat_list);
        user_listview = findViewById(R.id.user_list);
        msg_edit = findViewById(R.id.msg_edit);
       /* msg = new ArrayList<ChatMessage>();*/
        msglist = new ArrayList<String>();
        msgadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, msglist);
        useradapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userlist);
        msg_listview.setAdapter(msgadapter);
        user_listview.setAdapter(useradapter);
        asyncTaskExam = new AsyncTaskExam();

        writeHandler = new Handler();
    }
	//닉네임 입력 버튼을 누르면 호출되는 메소드
    public void nickname_input(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setTitle("데이터입력");
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.nick_name_view,null);
        builder.setPositiveButton("확인",new DialogListener());
        builder.setNegativeButton("취소",null);
        builder.setView(dialogView);
        builder.show();
    }

	//서버저속버튼을 누르면 호출되는 메소드
    public void server_connect(View view){
        asyncTaskExam.execute(10,20);
    }
    public void btn_send(View view){
        sendMessage("chatting/"+msg_edit.getText().toString()
                +"/"+nickname);
        msg_edit.setText("");
    }
    public void sendMessage(final String message) {
        //메시지를 서버에 전송할 수 있도록 작성하세요
        //Log.d("test", message);
        new Thread(new Runnable() {
            @Override
            public void run() {
                pw.println(message);
                pw.flush();
            }
        }).start();
    }

	//nickname을 다이얼로그를 통해서 입력받도록 구현한 리스너
    class DialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog inputAlert = (AlertDialog)dialog;
            EditText edit = inputAlert.findViewById(R.id.nickname);
            nickname = edit.getText().toString();
            userlist.add(nickname);
            useradapter.notifyDataSetChanged();
        }
    }



    //서버로부터 데이터 읽기
    class AsyncTaskExam extends AsyncTask<Integer,String,String> {
        @Override
        protected String doInBackground(Integer... integers) {
            //서버와 접속하여 서버가 보내오는 메시지를 읽을 수 있도록 작성하세요
            try {
                socket = new Socket("70.12.116.62", 12345);
                ioWork();
                sendMsg(nickname);
                userlist.add(nickname);

                while (true) {
                    String msg = br.readLine();
                    filteringMsg(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";

        }
        public void ioWork(){
            try {
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                os = socket.getOutputStream();
                pw = new PrintWriter(os,true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void sendMsg(String msg){
            Log.d("test","클라이언트가 서버에게 메시지 전송: "+ msg);
            pw.println(msg);
        }

        private void filteringMsg(String msg){
            token = new StringTokenizer(msg,"/");
            String protocol = token.nextToken();
            String message = token.nextToken();
            System.out.println("프로토콜:"+protocol+",메시지:"+message);
            if(protocol.equals("new")){
                userlist.add(message);
				//내용을 추가하세요.
                for (int i = 0; i < userlist.size(); i++) {
                    publishProgress("******* " + userlist.get(i) + "님이 입장하셨습니다. *******\n");
                }
            }else if(protocol.equals("old")){
                userlist.add(message);
				 //내용을 추가하세요.
                for (int i = 0; i < userlist.size(); i++) {
                    publishProgress("******* " + userlist.get(i) + "님이 입장하셨습니다. *******\n");
                }
            }else if(protocol.equals("chatting")){
                String nickname = token.nextToken();
               //내용을 추가하세요.
                publishProgress(nickname + " >> " + message + "\n");
            }else if(protocol.equals("out")){
                userlist.remove(message);
              //내용을 추가하세요.
                publishProgress(message + "님이 퇴장하셨습니다.\n");
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            //코드를 추가하세요
            Log.d("test", values[0]);
            msglist.add(values[0]);
            writeHandler.post(new Runnable() {
                @Override
                public void run() {
                    msgadapter.notifyDataSetChanged();
                    useradapter.notifyDataSetChanged();
                }
            });


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
