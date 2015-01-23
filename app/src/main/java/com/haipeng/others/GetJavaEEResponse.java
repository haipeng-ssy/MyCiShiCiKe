package com.haipeng.others;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haipeng.cishicike.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetJavaEEResponse extends ActionBarActivity {

    Button btn;
    TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_java_eeresponse);
        tt = (TextView) findViewById(R.id.getResult);
        final String url = "http://10.12.49.31:8080/cishicikejavaee/getResult?myphone=huawei";
        final String[] params =new String[]{url};
        btn = (Button) findViewById(R.id.btn_getResult);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetResult().execute(params);
            }
        });
    }

    public class GetResult extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet    httpGet    = new HttpGet(params[0]);
            httpGet.setHeader("myphone","huawei");
            StringBuffer stringBuffer = new StringBuffer();
            String htc=null;
            try {

                HttpResponse httpResponse = httpClient.execute(httpGet);
                InputStream is = httpResponse.getEntity().getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String     strResult = bufferedReader.readLine();
                while (strResult!=null)
                {
                    stringBuffer.append(strResult);
                    strResult = bufferedReader.readLine();


                }
                JSONObject jsonObject = new JSONObject(stringBuffer.toString());
                htc = jsonObject.getString("htc");

                bufferedReader.close();

            }catch (Exception e)
            {
              e.printStackTrace();

            }
            return htc;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            tt.setText(str);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_java_eeresponse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
