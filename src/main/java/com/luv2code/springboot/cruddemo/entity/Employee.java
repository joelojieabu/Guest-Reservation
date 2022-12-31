package com.luv2code.springboot.cruddemo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name ="employee")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
