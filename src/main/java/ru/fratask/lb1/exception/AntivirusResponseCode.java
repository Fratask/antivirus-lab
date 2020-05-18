package ru.fratask.lb1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AntivirusResponseCode {

    UNKNOWN_ERROR(100, "UNKNOWN ERROR!!!"),
    DAO_CREATE_ERROR(101, "Error with add data in database"),
    DAO_READ_ERROR(102, "Error with read data in database"),
    DAO_UPDATE_ERROR(103, "Error with update data in database"),
    DAO_DELETE_ERROR(104, "Error with delete data in database"),

    ;

    private final int code;
    private final String errorMessage;
}
