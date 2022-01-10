package com.company.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Document {
    private String documentId;
    private String title;
    private String content;
    private String ownerUsername;
    private DocumentMode documentMode;
    private TierMode tierMode;
    private HashMap<AccessType, HashSet<String>> typeAccessUsernames;

    public Document(String documentId, String title, String content, String ownerUsername, TierMode tierMode) {
        this.documentId = documentId;
        this.title = title;
        this.content = content;
        this.ownerUsername = ownerUsername;
        this.documentMode = DocumentMode.PRIVATE;
        this.tierMode = tierMode;
        this.typeAccessUsernames = new HashMap<>();
        for(AccessType accessType : AccessType.values())
        {
            typeAccessUsernames.put(accessType, new HashSet<>());
            typeAccessUsernames.get(accessType).add(ownerUsername);
        }
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public DocumentMode getDocumentMode() {
        return documentMode;
    }

    public TierMode getTierMode() {
        return tierMode;
    }

    public HashMap<AccessType, HashSet<String>> getTypeAccessUsernames() {
        return typeAccessUsernames;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public void setDocumentMode(DocumentMode documentMode) {
        this.documentMode = documentMode;
    }

    public void setTierMode(TierMode tierMode) {
        this.tierMode = tierMode;
    }

    public void setTypeAccessUsernames(HashMap<AccessType, HashSet<String>> typeAccessUsernames) {
        this.typeAccessUsernames = typeAccessUsernames;
    }

    public void updateDocument(String title, String content, String username)
    {
        if(documentMode == DocumentMode.PRIVATE &&
                !typeAccessUsernames.get(AccessType.EDIT).contains(username))
        {
            System.out.println("User cannot edit the file as the edit permissions are not there");
            return;
        }

        if(title != null)
            setTitle(title);

        if(content != null)
            setContent(content);
    }

    public Document readDocument(String username)
    {
        if(documentMode == DocumentMode.PRIVATE &&
                !typeAccessUsernames.get(AccessType.READ).contains(username))
        {
            System.out.println("User cannot read the file");
            return null;
        }
        return this;
    }

    public void addUsersAccess(AccessType accessType, List<String> usernames, String ownerUsername)
    {
        if(ownerUsername != getOwnerUsername())
        {
            System.out.println("Only the owners can update access, not allowed");
            return;
        }
        typeAccessUsernames.get(accessType).addAll(usernames);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", documentMode=" + documentMode +
                ", tierMode=" + tierMode +
                ", typeAccessUsernames=" + typeAccessUsernames +
                '}';
    }
}
