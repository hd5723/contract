package com.graph.contract.chain.util.covalent;

import lombok.Data;

import java.util.List;

@Data
public class CovalentData {

    private String address;
    private String updated_at;
    private String next_update_at;
    private String quote_currency;
    private int chain_id;
    private List<CovalentItem> items;
    private Page pagination;

}
