package com.bira.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_ORGANISATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "organisation_name",nullable = false)
    private String organisationName;

}
