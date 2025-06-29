package org.acme.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "release")
public class Release {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "task_num")
    private String taskNum;

    @Column(name = "version")
    private String version;

    @Column(name = "note")
    private String note;

    @Column(name = "document_link")
    private String documentLink;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReleaseItem> items;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public List<ReleaseItem> getItems() {
        return items;
    }

    public void setItems(List<ReleaseItem> items) {
        this.items = items;
    }
}
