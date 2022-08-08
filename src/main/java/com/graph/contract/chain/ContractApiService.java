package com.graph.contract.chain;

import com.graph.contract.chain.util.covalent.CovalentResult;
import com.graph.contract.chain.util.covalent.Page;
import com.graph.contract.chain.util.abi.AbiModel;

import java.util.List;

public interface ContractApiService {

    /**
     * 获取Abi Json
     * @return
     */
    List<AbiModel> queryContractAbi(String contractAddress);

    /**
     * 批量保存Abi集合
     * @param contractAddress
     * @param abiModels
     * @return
     */
    boolean saveAbiList(String contractAddress,List<AbiModel> abiModels);

    CovalentResult queryTransactionByHash(String transHash);

    CovalentResult queryTransactionByContractAddress(String contractAddress,Page page);
}
