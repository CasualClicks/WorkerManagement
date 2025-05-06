package com.officialshivam.HomeLab.WorkerManagement.services.impl;

import com.officialshivam.HomeLab.WorkerManagement.constants.Constants;
import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.repos.AttendanceRepository;
import com.officialshivam.HomeLab.WorkerManagement.repos.WorkerRepository;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Attendance;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import com.officialshivam.HomeLab.WorkerManagement.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {


    @Autowired
    private WorkerRepository IWorkerRepo;

    @Autowired
    private AttendanceRepository AttendanceRepo;

    @Autowired
    private ApplicationLogger logger;


    @Override
    public void ClockIn(RequestBodySchema requestBody, ResponseBodySchema responseBody) {

        try {
            logger.logInfo("Clock-in attempt for UUID: " + requestBody.getUuid());

            // Step 1: Fetch worker by UUID
            Optional<Worker> optionalWorker = IWorkerRepo.findByUuid(requestBody.getUuid());
            if (optionalWorker.isEmpty()) {
                throw new EntityNotFoundException("Worker not found for UUID: " + requestBody.getUuid());
            }

            Worker worker = optionalWorker.get();
            String workerUUID = worker.getUuid();
            LocalDate today = LocalDate.now();

            // Step 2: Check if already clocked in today
            Optional<Attendance> existingAttendance = AttendanceRepo.findByWorkerIdAndDate(workerUUID, today);
            if (existingAttendance.isPresent()) {
                logger.logInfo("Worker already clocked in today.");
                responseBody.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBody.setResponseMessage("Worker already clocked in today.");
                return;
            }

            // Step 3: Create new attendance record
            Attendance attendance = new Attendance();
            SetupAttendanceSchema(requestBody, attendance);

            AttendanceRepo.saveAndFlush(attendance);

            logger.logInfo("Clock-in successful for worker ID: " + workerUUID);
            responseBody.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBody.setResponseMessage("SUCCESS");
        }
        catch(EntityNotFoundException e)
        {
            responseBody.setResponseMessage(e.getMessage());
        }
        catch (Exception ex)
        {
            responseBody.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBody.setResponseMessage(ex.getMessage());
        }
    }


    @Override
    public void ClockOut(RequestBodySchema requestBody, ResponseBodySchema responseBody) {
        try {
            logger.logInfo("Clock-out attempt for UUID: " + requestBody.getUuid());


            // Step 1: Fetch worker by UUID
            Optional<Worker> optionalWorker = IWorkerRepo.findByUuid(requestBody.getUuid());
            if (optionalWorker.isEmpty()) {
                throw new EntityNotFoundException("Worker not found for UUID: " + requestBody.getUuid());
            }

            Worker worker = optionalWorker.get();
            String workerUUID = worker.getUuid();
            LocalDate today = LocalDate.now();

            // Step 2: Fetch today's attendance record
            Optional<Attendance> existingAttendance = AttendanceRepo.findByWorkerIdAndDate(workerUUID, today);
            if (existingAttendance.isEmpty()) {
                logger.logInfo("No clock-in found for today, cannot clock out.");
                responseBody.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBody.setResponseMessage("No clock-In record for this person for today.");
            }

            Attendance attendance = existingAttendance.get();

            // Step 3: Check if already clocked out
            if (attendance.getClockoutTime() != null) {
                logger.logInfo("Worker already clocked out today.");
                responseBody.setResponseCode(Constants.ResponseCodes.NO_CHANGE_REQUIRED.getCode());
                responseBody.setResponseMessage("Already Clocked Out");
            }

            attendance.setClockoutTime(LocalDateTime.now());
            AttendanceRepo.saveAndFlush(attendance);

            logger.logInfo("Clock-out successful for worker UUID: " + requestBody.getUuid());
            responseBody.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBody.setResponseMessage("SUCCESS");
        }
        catch(EntityNotFoundException e)
        {
            responseBody.setResponseMessage(e.getMessage());
        }
        catch (Exception ex)
        {
            responseBody.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBody.setResponseMessage(ex.getMessage());
        }
    }

    private static Attendance SetupAttendanceSchema(RequestBodySchema requestBody, Attendance attendance) {
        attendance.setWorker_uuid(requestBody.getUuid());
        attendance.setDate(LocalDate.now());
        attendance.setClockinTime(LocalDateTime.now());
        attendance.setStatus(Constants.Status.Present);
        attendance.setIsExtraTaskAssigned(false);
        attendance.setCreatedAt(LocalDateTime.now());
        return attendance;
    }

}
