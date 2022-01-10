package com.company.Entity;

import java.util.HashMap;

public class OnDiskDb {
    HashMap<String, Document> diskDb;

    public OnDiskDb() {
        diskDb = new HashMap<>();
    }

    public HashMap<String, Document> getDiskDb() {
        return diskDb;
    }

    public void setDiskDb(HashMap<String, Document> diskDb) {
        this.diskDb = diskDb;
    }

    @Override
    public String toString() {
        return "OnDiskDb{" +
                "diskDb=" + diskDb +
                '}';
    }
}
