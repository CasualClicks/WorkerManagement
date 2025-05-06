package com.officialshivam.HomeLab.WorkerManagement.services;

import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;

public interface ExtraTaskService {
    public void addExtraTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
    public void UpdateExistingTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
    public void RemoveExtraTask(RequestBodySchema requestBody, ResponseBodySchema responseBodySchema);
}
