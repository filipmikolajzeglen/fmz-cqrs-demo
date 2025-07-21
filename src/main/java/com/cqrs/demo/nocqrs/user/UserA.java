package com.cqrs.demo.nocqrs.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_a", schema = "cqrsdemo")
class UserA
{
   @Id
   @Column(name = "id", unique = true, nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cqrsdemo.user_a_seq_gen")
   @SequenceGenerator(name = "cqrsdemo.user_a_seq_gen",
                      sequenceName = "cqrsdemo.user_a_seq",
                      allocationSize = 1)
   private Long id;
   private String name;
   private String email;
}