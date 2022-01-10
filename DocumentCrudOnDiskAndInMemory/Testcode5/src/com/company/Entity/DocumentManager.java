package com.company.Entity;

import java.util.HashMap;
import java.util.List;

public class DocumentManager {
    private  HashMap<String, User> userMap;
    private HashMap<String, Document> documentMap;
    private InMemoryDb inMemoryDb;
    private OnDiskDb onDiskDb;

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public HashMap<String, Document> getDocumentMap() {
        return documentMap;
    }

    public DocumentManager()
    {
        userMap = new HashMap<>();
        documentMap = new HashMap<>();
        inMemoryDb = new InMemoryDb();
        onDiskDb = new OnDiskDb();
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setDocumentMap(HashMap<String, Document> documentMap) {
        this.documentMap = documentMap;
    }

    public InMemoryDb getInMemoryDb() {
        return inMemoryDb;
    }

    public void setInMemoryDb(InMemoryDb inMemoryDb) {
        this.inMemoryDb = inMemoryDb;
    }

    public OnDiskDb getOnDiskDb() {
        return onDiskDb;
    }

    public void setOnDiskDb(OnDiskDb onDiskDb) {
        this.onDiskDb = onDiskDb;
    }

    public void addUser(User user)
    {
        userMap.put(user.getUserId(), user);
    }

    public void addDocument(Document document)
    {
        documentMap.put(document.getDocumentId(), document);
        if(document.getTierMode() == TierMode.HOT_TIER)
            inMemoryDb.getMemoryDb().put(document.getDocumentId(), document);
        else
            onDiskDb.getDiskDb().put(document.getDocumentId(), document);
    }

    public void updateDocument(String documentId,  TierMode tierMode ,String title, String content, String username)
    {
        if(tierMode == TierMode.HOT_TIER)
            inMemoryDb.getMemoryDb().get(documentId).updateDocument(title, content, username);
        else
            onDiskDb.getDiskDb().get(documentId).updateDocument(title, content, username);
    }

    public Document readDocument(String documentId,  TierMode tierMode, String username)
    {
        if(tierMode == TierMode.HOT_TIER)
            return  inMemoryDb.getMemoryDb().get(documentId).readDocument(username);
        else
            return  onDiskDb.getDiskDb().get(documentId).readDocument(username);
    }

    public void addAccessToDocuments(String documentId, TierMode tierMode , AccessType accessType, List<String> usernames, String ownerUsername)
    {
        if(tierMode == TierMode.HOT_TIER)
            inMemoryDb.getMemoryDb().get(documentId).addUsersAccess(accessType, usernames, ownerUsername);
        else
            onDiskDb.getDiskDb().get(documentId).addUsersAccess(accessType, usernames, ownerUsername);
    }

    @Override
    public String toString() {
        return "DocumentManager{" +
                "userMap=" + userMap +
                ", documentMap=" + documentMap +
                ", inMemoryDb=" + inMemoryDb +
                ", onDiskDb=" + onDiskDb +
                '}';
    }
}
