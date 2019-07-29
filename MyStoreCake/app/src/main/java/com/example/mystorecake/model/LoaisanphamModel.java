package com.example.mystorecake.model;

public class LoaisanphamModel {
    public int id;
    public String tenLoai;
    public String hinh;

    public LoaisanphamModel(int id, String tenLoai, String hinh) {
        this.id = id;
        this.tenLoai = tenLoai;
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
