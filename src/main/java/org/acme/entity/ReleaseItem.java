package org.acme.entity;

import jakarta.persistence.*;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "release_item")
public class ReleaseItem {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "task_num")
    private String taskNum;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "document_link")
    private String documentLink;

    @ManyToOne
    @JoinColumn(name = "release_id")
    @JsonBackReference
    private Release release;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}
