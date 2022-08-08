package com.graph.contract.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2020/4/1 14:30
 */
@Component
public class BlockRpc {

    private static String ethUrl;

    private static String bscUrl;

    private static String okxUrl;

    private static String polygonUrl;

    private static String avaxUrl;

    private static String ftmUrl;

    private static String auroraUrl;

    public static String getUrl(String type) {
        if(type.equalsIgnoreCase(BlockType.ETH.name())) {
            return ethUrl;
        }

        if(type.equalsIgnoreCase(BlockType.BSC.name())) {
            return bscUrl;
        }

        if(type.equalsIgnoreCase(BlockType.OKX.name())) {
            return okxUrl;
        }

        if(type.equalsIgnoreCase(BlockType.POLYGON.name())) {
            return polygonUrl;
        }

        if(type.equalsIgnoreCase(BlockType.FANTOM.name())) {
            return ftmUrl;
        }

        if(type.equalsIgnoreCase(BlockType.AURORA.name())) {
            return auroraUrl;
        }

        if(type.equalsIgnoreCase(BlockType.AVAX.name())) {
            return avaxUrl;
        }
        return ethUrl;
    }

    @Value("${eth.rinkeby.url}")
    public void setEthUrl(String ethUrl) {
        BlockRpc.ethUrl = ethUrl;
    }

    @Value("${bsc.rinkeby.url}")
    public void setBscUrl(String bscUrl) {
        BlockRpc.bscUrl = bscUrl;
    }

    @Value("${okx.rinkeby.url}")
    public void setOkxUrl(String okxUrl) {
        BlockRpc.okxUrl = okxUrl;
    }

    @Value("${polygon.rinkeby.url}")
    public void setPolygonUrl(String polygonUrl) {
        BlockRpc.polygonUrl = polygonUrl;
    }

    @Value("${avax.rinkeby.url}")
    public void setAvaxUrl(String avaxUrl) {
        BlockRpc.avaxUrl = avaxUrl;
    }

    @Value("${ftm.rinkeby.url}")
    public void setFtmUrl(String ftmUrl) {
        BlockRpc.ftmUrl = ftmUrl;
    }

    @Value("${aurora.rinkeby.url}")
    public void setAuroraUrl(String auroraUrl) {
        BlockRpc.auroraUrl = auroraUrl;
    }

}
