package com.oz.simpleapplication.APIInteractions;
import com.oz.simpleapplication.DTO.OrderCreateDTO;
import com.oz.simpleapplication.DTO.OrderDTO;
import com.oz.simpleapplication.DTO.ProductDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @GET("api/order")
    Call<List<OrderDTO>> getOrders();

    @GET("api/product")
    Call<List<ProductDTO>> getProducts();

    @POST("api/order")
    Call<OrderDTO> createOrder(@Body OrderCreateDTO orderDto);
}
