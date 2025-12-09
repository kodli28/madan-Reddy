package com.satwik.account.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="customer_id")
   private Long customerId;
   @Column(name="name")
   private String name;
   @Column(name="email")
   private String email;
   @Column(name="mobile_number")
   private String mobileNumber;
}
