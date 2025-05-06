package com.officialshivam.HomeLab.WorkerManagement.services.impl;

import com.officialshivam.HomeLab.WorkerManagement.constants.Constants;
import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import com.officialshivam.HomeLab.WorkerManagement.repos.ExtraTaskRepository;
import com.officialshivam.HomeLab.WorkerManagement.repos.WorkerRepository;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Extra_Tasks;
import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.services.ExtraTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ExtraTaskServiceImpl implements ExtraTaskService {

    @Autowired
    private WorkerRepository IWorkerRepo;
    @Autowired
    private ExtraTaskRepository IExtraTaskRepo;
    @Autowired
    private ApplicationLogger logger;


    @Override
    public void addExtraTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema) {
        try{
            logger.logInfo("Adding Extra Task to table.");

            if(requestBody.getExtra_taskData() == null || !StringUtils.hasText(requestBody.getExtra_taskData().getTaskId()))
            {
                logger.logInfo("Request Task Data is null or Task Id is null. Aborting");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Request Task Data is null or Task Id is null. Aborting");
                return;
            }

            Optional<Extra_Tasks> optionalTask = IExtraTaskRepo.findByTaskId(requestBody.getExtra_taskData().getTaskId());
            if (optionalTask.isPresent()) {
                logger.logInfo("Task already Present.");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Task already Present.");
                return;
            }

            Extra_Tasks extraTasksSchema = new Extra_Tasks();
            SetupExtraTaskSchema(extraTasksSchema, requestBody);

            IExtraTaskRepo.saveAndFlush(extraTasksSchema);

            logger.logInfo("Task Added Successfully with taskId " + extraTasksSchema.getTaskId());

            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("SUCCESS");
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }

    @Override
    public void UpdateExistingTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema) {
        try{
            logger.logInfo("Updating Existing Extra Task.");

            if(requestBody.getExtra_taskData() == null || !StringUtils.hasText(requestBody.getExtra_taskData().getTaskId()))
            {
                logger.logInfo("Request Task Data is null or Task Id is null. Aborting");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Request Task Data is null or Task Id is null. Aborting");
                return;
            }

            Optional<Extra_Tasks> optionalTask = IExtraTaskRepo.findByTaskId(requestBody.getExtra_taskData().getTaskId());
            if (optionalTask.isEmpty()) {
                logger.logInfo("Task not Present.");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Task not Present.");
                return;
            }

            Extra_Tasks extraTasksSchema = optionalTask.get();
            SetupExtraTaskSchema(extraTasksSchema, requestBody, true);

            IExtraTaskRepo.saveAndFlush(extraTasksSchema);

            logger.logInfo("Task Updated Successfully with taskId " + extraTasksSchema.getTaskId());

            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("SUCCESS");
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }

    @Override
    public void RemoveExtraTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema) {
        try{
            logger.logInfo("Removing Existing Extra Task.");

            if(requestBody.getExtra_taskData() == null || !StringUtils.hasText(requestBody.getExtra_taskData().getTaskId()))
            {
                logger.logInfo("Request Task Data is null or Task Id is null. Aborting");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Request Task Data is null or Task Id is null. Aborting");
                return;
            }

            Optional<Extra_Tasks> optionalTask = IExtraTaskRepo.findByTaskId(requestBody.getExtra_taskData().getTaskId());
            if (optionalTask.isEmpty()) {
                logger.logInfo("Task not Present.");
                responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
                responseBodySchema.setResponseMessage("Task not Present.");
                return;
            }

            Extra_Tasks extraTasksSchema = optionalTask.get();
            IExtraTaskRepo.delete(extraTasksSchema);

            logger.logInfo("Task Deleted Successfully with taskId " + extraTasksSchema.getTaskId());

            responseBodySchema.setResponseCode(Constants.ResponseCodes.SUCCESS.getCode());
            responseBodySchema.setResponseMessage("SUCCESS");
        }
        catch (Exception ex)
        {
            responseBodySchema.setResponseCode(Constants.ResponseCodes.FAILURE.getCode());
            responseBodySchema.setResponseMessage(ex.getMessage());
        }
    }

    private void SetupExtraTaskSchema(Extra_Tasks extraTasks, RequestBodySchema requestBodySchema)
    {
        SetupExtraTaskSchema(extraTasks, requestBodySchema, false);
    }
    private void SetupExtraTaskSchema(Extra_Tasks extraTasks , RequestBodySchema requestBodySchema, boolean isUpdating)
    {
        RequestBodySchema.ExtraTaskData extraTaskData = requestBodySchema.getExtra_taskData();
        extraTasks.setTaskId(extraTaskData.getTaskId());
        extraTasks.setAmount(extraTasks.getAmount());
        extraTasks.setDescription(extraTaskData.getDescription());
        extraTasks.setIsCompleted(extraTaskData.getIsTaskCompleted());
        if(extraTaskData.getIsTaskCompleted()==null)
            extraTasks.setIsCompleted(false);
        if(!isUpdating)
            extraTasks.setWorkerUuid(requestBodySchema.getUuid());
    }
}
