package com.graph.contract.chain.util.covalent;

import lombok.Data;

@Data
public class Page {
    //page number = 0
    private int page_number;

    //page size , default 100
    private int page_size = 100;

    private boolean has_more;

    private Integer total_count;

    public Page() {
    }

    public Page(int page_number , int page_size) {
        this.page_number = page_number;
        this.page_size = page_size;
    }


}
