package com.example.imageserver;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue()
    private long id;

    private String name;
    private String filepath;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilepath() {
        return filepath;
    }
}
