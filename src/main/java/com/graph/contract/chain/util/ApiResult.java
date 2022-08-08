package com.graph.contract.chain.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResult implements Serializable {
    
    private int status;
    private String message;
    private String result;
}
