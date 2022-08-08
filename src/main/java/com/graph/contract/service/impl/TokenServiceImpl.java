package com.graph.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graph.contract.model.Token;
import com.graph.contract.mapper.TokenMapper;
import com.graph.contract.service.ITokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelvin
 * @since 2022-07-28
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements ITokenService {

    @Autowired(required = false)
    private TokenMapper mapper;

    @Resource
    private RedisTemplate<String, Token> redisTemplate;

    @Override
    public List<Token> queryTokensByName(String chain,String name) {
        //条件封装
        QueryWrapper<Token> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(name), "name", name);
        queryWrapper.eq(!StringUtils.isEmpty(chain), "chain", chain);
        return mapper.selectList(queryWrapper);
    }

    @Override
    public Token queryTokenByAddress(String chain,String address) {
        Token token = redisTemplate.opsForValue().get(chain+"-"+address);
        if(null != token) {
            return token;
        }
        //条件封装
        QueryWrapper<Token> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(address), "address", address);
        queryWrapper.eq(!StringUtils.isEmpty(chain), "chain", chain);
        token = mapper.selectOne(queryWrapper);
        if(null!= token) {
            redisTemplate.opsForValue().set(chain+"-" + address , token , 60 * 5 , TimeUnit.SECONDS);
        }
        return token;
    }

    @Override
    public double queryTokenPriceByAddress(String chain,String address) {
        Token token = this.queryTokenByAddress(chain,address);
        if(null==token) return 0.0;
        return token.getPrice();
    }

}
