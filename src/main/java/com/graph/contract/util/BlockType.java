package com.graph.contract.util;

public enum BlockType {
    ETH(1,"eth"),
    BSC(56,"bsc"),
    OKX(66,"okx"),
    POLYGON(137,"polygon"),
    AVAX(43114,"avax"),
    FANTOM(250,"fontom"),
    AURORA(1313161554,"aurora"),
    ;

    // 成员变量
    private int code;
    private String name;

    private BlockType(int code , String name){
        this.code = code;
        this.name = name;
    }

    private int getCodeByName(String name){
        for (BlockType type : BlockType.values() ) {
            if(type.name.equals(name)) {
                return type.code;
            }
        }
        return 0;
    }

    private String getNameByCode(int code){
        for (BlockType type : BlockType.values() ) {
            if(type.code == code) {
                return type.name;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
}
