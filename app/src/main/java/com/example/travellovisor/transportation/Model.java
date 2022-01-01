package com.example.travellovisor.transportation;

public class Model {
    private String id;
    private String name;

    public Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
