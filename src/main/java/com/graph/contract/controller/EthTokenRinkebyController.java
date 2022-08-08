package com.graph.contract.controller;

import com.graph.contract.service.chian.EvmTokenService;
import com.graph.contract.service.chian.TransactionService;
import com.graph.contract.vo.RequestAddressVO;
import com.graph.contract.vo.RequestTransactionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author
 * @date 2020/4/2 15:00
 */
@RestController
@RequestMapping("/ethTokenRinkeby")
@Slf4j
public class EthTokenRinkebyController {

    private @Autowired
    EvmTokenService evmTokenService;

    private @Autowired
    TransactionService transactionService;

    /**
     * 根据地址、合约查询ETH代币余额
     * @return
     */
    @PostMapping(value = "/findEthTokenBalanceByAddress")
    public String findEthTokenBalanceByAddress(@RequestBody RequestAddressVO requestAddressVO){
        return evmTokenService.findEthTokenBalanceByAddress(requestAddressVO.getType(),requestAddressVO.getAddress(),requestAddressVO.getContractAddress());
    }

    /**
     * ETH代币转账
     * @return
     */
    @PostMapping(value = "/ethTokenTransaction")
    public String ethTokenTransaction(@RequestBody RequestTransactionVO requestTransactionVO){
        return transactionService.ethTokenTransaction(requestTransactionVO.getType(),requestTransactionVO.getFrom(),requestTransactionVO.getTo(),requestTransactionVO.getToken(),requestTransactionVO.getValue());
    }
}
