package com.nguyen1207.vishbe.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponseDto<T> extends ResponseDto<T> {
    protected int code;

    public ErrorResponseDto(HttpStatus code, T data, String message) {
        this.status = "error";
        this.code = code.value();
        this.data = data;
        this.message = message;
    }
}
