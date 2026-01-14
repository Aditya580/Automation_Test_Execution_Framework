package com.infosys.testingFramework.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // UI or API
    private String type;

    // URL for UI / endpoint for API
    private String target;

    // GET, POST (only for API)
    private String method;

    // SMOKE / REGRESSION / SANITY
    private String tag;
}
