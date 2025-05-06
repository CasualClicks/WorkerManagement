package com.officialshivam.HomeLab.WorkerManagement.services;

import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService{
    public void addWorker(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
    public void updateWorker(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
    public void deleteWorker(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
}
