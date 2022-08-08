package com.graph.contract.common;

import java.util.HashSet;
import java.util.Set;

public class Constant {

    public class Redis {
        public final static String BSC_MAX_BLOCK_NUNBER = "bsc_max_block_number";
    }

    public static class Contract {
        
        public final static Set<String> listenerAddress =  new HashSet<String>() {{
            add("0x43bf78a82bbc334b42dc2f9708d667d6ade79ec6");
        }};

        public final static Set<String> listenerFunctions =  new HashSet<String>() {{
            add("0xe63d38ed");
        }};
    }

    public static class ContractApi {
        public static final String GETABI_URL = "/api?module=contract&action=getabi";
    }
}
