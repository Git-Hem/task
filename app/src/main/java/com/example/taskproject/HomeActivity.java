package com.example.taskproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.taskproject.Adapters.Adapter;
import com.example.taskproject.Modals.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private ArrayList<Category> mCategorylist;
    private RequestQueue requestQueue;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mCategorylist = new ArrayList<>();
        mContext = getApplicationContext();

      /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsmart.biz/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Category>> call = apiInterface.getCategory();

        call.enqueue(new Callback<List<Category>>() {


            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                mCategorylist = (ArrayList<Category>) response.body();
                Log.d("size", String.valueOf(mCategorylist.size()));
                mAdapter = new Adapter(mContext, mCategorylist);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                //Log.d("tag","onErrorResponse:"+ Error.getMessage(e));
                Toast.makeText(HomeActivity.this, "Inavalid data", Toast.LENGTH_LONG).show();

            }

        });*/


         requestQueue = Volley.newRequestQueue(this);
         parseJason();


    }

    private void parseJason() {
        String url ="https://jsmart.biz/customer/category.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("categories");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String sl_no = jsonObject.getString("slno");
                                String category_text = jsonObject.getString("category");
                                String image_view = jsonObject.getString("image");

                                mCategorylist.add(new Category(image_view, category_text, sl_no));
                                Log.d("size", String.valueOf(mCategorylist.size()));


                                mAdapter = new Adapter(HomeActivity.this, mCategorylist);
                                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setAdapter(mAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse:"+ error.getMessage());
                Toast.makeText(HomeActivity.this,"Inavlid data",Toast.LENGTH_LONG).show();

            }

        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("tag","category");
                return  params;
            }
        };



        requestQueue.add(jsonObjectRequest);
    }

}