package com.akexorcist.cameraapi.v1;

import java.io.Serializable;

public class Persontoblak implements Serializable {

    private  static  final long serialVersionUID = 1L;


    private String file3;

    public void setData(String persontoblak) {
        this.file3= persontoblak;

    }
    public String toString(){
        return file3;
    }


}

