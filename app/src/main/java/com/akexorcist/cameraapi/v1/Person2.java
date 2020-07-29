package com.akexorcist.cameraapi.v1;

import java.io.Serializable;

public class Person2 implements Serializable {
    private  static  final long serialVersionUID = 1L;

    private String file2;
   // private String file3;
    public void setFile(String person2){
        this.file2 = person2;
        //this.file3 = person2;
    }
    public String toString(){

        return file2;

    }


}
