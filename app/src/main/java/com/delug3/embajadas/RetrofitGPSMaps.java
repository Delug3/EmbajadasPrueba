package com.delug3.embajadas;

import com.delug3.embajadas.POJO.RespuestaGPS;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RetrofitGPSMaps {

    //Get API datos madrid
    //listado embajas datos.madrid
    //https://datos.madrid.es/egob/catalogo/201000-0-embajadas-consulados.json

    @GET("https://datos.madrid.es/egob/catalogo/201000-0-embajadas-consulados.json?sensor=true&key=AIzaSyB67X4TAO5eeXwEcQAd5bXRshj-9W_8ckU")
    Call<RespuestaGPS> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);

}
