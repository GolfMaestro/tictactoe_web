package org.bygolf.datasource.model;

import jakarta.persistence.*;

@Entity
@Table(name = "game_field")
public class DSGameField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String fieldText;

    public DSGameField() {
    }

    public DSGameField(String fieldText) {
        this.fieldText = fieldText;
    }

    public DSGameField(Long id, String fieldText) {
        this.id = id;
        this.fieldText = fieldText;
    }

    public String getFieldText() {
        return fieldText;
    }

    public Long getId() {
        return id;
    }

}