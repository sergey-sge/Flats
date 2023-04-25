package com.gmail.sge.serejka;

import java.util.List;

public interface FlatDAO {
    void createTable();
    List<Flat> getAll();
    void addFlat(Flat flat);
    List<Flat> getByRooms(int rooms);
    List<Flat> getByMinPrice(int price);
    List<Flat> getByMaxPrice(int price);
    List<Flat> getByMinArea(double area);
    List<Flat> getByMaxArea(double area);
    List<Flat> getByDistrict(String district);
    List<Flat> getByAddress(String address);
    Flat getById(int id);
}
