package com.graph.contract.chain.util.covalent;

import lombok.Data;

@Data
public class CovalentLogEvent {

    private String block_signed_at;
    private double block_height;
    private double tx_offset;
    private double log_offset;
    private String tx_hash;
    private String[] raw_log_topics;
    private double sender_contract_decimals;
    private String sender_name;
    private String sender_contract_ticker_symbol;
    private String sender_address;
    private String sender_address_label;
    private String sender_logo_url;
    private String raw_log_data;
    private String decoded;

}

