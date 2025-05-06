package com.officialshivam.HomeLab.WorkerManagement.controllers;

import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import com.officialshivam.HomeLab.WorkerManagement.services.WorkerService;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private ApplicationLogger logger;

    @PostMapping("/add")
    public ResponseEntity<ResponseBodySchema> addWorker(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to add worker");
        ResponseBodySchema response = new ResponseBodySchema();
        try {
            workerService.addWorker(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while adding worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while adding worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseBodySchema> deleteWorker(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to Delete worker");
        ResponseBodySchema response = new ResponseBodySchema();
        try {
            workerService.deleteWorker(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while Deleting worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while deleting worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseBodySchema> updateWorker(@RequestBody RequestBodySchema requestBodySchema) {
        logger.logInfo("Received request to Update worker");
        ResponseBodySchema response = new ResponseBodySchema();

        try {
            workerService.updateWorker(requestBodySchema, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while Updating worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while Updating worker: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
