package com.graph.contract;

import com.alibaba.fastjson.JSON;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.fastjson.FastjsonMsgConvertor;
import com.graph.contract.chain.ContractApiService;
import com.graph.contract.chain.util.abi.AbiModel;
import com.graph.contract.model.Token;
import com.graph.contract.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class ContractApiTests {

	@Resource
	private RedisTemplate<String, Token> redisTemplate;

	@Autowired
	private ITokenService tokenService;

	@Resource(name = "contractApiServiceImpl")
	private ContractApiService contractApiService;

	@Test
	void queryTokenBalance() {
		HTTP http = HTTP.builder()
				.baseUrl("https://api.bscscan.com")
				.addMsgConvertor(new FastjsonMsgConvertor())
				.build();

		String str = http.sync("/api?module=account&action=balance&address=0xde0b295669a9fd93d5f28d9ec85e40f4cb697bae&tag=latest&apikey=ETZ7T8AJW8N8Q5I93BH61E2TSV3EUE2X25")
				.get()                          // GET请求
				.getBody().toString();
		System.out.println( str );

	}

	@Test
	void getsourcecode() {
		HTTP http = HTTP.builder()
				.baseUrl("https://api.bscscan.com")
				.addMsgConvertor(new FastjsonMsgConvertor())
				.build();

		String str = http.sync("/api?module=contract&action=getsourcecode&address=0x0e09fabb73bd3ade0a17ecc321fd13a19e81ce82&apikey=ETZ7T8AJW8N8Q5I93BH61E2TSV3EUE2X25")
				.get()                          // GET请求
				.getBody().toString();
		System.out.println( str );
	}

	@Test
	void getabi() {
		/*HTTP http = HTTP.builder()
				.baseUrl("https://api.bscscan.com")
				.addMsgConvertor(new FastjsonMsgConvertor())
				.build();

		String str = http.sync("/api?module=contract&action=getabi&address=0xe93577cF0590c3CD7eCeb6cB68d84B2A717c15bB&apikey=ETZ7T8AJW8N8Q5I93BH61E2TSV3EUE2X25")
				.get()                          // GET请求
				.getBody().toString();
		System.out.println( str );*/

//		String contractAddress = "0xe93577cF0590c3CD7eCeb6cB68d84B2A717c15bB";
		String contractAddress = "0x43bF78a82Bbc334b42dC2f9708D667d6aDE79eC6";
		/*List<AbiModel> abiModels = contractApiService.queryContractAbi(contractAddress);
		System.out.println(JSON.toJSONString(abiModels) );*/

		/*boolean flag = contractApiService.saveAbiList(contractAddress,abiModels);
		System.out.println("保存成功：" + flag);*/


		HTTP http = HTTP.builder()
				.baseUrl("https://api.bscscan.com")
				.addMsgConvertor(new FastjsonMsgConvertor())
				.build();

		String str = http.sync("/api?module=contract&action=checkverifystatus&address=0xe93577cF0590c3CD7eCeb6cB68d84B2A717c15bB&apikey=ETZ7T8AJW8N8Q5I93BH61E2TSV3EUE2X25")
				.get()                          // GET请求
				.getBody().toString();
		System.out.println( str );

	}


}
