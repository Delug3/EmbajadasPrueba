package com.delug3.embajadas.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("coordenadas")
    @Expose
    private Coordenadas coordenadas;

    @SerializedName("id")
    @Expose
    private String id;
    //nombre embajada
    @SerializedName("title")
    @Expose
    private String title;
    //calle
    @SerializedName("street-address")
    @Expose
    private String street;
    //tipo:embajadas,colegios...
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    //error aqui?
    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

