package com.gmail.sge.serejka;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlatDAOImpl implements FlatDAO {
    private final Connection connection;

    public FlatDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createTable() {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE IF EXISTS Flats");
                statement.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "district VARCHAR(15) NOT NULL, address VARCHAR(30) NOT NULL, area double NOT NULL," +
                        "rooms int NOT NULL, price int NOT NULL);");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFlat(Flat flat) {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("INSERT INTO Flats (district, address,area,rooms,price) VALUES ('" +
                        flat.getDistrict() + "', '" + flat.getAddress() + "', " + flat.getArea() + ", " +
                        flat.getRooms() + ", " + flat.getPrice() + ");");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Flat> getAll() {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats")) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByRooms(int rooms) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE rooms = " + rooms)) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByMinPrice(int price) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE price > " + price)) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByMaxPrice(int price) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE price < " + price)) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByMinArea(double area) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE area > " + area)) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByMaxArea(double area) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE area < " + area)) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByDistrict(String district) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE district = '" + district + "'")) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Flat> getByAddress(String address) {
        List<Flat> list = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE ADDRESS = '" + address + "'")) {
                    while (rs.next()) {
                        Flat flat = new Flat();
                        setFlat(flat,rs);
                        list.add(flat);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Flat getById(int id) {
        Flat flat = new Flat();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery("SELECT * FROM Flats WHERE ID = " + id)) {
                    while (rs.next()) {
                        setFlat(flat,rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flat;
    }

    private void setFlat(Flat flat, ResultSet rs) throws SQLException {
        flat.setId(rs.getInt(1));
        flat.setDistrict(rs.getString(2));
        flat.setAddress(rs.getString(3));
        flat.setArea(rs.getDouble(4));
        flat.setRooms(rs.getInt(5));
        flat.setPrice(rs.getInt(6));
    }
}