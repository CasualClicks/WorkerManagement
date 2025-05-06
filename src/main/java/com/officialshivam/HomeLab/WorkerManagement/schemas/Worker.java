package com.officialshivam.HomeLab.WorkerManagement.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "workers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "task")
    private String task;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "joining_date")
    private Date joiningDate;
    @Column(name = "base_salary")
    private BigDecimal baseSalary;
    @Column(name = "per_day_salary")
    private BigDecimal perDaySalary;
    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "row_insertion_date_time")
    private LocalDateTime rowInsertionDateTime;
    @Column(name = "row_updation_date_time")
    private LocalDateTime rowUpdationDateTime;
    @Column(name = "is_active")
    private Boolean isActive;
}
