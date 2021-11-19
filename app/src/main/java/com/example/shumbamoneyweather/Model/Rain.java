
package com.example.shumbamoneyweather.Model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rain implements Serializable
{

    @SerializedName("3h")
    @Expose
    private Double rainfall;
    private final static long serialVersionUID = 8085043595117152890L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rain() {
    }

    /**
     * 
     * @param rainfall
     */
    public Rain(Double rainfall) {
        super();
        this.rainfall = rainfall;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

}
