package com.example.demojavaproject.model;


import java.io.Serializable;

public class Member implements Serializable {

    private int part;
    private String size;

    public Member(int part, String size){
        this.part = part;
        this.size = size;
    }


    @Override
    public String toString() {
        return "Member " +
                " part=" + part +
                ", size='" + size + '\'';
    }
}
