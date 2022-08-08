package com.graph.contract.util;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

/**
 * @author
 * @date 2020/4/1 14:30
 */
public class InitWeb3j {
    private HttpService httpService;

    /**
     * 初始化web3j普通api调用
     *
     * @return web3j
     */
    public Web3j initWeb3j(String type) {
        return Web3j.build(getService(BlockRpc.getUrl(type)));
    }

    /**
     * 初始化web3j普通api调用
     *
     * @return web3j
     */
    public Web3j initWeb3jByUrl(String url) {
        return Web3j.build(getService(url));
    }

    /**
     * 初始化personal级别的操作对象
     * @return Geth
     */
    public Geth initGeth(String url){
        return Geth.build(getService(url));
    }


    /**
     * 初始化admin级别操作的对象
     * @return Admin
     */
    public Admin initAdmin(String url){
        return Admin.build(getService(url));
    }


    /**
     * 通过http连接到geth节点
     * @return
     */
    private HttpService getService(String url){
        if(httpService==null){
            httpService=new HttpService(url);
        }
        return httpService;
    }



    /**
     * 关闭网络
     * @param web3j
     */
    public static void close(Web3j web3j){
        web3j.shutdown();
    }
}
