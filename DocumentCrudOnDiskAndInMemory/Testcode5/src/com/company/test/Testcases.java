package com.company.test;

import com.company.Entity.Document;
import com.company.Entity.DocumentManager;
import com.company.Entity.TierMode;
import com.company.Entity.User;
import org.junit.Test;

public class Testcases {
    DocumentManager documentManager;

    public Testcases(){
        documentManager = new DocumentManager();
        User user = new User("abc");
        documentManager.addUser(user);
        user = new User("xyz");
        documentManager.addUser(user);
        Document document = new Document("123", "abc", "bbbbbbb", user.getUserId(), TierMode.HOT_TIER);
        documentManager.addDocument(document);
        document = new Document("4", "oo", "pp", user.getUserId(), TierMode.COLD_TIER);
        documentManager.addDocument(document);
        System.out.println(documentManager);
    }

    @Test
    public void test1()
    {
        Document document = new Document("6", "ii", "pp", "abc", TierMode.COLD_TIER);
        documentManager.addDocument(document);
        documentManager.updateDocument(document.getDocumentId(), document.getTierMode(), "www", "kk", "abc");
        System.out.println(document);
        System.out.println(documentManager.readDocument(document.getDocumentId(), document.getTierMode(),"abc"));
        System.out.println();
    }

    @Test
    public void test2()
    {
        System.out.println("Permission check test...");
        Document document = new Document("7", "ii", "pp", "abc", TierMode.COLD_TIER);
        documentManager.addDocument(document);
        documentManager.updateDocument(document.getDocumentId(), document.getTierMode(), "www", "kk", "xyz");
        System.out.println(document);
        System.out.println(documentManager.readDocument(document.getDocumentId(), document.getTierMode(),"xyz"));
        System.out.println();
    }
}
