package com.officialshivam.HomeLab.WorkerManagement.schemas;
import com.officialshivam.HomeLab.WorkerManagement.constants.Constants.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance", uniqueConstraints = {@UniqueConstraint(columnNames = {"worker_uuid", "date"})})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "worker_uuid", nullable = false)
    private String worker_uuid;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.Absent;

    @Column(name = "clockin_time")
    private LocalDateTime clockinTime;

    @Column(name = "clockout_time")
    private LocalDateTime clockoutTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "isExtraTaskAssigned", nullable = false)
    private Boolean isExtraTaskAssigned = false;

    @Column(name = "total_amount_today")
    private BigDecimal totalAmountToday;
}
