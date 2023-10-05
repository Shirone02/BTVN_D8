package com.example.btvn_d8;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyServices {
    @GET("products")
    Call<ProductsResponse> getProducts();
}
