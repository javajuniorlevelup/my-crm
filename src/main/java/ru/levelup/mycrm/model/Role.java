package ru.levelup.mycrm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "roles", schema = DbConstants.DB_SCHEMA)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", schema = DbConstants.DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column
    private String name;

}
