package com.officialshivam.HomeLab.WorkerManagement.services.impl;

import com.officialshivam.HomeLab.WorkerManagement.constants.Constants;
import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.repos.WorkerRepository;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;
import com.officialshivam.HomeLab.WorkerManagement.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository IWorkerRepo;

    @Autowired
    private ApplicationLogger logger;



    @Override
    public void addWorker(RequestBodySchema requestBody,ResponseBodySchema responseBodySchema) {

        try {
            logger.logInfo("In function: addWorker");
            if (!StringUtils.hasText(requestBody.getName()))
                throw new IllegalArgumentException("Worker name can not be null or empty");

            // Setup
            Worker worker = new Worker();

            //Finding Existing
            Optional<Worker> existingWorker = IWorkerRepo.findByUuid(requestBody.getUuid());
            if (existingWorker.isPresent()) {
                throw new IllegalArgumentException("Worker with ID '" + requestBody.getUuid() + "' already exists.");
            }

            // Setup Worker
            SetupWorkerSchema(worker, requestBody);

            // UpdateDB
            IWorkerRepo.saveAndFlush(worker);

            //Setup Response
            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("Worker Added Successfully");
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }


    @Override
    public void deleteWorker(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema) {

        try {
            logger.logInfo("In function: deleteWorker");

            Optional<Worker> workerToDelete = IWorkerRepo.findByUuid(requestBody.getUuid());

            if (workerToDelete.isPresent()) {
                IWorkerRepo.delete(workerToDelete.get());
                logger.logInfo("Deleted worker with ID: " + workerToDelete.get().getUuid());

            } else {
                throw new EntityNotFoundException("Worker not found with provided identifier.");
            }

            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("SUCCESS");
        }
        catch (EntityNotFoundException ex)
        {
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            if(!StringUtils.hasText(responseBodySchema.getResponseMessage()))
                responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }

    @Override
    public void updateWorker(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema) {
        try {
            logger.logInfo("In function: updateWorker");

            Optional<Worker> workerToUpdate = IWorkerRepo.findByUuid(requestBody.getUuid());

            if (workerToUpdate.isEmpty()) {
                logger.logInfo("Worker not found with provided identifier.");
                throw new EntityNotFoundException("Worker not found with provided identifier.");
            }

            Worker existingWorker = workerToUpdate.get();
            SetupWorkerSchema(existingWorker, requestBody, true);

            IWorkerRepo.saveAndFlush(existingWorker);
            logger.logInfo("Updated worker with ID: "+ existingWorker.getUuid());
            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("SUCCESS");
        }
        catch (EntityNotFoundException ex)
        {
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            if(!StringUtils.hasText(responseBodySchema.getResponseMessage()))
                responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }

    private void SetupWorkerSchema(Worker worker, RequestBodySchema requestBodySchema)
    {
        SetupWorkerSchema(worker, requestBodySchema, false);
    }
    private void SetupWorkerSchema(Worker worker , RequestBodySchema requestBodySchema, boolean isUpdating)
    {
        worker.setIsActive(requestBodySchema.getIsActive());
        if(!isUpdating)
            worker.setUuid(requestBodySchema.getUuid());
        worker.setName(requestBodySchema.getName());

        RequestBodySchema.WorkerData workerData = requestBodySchema.getWorkerData();
        worker.setPhone(workerData.getPhone());
        worker.setAddress(workerData.getAddress());
        worker.setBaseSalary(workerData.getBaseSalary());
        worker.setPerDaySalary(workerData.getPerDaySalary());
        worker.setEmergencyContactNumber(workerData.getEmergencyContactNumber());
        worker.setJoiningDate(workerData.getJoiningDate());

        worker.setRowInsertionDateTime(LocalDateTime.now());
        worker.setRowUpdationDateTime(LocalDateTime.now());
    }
}
