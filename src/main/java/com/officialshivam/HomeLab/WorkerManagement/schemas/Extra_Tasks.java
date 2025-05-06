package com.officialshivam.HomeLab.WorkerManagement.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extra_tasks")
public class Extra_Tasks {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false, unique = true, length = 20)
    private String taskId;

    @Column(name = "worker_uuid", length = 255)
    private String workerUuid;

    @Column(name = "row_insertion_date_time", nullable = false)
    private LocalDateTime rowInsertionDateTime = LocalDateTime.now();

    @Column(name = "row_updation_date_time", nullable = false)
    private LocalDateTime rowUpdationDateTime = LocalDateTime.now();

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;
}
