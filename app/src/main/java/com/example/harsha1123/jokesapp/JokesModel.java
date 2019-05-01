package com.example.harsha1123.jokesapp;

import java.util.List;

public class JokesModel {
    List<String> list1;

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public JokesModel(List<String> list) {
        list1=list;
    }
}
