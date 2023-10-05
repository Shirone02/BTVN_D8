package com.example.btvn_d8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://dummyjson.com/";
    private RecyclerView rvProduct;
    private ProductAdapter mAdapter;
    private List<Product> mListProducts;
    private Retrofit mRetrofit;
    private DummyServices dummyServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListProducts = new ArrayList<>();
        initData();
        initView();

    }

    private void initData() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dummyServices = mRetrofit.create(DummyServices.class);

        dummyServices.getProducts().enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == 200){
                        ProductsResponse productsResponse = response.body();
                        mListProducts = productsResponse.getProducts();
                        mAdapter = new ProductAdapter(mListProducts);
                        rvProduct.setAdapter(mAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        rvProduct = findViewById(R.id.rvProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(gridLayoutManager);

    }
}