package edu.miu.waalab.domain.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Empl {

    @Id
    int id;

    String name;

    @ManyToOne
    Department department;

}
