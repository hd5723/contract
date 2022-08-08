package com.graph.contract.service.chian;

import com.graph.contract.service.ITokenService;
import com.graph.contract.common.Constant;
import com.graph.contract.util.BlockType;
import com.graph.contract.util.Web3jUilt;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Slf4j
public class ContractDataService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ITokenService tokenService;

    private @Autowired
    EvmService evmService;

    // 线程池
    private ThreadPoolExecutor executorService;
    // 关闭程序时候记得调用dispose()
    private Disposable subscribe;

    void catchData() {
        Web3j web3j = Web3jUilt.web3jMap.get(BlockType.BSC.name());
        // 获取到上次同步最高块高
        BigInteger bsc_max_block_number = (BigInteger) redisTemplate.opsForValue().get(Constant.Redis.BSC_MAX_BLOCK_NUNBER);
        BigInteger currentBlockNumber = BigInteger.valueOf(Long.parseLong(evmService.getCurrentBlockNumber(BlockType.BSC.name())));

        //如果是初始化状态
        if( bsc_max_block_number.compareTo(BigInteger.ZERO) == 0 ) {
            bsc_max_block_number.setBit(1);
        }
        //如果区块落后
        if(currentBlockNumber.compareTo(bsc_max_block_number) > 0) {
            //追块高
            while(currentBlockNumber.compareTo(bsc_max_block_number) > 0){
                subscribe = web3j.replayPastBlocksFlowable(DefaultBlockParameter.valueOf(bsc_max_block_number.add(BigInteger.ONE)), DefaultBlockParameterName.LATEST, true)
                        .doOnError(e -> log.error("on error:{}", e.getMessage()))
                        // subscribe 获取到EthBlock， executeBlock 处理块信息
                        .subscribe(this::executeBlock, ex -> log.error("subscribe error:{}", ex.getMessage()));

//                bsc_max_block_number.setBit(bsc_max_block_number.add(BigInteger.ONE));
            }
        }
    }

    public void execute(Runnable r) {
        executorService.execute(r);
    }

    public void executeBlock(EthBlock block) {
        execute(() -> {
            // 获取到所需的块信息
            EthBlock.Block ethBlock = block.getBlock();
            // transaction信息获取
            executeTransaction(ethBlock.getTransactions());
        });
    }

    public void executeTransaction(List<EthBlock.TransactionResult> transactions) {
        if (transactions.size() == 0) {
            return;
        }
        try {
            Web3j web3j = Web3jUilt.web3jMap.get(BlockType.BSC.name());
            for (EthBlock.TransactionResult<EthBlock.TransactionObject> transactionResult : transactions) {
                EthBlock.TransactionObject transaction = transactionResult.get();
                // log 数据的获取 记得过滤一下已经removed 的log数据
//                executeLog(receipt.getLogs());
                // TransactionReceipt 数据获取
                TransactionReceipt receipt = web3j.ethGetTransactionReceipt(transaction.getHash()).send().getResult();
            }
        } catch (IOException e) {
            log.error("transaction input error, msg:{}", e.getMessage());
        }
    }

}
