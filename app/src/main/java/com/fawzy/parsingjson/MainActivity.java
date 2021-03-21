package com.fawzy.parsingjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView name , salary ;
    RecyclerView recyclerView ;
    ImageView header ;
   // String Name , Salary ;
   // String Json_String = "{\"employee\":{\"name\":\"Master Coding\",\"salary\":5000}}" ;
   // private static  String JSON_URL = "https://run.mocky.io/v3/33525be8-f553-462c-81ff-ae8c6fd65655" ;

     /*  ArrayList<String> names = new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> mob_numbers = new ArrayList<>();*/


    private  static  String Movie_URL = "https://api.themoviedb.org/3/movie/popular?api_key=2954cbd3cd0e7607d82e2e5b629f1fee" ;
    private ArrayList<MovieModelClass> movieModelClasses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);*/

        recyclerView = findViewById(R.id.rec);
        header = findViewById(R.id.img);

        Glide.with(this).load(R.drawable.header).into(header);
        GetData getData = new GetData();
        getData.execute();

        // getJsonObject from Json File .
        /*try{
            // parsing JSON from string
            JSONObject object = new JSONObject(Json_String);
            // fet JsonObject named employee
            JSONObject employee = object.getJSONObject("employee");
            Name = employee.getString("name");
            Salary = String.valueOf(employee.getInt("salary"));
            name.setText("Name:"+Name);
            salary.setText("Salary:"+Salary);*/

            // parsing JSON from assets
           /* JSONObject jsonObject = new JSONObject(loadJsonfromAssets());
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            for (int i =0 ; i<jsonArray.length();i++){
                JSONObject userdetails = jsonArray.getJSONObject(i);
                String name = userdetails.getString("name");
                String email = userdetails.getString("email");
                names.add(name);
                emails.add(email);
                JSONObject contact = userdetails.getJSONObject("contact");
                String mobile = contact.getString("mobile");
                mob_numbers.add(mobile);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setHasFixedSize(true);
                Adapter adapter = new Adapter(this,names,emails,mob_numbers);
                recyclerView.setAdapter(adapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/










    }

    public class GetData extends AsyncTask<String,String,String>{
       // parsing JSON using url
        @Override
        protected String doInBackground(String... strings) {
          return LoadJsonfromURL() ;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i=0 ; i < jsonArray.length() ; i++){
                    JSONObject data = jsonArray.getJSONObject(i);
                    String Title = data.getString("title");
                    String Vote = data.getString("vote_average");
                    String Image = data.getString("poster_path");
                    MovieModelClass movieModelClass = new MovieModelClass(Title,Vote,Image);
                    movieModelClasses.add(movieModelClass);
                    PutDatainRecycler(movieModelClasses);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }


    private void PutDatainRecycler(ArrayList<MovieModelClass> movieModelClasses){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        Adapter adapter = new Adapter(this,movieModelClasses);
        recyclerView.setAdapter(adapter);
    }


    private String LoadJsonfromURL(){
        String current = " " ;
        try {
            URL url ;
            HttpURLConnection urlConnection = null ;
            try {
                url = new URL(Movie_URL);
                urlConnection = (HttpURLConnection) url.openConnection() ;
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1){
                    current += (char) data ;
                    data = inputStreamReader.read();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return current;
    }

    private String loadJsonfromAssets(){
        String json = null ;
        try {
            InputStream inputStream = getAssets().open("JSON.json");
            int size = inputStream.available();
            byte [] buffer = new byte[size] ;
            inputStream.read(buffer);
            inputStream.close();
             json = new String(buffer,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return json ;
    }



}