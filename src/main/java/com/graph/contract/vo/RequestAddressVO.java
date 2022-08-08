package com.graph.contract.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author
 * @date 2020/4/1 16:06
 */
@Data
public class RequestAddressVO {
    private String address;

    private String contractAddress;

    private String type;
}
