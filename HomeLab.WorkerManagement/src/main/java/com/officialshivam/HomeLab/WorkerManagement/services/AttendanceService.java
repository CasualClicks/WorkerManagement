package com.officialshivam.HomeLab.WorkerManagement.services;

import com.officialshivam.HomeLab.WorkerManagement.schemas.RequestBodySchema;
import com.officialshivam.HomeLab.WorkerManagement.schemas.ResponseBodySchema;

public interface AttendanceService {
    public void ClockIn(RequestBodySchema requestBody, ResponseBodySchema responseBody);
    public void ClockOut(RequestBodySchema resquestBody, ResponseBodySchema responseBody);
}
