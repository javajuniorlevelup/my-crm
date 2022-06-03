package ru.levelup.mycrm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static ru.levelup.mycrm.model.DbConstants.DB_SCHEMA;

@Getter @Setter
@Entity
@Table(name = Company.TABLE_NAME, schema = DB_SCHEMA)
public class Company {

    public static final String TABLE_NAME = "companies";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_id_seq")
    @SequenceGenerator(name = "companies_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
