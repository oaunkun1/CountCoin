package com.akexorcist.cameraapi.v1;

import java.io.Serializable;

public class Person implements Serializable {
    private  static  final long serialVersionUID = 1L;


    private String file;

    public void setFile(String person) {
        this.file = person;

    }
    public String toString(){
        return file;
    }



}
