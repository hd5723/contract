package com.graph.contract;

import com.alibaba.fastjson.JSON;
import com.graph.contract.chain.ContractApiService;
import com.graph.contract.chain.util.covalent.CovalentResult;
import com.graph.contract.model.Token;
import com.graph.contract.service.ITokenService;
import com.graph.contract.service.chian.EvmTokenService;
import com.graph.contract.util.BlockType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class ContractCovalentApiTests {

	@Resource
	private RedisTemplate<String, Token> redisTemplate;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private EvmTokenService evmTokenService;

	@Resource(name = "contractCovalentApiServiceImpl")
	private ContractApiService contractApiService;

	@Test
	void queryTransactionByHash() {
		String transHash = "0x9d2521d95ecca524523e48b7fac96d23a9d7f16c5dad84ecd40b72bdd4f2c745";
		CovalentResult result = contractApiService.queryTransactionByHash(transHash);
		System.out.println( JSON.toJSONString(result) );

	}

	@Test
	void queryTransactionByContractAddress() {
		String blockType = BlockType.BSC.name();

		String contractAddress = "0x43bf78a82bbc334b42dc2f9708d667d6ade79ec6";

		//查询feeReceiver
		String feeReceiver = evmTokenService.findEvmTokenFeeReceiverByAddress(blockType,contractAddress);
		log.info("feeReceiver:{}" , feeReceiver);

		//查询此合约的历史交易数据
		CovalentResult result = contractApiService.queryTransactionByContractAddress(contractAddress , null);
		log.info("transaction pageNumber 1:{}" , JSON.toJSONString(result));

//		Page page = new Page(1,100);
//		str = contractApiService.queryTransactionByContractAddress(contractAddress , page);
//		log.info("transaction pageNumber 2:{}" , str);
//
//		page = new Page(2,100);
//		str = contractApiService.queryTransactionByContractAddress(contractAddress , page);
//		log.info("transaction pageNumber 3:{}" , str);



	}


}
