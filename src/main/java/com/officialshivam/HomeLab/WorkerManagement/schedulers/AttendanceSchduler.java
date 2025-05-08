package com.officialshivam.HomeLab.WorkerManagement.schedulers;


import com.officialshivam.HomeLab.WorkerManagement.constants.Constants;
import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.repos.AttendanceRepository;
import com.officialshivam.HomeLab.WorkerManagement.repos.WorkerRepository;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Attendance;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AttendanceSchduler {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ApplicationLogger logger;

    /**
     * Cron Expression Explanation: Every day at 6:00 AM
     * Format: second, minute, hour, dayOfMonth, month, dayOfWeek
     */
    @Scheduled(cron = "${scheduler.attendance.cron}")
    public void autoMarkDailyAttendance() {
        logger.logInfo("Starting autoMarkDailyAttendance scheduler at: " + LocalDateTime.now());

        List<Worker> workers = workerRepository.findActiveWorkers();
        logger.logInfo("Found " + workers.size() + " active workers to process.");

        LocalDate today = LocalDate.now();
        int markedCount = 0;
        int skippedCount = 0;

        for (Worker worker : workers) {
            boolean alreadyMarked = attendanceRepository
                    .findByWorkerIdAndDate(worker.getUuid(), today)
                    .isPresent();

            if (!alreadyMarked) {
                Attendance attendance = new Attendance();
                attendance.setWorker_uuid(worker.getUuid());
                attendance.setDate(today);
                attendance.setStatus(Constants.Status.Absent);
                attendance.setClockinTime(null);
                attendance.setClockoutTime(null);
                attendance.setIsExtraTaskAssigned(false);
                attendance.setCreatedAt(LocalDateTime.now());

                attendanceRepository.saveAndFlush(attendance);
                logger.logInfo("Attendance marked as ABSENT for worker: " + worker.getUuid());
                markedCount++;
            } else {
                logger.logInfo("Attendance already exists for worker: " + worker.getUuid());
                skippedCount++;
            }
        }

        logger.logInfo("AutoMarkDailyAttendance completed at: " + LocalDateTime.now());
        logger.logInfo("Summary — Marked: " + markedCount + ", Skipped: " + skippedCount);
    }
}