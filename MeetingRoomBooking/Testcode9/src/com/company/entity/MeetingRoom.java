package com.company.entity;

import java.util.EnumSet;

public class MeetingRoom {
    private String id;
    private int capacity;
    private EnumSet<Features> features;

    public MeetingRoom(String id, int capacity, EnumSet<Features> features) {
        this.id = id;
        this.capacity = capacity;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EnumSet<Features> getFeatures() {
        return features;
    }

    public void setFeatures(EnumSet<Features> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", features=" + features +
                '}';
    }
}
