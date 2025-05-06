package com.officialshivam.HomeLab.WorkerManagement.constants;

public class Constants {

    public enum Status {
        Absent,
        Present,
        Leave
    }

    public enum ResponseCodes {
        SUCCESS("00"),
        FAILURE("01"),
        NO_CHANGE_REQUIRED("02");

        private final String code;

        ResponseCodes(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
