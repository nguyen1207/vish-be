package com.nguyen1207.vishbe.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class ResponseDto<T> implements Serializable {
    protected String status;

    protected T data;

    protected String message;
}
