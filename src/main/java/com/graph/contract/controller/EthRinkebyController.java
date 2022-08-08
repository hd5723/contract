package com.graph.contract.controller;

import com.graph.contract.service.chian.EvmService;
import com.graph.contract.service.chian.TransactionService;
import com.graph.contract.vo.RequestAddressVO;
import com.graph.contract.vo.RequestHashVO;
import com.graph.contract.vo.RequestNumberVO;
import com.graph.contract.vo.RequestTransactionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author
 * @date 2020/3/31 17:34
 */
@RestController
@RequestMapping("/ethRinkeby")
@Slf4j
public class EthRinkebyController {

    private @Autowired
    EvmService evmService;
    private @Autowired
    TransactionService transactionService;

    @Value("${eth.rinkeby.password}")
    private String password;

    /**
     * @return 获取当前geth版本信息
     */
    @GetMapping(value = "/findClientVersion")
    public String findClientVersion(@RequestParam String type){
        return evmService.findClientVersion(type);
    }

    /**
     * @return 获取当前区块高度
     */
    @GetMapping(value = "/getCurrentBlockNumber")
    public String getCurrentBlockNumber(@RequestParam String type){
        return evmService.getCurrentBlockNumber(type);
    }

    /**
     * @return 根据交易hash获取交易详情
     */
    @PostMapping(value = "/getTransactionByHash")
    public String getTransactionByHash(@RequestBody RequestHashVO requestHashVO){
        return evmService.getTransactionByHash(requestHashVO.getType(),requestHashVO.getHash());
    }

    /**
     * @return 根据交易hash获取交易详情
     */
    @PostMapping(value = "/getTransactionReceiptByHash")
    public String getTransactionReceiptByHash(@RequestBody RequestHashVO requestHashVO){
        return evmService.getTransactionReceiptByHash(requestHashVO.getType(),requestHashVO.getHash());
    }

    /**
     * @return 根据区块高度获取完整区块
     */
    @PostMapping(value = "/getBlockEthBlock")
    public String getBlockEthBlock(@RequestBody RequestNumberVO requestNumberVO){
        return evmService.getBlockEthBlock(requestNumberVO.getType(),requestNumberVO.getNumber());
    }

    /**
     * @return 根据密码生成地址
     */
    @PostMapping(value = "/newAccount")
    public String newAccount(@RequestParam String type){
        return evmService.newAccount(type,password);
    }

    /**
     * 获取全部账户
     * @return
     */
    @PostMapping(value = "/findAllAccounts")
    public List<String> findAllAccounts(@RequestParam String type){
        return evmService.findAllAccounts(type);
    }

    /**
     * 根据地址查询ETH余额
     * @return
     */
    @PostMapping(value = "/findEthBalanceByAddress")
    public String findEthBalanceByAddress(@RequestBody RequestAddressVO requestAddressVO){
        return evmService.findEthBalanceByAddress(requestAddressVO.getType(),requestAddressVO.getAddress());
    }

    /**
     * 根据hash查询区块确认次数
     * @return
     */
    @PostMapping(value = "/findEthTrueCountByHash")
    public BigInteger findEthTrueCountByHash(@RequestBody RequestHashVO requestHashVO){
        return evmService.findEthTrueCountByHash(requestHashVO.getType(),requestHashVO.getHash());
    }

    /**
     * ETH转账
     * @return
     */
    @PostMapping(value = "/transactionETH")
    public String ethTransaction(@RequestBody RequestTransactionVO requestTransactionVO){
        return transactionService.ethTransaction(requestTransactionVO.getType(),requestTransactionVO.getFrom(),requestTransactionVO.getTo(),requestTransactionVO.getValue());
    }

}
