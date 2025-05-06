package com.officialshivam.HomeLab.WorkerManagement.controllers;

import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.services.ExtraTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/")
public class ExtraTaskController {
    @Autowired
    private ExtraTaskService extraTaskService;

    @Autowired
    private ApplicationLogger logger;

    @PostMapping("/add")
    public ResponseEntity<ResponseBodySchema> addTask(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to add worker");
        ResponseBodySchema response = new ResponseBodySchema();
        try {
            extraTaskService.addExtraTask(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while adding Task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while adding task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseBodySchema> updateTask(@RequestBody RequestBodySchema requestBodySchema) {
        logger.logInfo("Received request to Update task");
        ResponseBodySchema response = new ResponseBodySchema();

        try {
            extraTaskService.UpdateExistingTask(requestBodySchema, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while Updating task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while Updating Task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseBodySchema> deleteTask(@RequestBody RequestBodySchema requestBody) {
        logger.logInfo("Received request to Delete Task");
        ResponseBodySchema response = new ResponseBodySchema();
        try {
            extraTaskService.RemoveExtraTask(requestBody, response);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            logger.logError("Validation failed while Deleting task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            logger.logError("Error while deleting task: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
