
package com.example.shumbamoneyweather.Model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys implements Serializable
{

    @SerializedName("pod")
    @Expose
    private String pod;
    private final static long serialVersionUID = 6401895013068258805L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param pod
     */
    public Sys(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
