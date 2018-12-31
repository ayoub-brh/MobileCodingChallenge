package com.example.asusrog.mobilecodingchallenge;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    // Declaration of the global variables

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private List<Repos> mRepos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the id of the RecyclerView
        mRecyclerView = findViewById(R.id.RecyclerView);
        // Attach the the RecyclerView to the layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // create an arraylist where we're gonna put all of your objects
        mRepos = new ArrayList<>();
        // Fill up the arraylist with the data
        new RetrieveData().execute();
        // create an adapter for the objects in the RecyclerView
        mAdapter = new ImageAdapter(MainActivity.this, mRepos);
        // set the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }

        class RetrieveData extends AsyncTask<Void,Void,Boolean> {
        // declaration of the local variables used inside the method doInBackground()
            List<String> names,descriptions,login,avatars;
            List<Integer> stars;
            InputStream httpcon;

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    // get the API URL
                    URL url = new URL("https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc");
                    // connect to the url
                    URLConnection request = url.openConnection();
                    request.connect();
                    // get all the data
                    httpcon = (InputStream) request.getContent();
                    // and put it in a variable called in
                    BufferedReader in = new BufferedReader(new InputStreamReader(httpcon));
                    // a String variable where you put all the text
                    String line = "", inputLine;
                    // Loop to fill the variable
                    while ((inputLine = in.readLine()) != null) {
                        line += "\n" + inputLine;
                    }
                    // stop the BufferedReader
                    in.close();

                    // Get all the repository names and put them in a list
                    names = Arrays.stream(line.split("\"name\":")).skip(1)
                            .map(x -> x.split("\"")[1])
                            .map(x -> x.split("\",")[0])
                            .filter(x -> !"MIT License".equals(x))
                            .filter(x -> !"Apache License 2.0".equals(x))
                            .filter(x -> !"GNU General Public License v3.0".equals(x))
                            .filter(x -> !"GNU Affero General Public License v3.0".equals(x))
                            .filter(x -> !"Creative Commons Zero v1.0 Universal".equals(x))
                            .filter(x -> !"Other".equals(x)).collect(Collectors.toList());

                    // get all the descriptions

                    descriptions = Arrays.stream(line.split("\"description\":")).skip(1)
                            .map(x -> x.split("\"")[1])
                            .map(x -> x.split("\",")[0]).collect(Collectors.toList());

                   // get all the users images

                    avatars = Arrays.stream(line.split("\"avatar_url\":")).skip(1)
                            .map(x -> x.split("\"")[1])
                            .map(x -> x.split("\",")[0]).collect(Collectors.toList());


                    // get all the usernames

                    login = Arrays.stream(line.split("\"login\":")).skip(1)
                            .map(x -> x.split("\"")[1])
                            .map(x -> x.split("\",")[0]).collect(Collectors.toList());


                    // get the number of stars

                    stars = Arrays.stream(line.split("\"stargazers_count\":")).skip(1)

                            .mapToInt(x -> Integer.parseInt(x.split(",")[0])).boxed()
                            .collect(Collectors.toList());


                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            }

            @Override
            protected void onPostExecute(Boolean booleans) {
                // a For loop to fill the list
                for(int i=0; i<30;i++){
                    double number = 0.0;
                    String num=null;
                    // if conditions to change the number format
                    if(stars.get(i)>1000000)
                    {
                        number = stars.get(i)/1000000.0;
                        num = String.format("%.1f",number)+"m";
                    }
                    else if(stars.get(i)>1000)
                    {
                        number = stars.get(i)/1000.0;
                        num = String.format("%.1f",number)+"k";
                    }
                    else {number=stars.get(i);}
                    // create an object using the constructor
                    Repos repos = new Repos(names.get(i),descriptions.get(i),avatars.get(i),num,login.get(i));
                    // add that object to the list
                    mRepos.add(i,repos);
                    // notify the changes
                    mAdapter.notifyDataSetChanged();
                }

            }
        }


    }

