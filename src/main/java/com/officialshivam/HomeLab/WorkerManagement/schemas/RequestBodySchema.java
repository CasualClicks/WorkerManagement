package com.officialshivam.HomeLab.WorkerManagement.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.officialshivam.HomeLab.WorkerManagement.constants.Constants.Status;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBodySchema {

    @NotNull
    @JsonProperty("worker_uuid")
    private String uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("worker_data")
    private WorkerData workerData;
    @JsonProperty("attendance_data")
    private AttendanceData attendanceData;
    @JsonProperty("due_data")
    private Dues dues;
    @JsonProperty(value = "extra_task_data")
    private ExtraTaskData extra_taskData;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkerData
    {
        @JsonProperty(value = "worker_task")
        private String task;
        @JsonProperty(value = "worker_phone")
        private String phone;
        @JsonProperty(value = "worker_joining_date")
        private Date joiningDate;
        @JsonProperty(value = "worker_base_salary")
        private BigDecimal baseSalary;
        @JsonProperty(value = "worker_per_day_salary")
        private BigDecimal perDaySalary;
        @JsonProperty(value = "worker_emergency_contact_number")
        private String emergencyContactNumber;
        @JsonProperty(value = "worker_address")
        private String address;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AttendanceData
    {
        @JsonProperty(value = "attendance_date")
        private LocalDate date;
        @JsonProperty(value = "attendance_status")
        private Status status;
        @JsonProperty(value = "is_extra_task_assigned")
        private Boolean isExtraTaskAssigned;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dues
    {
        @JsonProperty(value = "due_date")
        private LocalDate dueDate;
        @JsonProperty(value = "due_amount")
        private BigDecimal amount;
        @JsonProperty(value = "due_reason")
        private String reason;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExtraTaskData
    {
        @JsonProperty(value = "task_id")
        private String taskId;
        @JsonProperty(value = "is_task_completed")
        private Boolean isTaskCompleted;
        @JsonProperty(value = "description")
        private String description;
        @JsonProperty(value = "task_amount")
        private BigDecimal taskAmount;
    }
}
