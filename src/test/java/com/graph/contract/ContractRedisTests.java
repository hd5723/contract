package com.graph.contract;

import com.alibaba.fastjson.JSON;
import com.graph.contract.common.Constant;
import com.graph.contract.model.Token;
import com.graph.contract.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class ContractRedisTests {

	@Resource
	private RedisTemplate<String, Token> redisTemplate;

	@Autowired
	private ITokenService tokenService;

	@Test
	void queryTokensByName() {
		String chain = "bsc";
		String name = "bnb";
		List<Token> tokens = tokenService.queryTokensByName(chain,name);
		for (Token token : tokens) {
			redisTemplate.opsForValue().set(chain+"-" + token.getAddress() , token , 60 * 5 , TimeUnit.SECONDS);
			System.out.println(token.getAddress());
		}

	}


}
