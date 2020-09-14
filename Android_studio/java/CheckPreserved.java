package com.example.electronic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CheckPreserved extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.102.202";   // 서버 아이피
    private static String TAG = "phpexample";

    private EditText mEditTextSearchKeyword;

    private TextView mTextCheck;   // 결과 출력
    private TextView mTextDelete;   // 취소 결과 출력
    private Button mDelete;      // 예약 취소 버튼 제어

    private String mJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_preserved);

        mEditTextSearchKeyword = (EditText) findViewById(R.id.editText_check_phone);

        mTextCheck = (TextView)findViewById(R.id.textView_check_name);  // 출력 추가
        mTextDelete = (TextView)findViewById(R.id.textView_check_delete);  // 취소 결과 출력
        mDelete = (Button)findViewById(R.id.button_check_delete);

        Button button_search = (Button) findViewById(R.id.button_check_preserved);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Keyword =  mEditTextSearchKeyword.getText().toString();  // 내용 읽기                mEditTextSearchKeyword.setText("");

                GetData task = new GetData();
                task.execute( "http://" + IP_ADDRESS + "/query.php", Keyword);
            }
        });

    }

    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;  // 다이얼로그
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CheckPreserved.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);           // 포스트가 실행됨!

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            if (result == ""){
                mTextCheck.setText("예약 내역이 없습니다.");
               // 에러
            }
            else {

                mJsonString = result;    // echo 결과를 제이슨파일로 처리
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String postParameters = "phone=" + params[1];  // 이거는 인덱싱때문에 붙여준거


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString().trim();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult(){

        String TAG_JSON="webnautes";
        String TAG_NAME = "name";
        String TAG_P_TIME = "p_time";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                final String name = item.getString(TAG_NAME);   // final 써줘야 버튼에서 이름인식함
                final String p_time = item.getString(TAG_P_TIME);

                mTextCheck.setText("'" + name + "'님 '" + p_time + "'시에 예약되어 있습니다.");
                mDelete.setVisibility(View.VISIBLE);    // 삭제 버튼 보이게함


                Button button_delete = mDelete;
                button_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {   // 버튼누를시 동작
                        GetData_delete task = new GetData_delete();
                        task.execute( "http://" + IP_ADDRESS + "/delete.php", name,p_time);
                    }
                });

            }

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }///////////////////////////////////////////////////////////////////////////////////////

    //삭제하는 테스크
    private class GetData_delete extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CheckPreserved.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            if (result == ""){  // php 에서 echo 한 내용이 result 로 온다!
                mTextDelete.setText(result);

            }
            else
            {
                mTextDelete.setText(result);
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String name = (String)params[1];    // 주의 해야함
            String p_time = (String) params[2];

            String postParameters = "name=" + name + "&p_time=" + p_time;
            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString().trim();

            } catch (Exception e) {

                Log.d(TAG, "DeleteData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

}
