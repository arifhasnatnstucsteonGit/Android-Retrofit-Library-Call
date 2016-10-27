package com.example.hc4u.androidretrofitpractice.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hc4u.androidretrofitpractice.Adapter.GitAdapter;
import com.example.hc4u.androidretrofitpractice.R;
import com.example.hc4u.androidretrofitpractice.interfaces.GitRetrofitCall;
import com.example.hc4u.androidretrofitpractice.models.GitModel;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {


    public String  url ="https://api.github.com/";
    public TextView textView;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)




        getRetrofitObject();



    }


    void getRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitRetrofitCall service = retrofit.create(GitRetrofitCall.class);

        Call<GitModel> call = service.getStudentDetails();

        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Response<GitModel> response, Retrofit retrofit) {


                ArrayList<GitModel> arrayListGit= new ArrayList<GitModel>();
                GitModel gitModel = new GitModel();
                gitModel = response.body();
                arrayListGit.add(gitModel);

                for (int i = 0; i <arrayListGit.size() ; i++) {
                    arrayListGit.get(i).getBio();

                }


                mAdapter = new GitAdapter(arrayListGit);
                mRecyclerView.setAdapter(mAdapter);

               /* Toast.makeText(MainActivity.this, arrayListGit.toString(), Toast.LENGTH_SHORT).show();


                textView.setText(arrayListGit.toString());

                String data = response.body().getBlog();*/

               // Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(MainActivity.this, response.body().getCompany().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
