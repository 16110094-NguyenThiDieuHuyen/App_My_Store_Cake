package com.example.mystorecake.model;

import java.io.Serializable;

public class SanPhamModel implements Serializable {
    public int ID;
    public String TenSanPham;
    public Integer Gia;
    public String Hinh;
    public String MoTa;
    public int IdLoai;
    public SanPhamModel(int ID, String tenSanPham, Integer gia, String hinh, String moTa, int idLoai) {
        this.ID = ID;
        TenSanPham = tenSanPham;
        Gia = gia;
        Hinh = hinh;
        MoTa = moTa;
        IdLoai = idLoai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public Integer getGia() {
        return Gia;
    }

    public void setGia(Integer gia) {
        Gia = gia;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getIdLoai() {
        return IdLoai;
    }

    public void setIdLoai(int idLoai) {
        IdLoai = idLoai;
    }





}
