package com.graph.contract.vo;

import lombok.Data;

/**
 * @author
 * @date 2020/4/3 11:50
 */
@Data
public class EtherKeystore {
    private String address;
    private String keystore;

    public EtherKeystore() {
    }

    public EtherKeystore(String address, String keystore) {
        this.address = address;
        this.keystore = keystore;
    }
}
