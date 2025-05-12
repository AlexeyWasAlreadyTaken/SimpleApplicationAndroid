package com.oz.simpleapplication.APIInteractions;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oz.simpleapplication.DTO.OrderDTO;
import com.oz.simpleapplication.DTO.ProductDTO;
import com.oz.simpleapplication.DTO.OrderCreateDTO;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIInteractions {
    private final Retrofit _retrofit;
    private final ApiService _apiService;

    public APIInteractions() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss") // ISO 8601
                .create();

        _retrofit = new Retrofit.Builder()
                .baseUrl(APIInteractionsSettings.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        _apiService = _retrofit.create(ApiService.class);
    }

    // Новый интерфейс-колбэк
    public interface ProductListCallback {
        void onSuccess(List<ProductDTO> products);
        void onError(String errorMessage);
    }

    // Асинхронный метод с передачей результата через колбэк
    public void getListOfProducts(ProductListCallback callback) {
        _apiService.getProducts().enqueue(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                callback.onError("Request failed: " + t.getMessage());
            }
        });
    }

    // Интерфейс для создания заказа
    public interface OrderCreateCallback {
        void onSuccess(OrderDTO createdOrder);  // передаём заказ
        void onError(String errorMessage);
    }

    public void createOrder(OrderCreateDTO orderDto, OrderCreateCallback callback) {
        _apiService.createOrder(orderDto).enqueue(new Callback<OrderDTO>() {
            @Override
            public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body()); // передаём объект заказа
                } else {
                    callback.onError("Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<OrderDTO> call, Throwable t) {
                callback.onError("Request failed: " + t.getMessage());
            }
        });
    }
}
