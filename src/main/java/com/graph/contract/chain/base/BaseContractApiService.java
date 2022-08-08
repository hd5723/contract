package com.graph.contract.chain.base;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.fastjson.FastjsonMsgConvertor;
import com.graph.contract.chain.ContractApiService;
import com.graph.contract.chain.util.covalent.CovalentResult;
import com.graph.contract.chain.util.covalent.Page;
import com.graph.contract.chain.util.abi.AbiModel;
import com.graph.contract.service.IContractSignService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseContractApiService implements ContractApiService {

    @Autowired
    protected IContractSignService contractSignService;

    protected HTTP http = null;

    protected BaseContractApiService(String baseUrl) {
        http = HTTP.builder()
                .baseUrl(baseUrl)
                .addMsgConvertor(new FastjsonMsgConvertor())
                .build();
    }


    @Override
    public List<AbiModel> queryContractAbi(String contractAddress) {
        return null;
    }

    @Override
    public boolean saveAbiList(String contractAddress, List<AbiModel> abiModels) {
        return false;
    }

    @Override
    public CovalentResult queryTransactionByHash(String transHash) {
        return null;
    }

    @Override
    public CovalentResult queryTransactionByContractAddress(String contractAddress, Page page) {
        return null;
    }
}
