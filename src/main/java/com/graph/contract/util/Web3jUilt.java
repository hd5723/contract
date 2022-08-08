package com.graph.contract.util;

import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @date 2020/4/1 14:30
 */
@Component
public class Web3jUilt {

    public static Map<String, Web3j> web3jMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void initWeb3jMap() {
        Web3j eth = new InitWeb3j().initWeb3j(BlockType.ETH.name());
        Web3j bsc = new InitWeb3j().initWeb3j(BlockType.BSC.name());
        Web3j okx = new InitWeb3j().initWeb3j(BlockType.OKX.name());
        Web3j fantom = new InitWeb3j().initWeb3j(BlockType.FANTOM.name());
        Web3j polygon = new InitWeb3j().initWeb3j(BlockType.POLYGON.name());
        Web3j aurora = new InitWeb3j().initWeb3j(BlockType.AURORA.name());
        Web3j avax = new InitWeb3j().initWeb3j(BlockType.AVAX.name());

        web3jMap.put(BlockType.ETH.name(),eth);
        web3jMap.put(BlockType.BSC.name(),bsc);
        web3jMap.put(BlockType.OKX.name(),okx);
        web3jMap.put(BlockType.FANTOM.name(),fantom);
        web3jMap.put(BlockType.POLYGON.name(),polygon);
        web3jMap.put(BlockType.AURORA.name(),aurora);
        web3jMap.put(BlockType.AVAX.name(),avax);
    }

    public static Web3j getWeb3jByType(String type) {
        return web3jMap.get(type.toUpperCase());
    }
}
