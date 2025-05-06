package com.officialshivam.HomeLab.WorkerManagement.controllers;

import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import com.officialshivam.HomeLab.WorkerManagement.services.AttendanceService;
import com.officialshivam.HomeLab.WorkerManagement.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ApplicationLogger logger;

    @PostMapping("/clock-in")
    public ResponseEntity<ResponseBodySchema> ClockInWorker(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to Clock-In worker");
        ResponseBodySchema response = new ResponseBodySchema();

        try {
            attendanceService.ClockIn(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("failed while adding attendance: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while adding attendance: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PostMapping("/clock-out")
    public ResponseEntity<ResponseBodySchema> ClockOutWorker(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to Clock-Out worker");
        ResponseBodySchema response = new ResponseBodySchema();

        try {
            attendanceService.ClockOut(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("failed while clocking out worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while clocking out Worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
