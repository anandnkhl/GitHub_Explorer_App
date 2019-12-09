package com.example.nikhilanand.githubexplorer;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static String usrname = "";
    public String nameOfUser = "";
    public String created_at = "";
    public String public_repos = "";
    public String picURL = "";
    public String reposURL = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        final Button button = findViewById(R.id.button);
        final EditText e1 = (EditText) findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                usrname = e1.getText().toString();

                HttpURLConnection urlConnection;
                URL url;
                InputStream inputStream;

                try{
                    url = new URL("https://api.github.com/users/"+usrname);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);
                    urlConnection.connect();
                    int httpStatus = urlConnection.getResponseCode();

                    if (httpStatus != HttpURLConnection.HTTP_OK) {
                        inputStream = urlConnection.getErrorStream();
                        Map<String, List<String>> map = urlConnection.getHeaderFields();
                        System.out.println("Printing Response Header...\n");
                        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                            System.out.println(entry.getKey()
                                    + " : " + entry.getValue());
                        }
                    }else {
                        inputStream = urlConnection.getInputStream();
                    }
                    
                    //read inputstream
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String temp,response="";
                    while((temp = bufferedReader.readLine())!=null){
                        response+=temp;
                    }

                    boolean apiLimitExceeded = false;
                    if(response.contains("API rate limit exceeded")){
                        apiLimitExceeded =true;
                    }else {
                        //convert data string into JSONObject
                        JSONObject obj = (JSONObject) new JSONTokener(response).nextValue();
                        nameOfUser = obj.getString("name");
                        picURL = obj.getString( "avatar_url" );
                        reposURL = obj.getString( "repos_url" );
                        created_at = obj.getString( "created_at" );
                        public_repos = obj.getString( "public_repos" );

                    }
                    urlConnection.disconnect();

                }catch(Exception e){
                    System.out.println("------->Exception"+e);
                    Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_SHORT).show();

                }

                System.out.println(nameOfUser);
                System.out.println(picURL);
                System.out.println(reposURL);



                Intent tabsIntent = new Intent(MainActivity.this,ProfileRepoActivity.class);
                Bundle b = new Bundle( );
                b.putString( "nameOfUser", nameOfUser );
                b.putString( "picURL", picURL );
                b.putString( "reposURL", reposURL );
                b.putString( "created_at", created_at );
                b.putString( "public_repos", public_repos );
                tabsIntent.putExtras(b);
                MainActivity.this.startActivity(tabsIntent);
                MainActivity.this.finish();
            }
        });
    }



}
