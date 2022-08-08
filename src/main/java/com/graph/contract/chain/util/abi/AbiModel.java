package com.graph.contract.chain.util.abi;

import lombok.Data;
import java.util.List;

@Data
public class AbiModel {

    private List<AbiInput> inputs;
    private List<AbiOutput> outputs;
    private String name;
    private String stateMutability;
    private String type;  //funciton or event
    private boolean anonymous;

}
