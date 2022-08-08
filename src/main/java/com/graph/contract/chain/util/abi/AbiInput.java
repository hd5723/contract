package com.graph.contract.chain.util.abi;

import lombok.Data;

@Data
public class AbiInput {

    private boolean indexed = false;
    private String internalType;
    private String name;
    private String type; //org.web3j.abi.datatypes.generated

}
