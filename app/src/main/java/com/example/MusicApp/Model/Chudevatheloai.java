package com.example.MusicApp.Model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public  class Chudevatheloai implements Serializable {

@SerializedName("TheLoai")
@Expose
public List<TheLoai> theLoai = null;
@SerializedName("ChuDe")
@Expose
private List<ChuDe> chuDe = null;

public  List<TheLoai> getTheLoai() {
return theLoai;
}

public void setTheLoai(List<TheLoai> theLoai) {
this.theLoai = theLoai;
}

public List<ChuDe> getChuDe() {
return chuDe;
}

public void setChuDe(List<ChuDe> chuDe) {
this.chuDe = chuDe;
}

}