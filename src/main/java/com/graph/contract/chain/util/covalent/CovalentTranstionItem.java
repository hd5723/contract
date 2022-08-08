package com.graph.contract.chain.util.covalent;

import lombok.Data;

import java.util.List;

@Data
public class CovalentTranstionItem extends CovalentItem{

    private String block_signed_at;
    private double block_height;
    private String tx_hash;
    private int tx_offset;
    private boolean successful;
    private String from_address;
    private String from_address_label;
    private String to_address;
    private String to_address_label;
    private double value;
    private double value_quote;
    private double gas_offered;
    private double gas_spent;
    private double gas_price;
    private double fees_paid;
    private double gas_quote;
    private double gas_quote_rate;
    private List<CovalentLogEvent> log_events;

}

