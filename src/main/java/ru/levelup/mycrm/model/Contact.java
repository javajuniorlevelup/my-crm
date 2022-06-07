package ru.levelup.mycrm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static ru.levelup.mycrm.model.DbConstants.DB_SCHEMA;

@Getter @Setter
@Entity
@Table(name = "contacts", schema = DB_SCHEMA)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_id_seq")
    @SequenceGenerator(name = "contacts_id_seq", schema = DB_SCHEMA, allocationSize = 1)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}
