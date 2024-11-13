
// src/main/java/org/example/Petition.java
package org.example;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private static int counter = 0;
    private final int id;
    private String title;
    private String description;
    private List<String> signatures = new ArrayList<>();

    public Petition(String title, String description) {
        this.id = counter++;
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<String> getSignatures() { return signatures; }

    public void addSignature(String name, String email) {
        signatures.add(name + " (" + email + ")");
    }
}
