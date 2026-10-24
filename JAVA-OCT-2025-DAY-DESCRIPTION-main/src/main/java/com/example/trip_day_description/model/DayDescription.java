package com.example.trip_day_description.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 70, nullable = false)
    private String title;

    @Column(length = 800, nullable = false)
    private String description;

    private String location;

    @Column(name = "time")
    private String time;

    @Column(length = 800)
    private String packingSuggestions;

    @ElementCollection
    private List<String> images;


    @Column(columnDefinition = "TINYINT(1) default 0")
    private boolean saveInLibrary;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @JsonBackReference
    private Trip trip;


}
