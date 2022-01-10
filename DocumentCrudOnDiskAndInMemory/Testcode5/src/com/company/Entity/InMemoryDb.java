package com.company.Entity;

import java.util.HashMap;

public class InMemoryDb {
    HashMap<String, Document> memoryDb;

    public InMemoryDb() {
        memoryDb = new HashMap<>();
    }

    public HashMap<String, Document> getMemoryDb() {
        return memoryDb;
    }

    public void setMemoryDb(HashMap<String, Document> memoryDb) {
        this.memoryDb = memoryDb;
    }

    @Override
    public String toString() {
        return "InMemoryDb{" +
                "memoryDb=" + memoryDb +
                '}';
    }
}
