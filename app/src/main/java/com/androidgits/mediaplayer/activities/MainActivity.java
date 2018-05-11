package com.androidgits.mediaplayer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidgits.mediaplayer.R;
import com.androidgits.mediaplayer.adapter.PostAdapter;
import com.androidgits.mediaplayer.model.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String URL_JSON = "https://firebasestorage.googleapis.com/v0/b/fireapp-3039a.appspot.com/o/WebService.json?alt=media&token=f39f9c5f-25f6-4c93-b20a-3fc43b595240";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Post> lstPost = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        jsoncall();
    }

    public void jsoncall() {
        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try{
                        jsonObject = response.getJSONObject(i);
                        Post post = new Post(); //model class object
                        post.setImage_url(jsonObject.getString("image_url"));
                        post.setUsername(jsonObject.getString("username"));
                        post.setLikes(jsonObject.getString("likes"));
                        post.setStatus(jsonObject.getString("status"));
                        post.setTimeStamp(jsonObject.getString("TimeStamp"));
                        lstPost.add(post);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                setPostAdapter(lstPost);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "There Was An Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ArrayRequest);
    }

    public void setPostAdapter(List<Post> mData){
        PostAdapter postAdapter = new PostAdapter(this,mData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }
}
