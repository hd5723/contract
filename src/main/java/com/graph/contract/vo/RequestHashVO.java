package com.graph.contract.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author
 * @date 2020/4/1 9:37
 */
@Data
@ToString
public class RequestHashVO {
    private String hash;

    private String type;
}
