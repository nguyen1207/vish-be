package com.nguyen1207.vishbe.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponseDto<T> extends ResponseDto<T> {
    public SuccessResponseDto(T data) {
        this.status = "success";
        this.data = data;
    }

    public SuccessResponseDto(T data, String message) {
        this(data);
        this.message = message;
    }

    public SuccessResponseDto() {
        this(null);
    }
}
