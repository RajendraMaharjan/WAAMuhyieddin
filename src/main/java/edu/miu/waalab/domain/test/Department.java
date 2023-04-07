package edu.miu.waalab.domain.test;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department {
    @Id
    int id;

    String name;

    @OneToMany(mappedBy = "department")
    List<Empl> empls;

}
