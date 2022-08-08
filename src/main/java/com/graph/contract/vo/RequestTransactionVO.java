package com.graph.contract.vo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author
 * @date 2020/4/1 17:28
 */
@Data
public class RequestTransactionVO {
    private String from;
    private String to;
    private String token;
    private BigInteger gas;
    private BigInteger gasPrice;
    private String value;
    private String data;
    private BigInteger nonce;

    private String type;
}
