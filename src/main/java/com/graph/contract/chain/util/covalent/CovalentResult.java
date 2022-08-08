package com.graph.contract.chain.util.covalent;

import lombok.Data;

@Data
public class CovalentResult {

    private CovalentData data;
    private boolean error;
    private String error_message;
    private String error_code;

}
