package com.officialshivam.HomeLab.WorkerManagement.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBodySchema {
    private String ResponseCode;
    private String ResponseMessage;
}
