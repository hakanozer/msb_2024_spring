package com.works.utils;

import com.works.models.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity success(Object resultObj) {
        Result result = new Result(true, "Success", resultObj);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    public static ResponseEntity fail(String message, Object resultObj, HttpStatus httpStatus) {
        Result result = new Result(false, message, resultObj);
        return new ResponseEntity(result, httpStatus);
    }

}
