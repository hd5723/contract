package com.graph.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graph.contract.mapper.TokenMapper;
import com.graph.contract.model.Token;
import com.graph.contract.service.IContractSignService;
import com.graph.contract.mapper.ContractSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.graph.contract.model.ContractSign;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kelvin
 * @since 2022-07-29
 */
@Service
public class ContractSignServiceImpl extends ServiceImpl<ContractSignMapper, ContractSign> implements IContractSignService {
    @Autowired(required = false)
    private ContractSignMapper mapper;

    @Resource
    private RedisTemplate<String, ContractSign> redisTemplate;

    @Override
    public ContractSign querySignInfoByAddress(String chain, String address, String sign) {

        ContractSign signInfo = redisTemplate.opsForValue().get(chain+"-" + address + "-" + sign);
        if(null != signInfo) {
            return signInfo;
        }
        //条件封装
        QueryWrapper<ContractSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(address), "address", address);
        queryWrapper.eq(!StringUtils.isEmpty(chain), "chain", chain);
        queryWrapper.eq(!StringUtils.isEmpty(sign), "sign", sign);
        signInfo = mapper.selectOne(queryWrapper);
        if(null!= signInfo) {
            redisTemplate.opsForValue().set(chain+"-" + address + "-" + sign , signInfo , 5 , TimeUnit.DAYS);
        }
        return signInfo;
    }

    @Override
    public boolean saveOrUpdate(ContractSign signInfo) {
        Long nowTime = System.currentTimeMillis();
        signInfo.setCreateTime(nowTime);
        signInfo.setUpdateTime(nowTime);
        return super.saveOrUpdate(signInfo);
    }

}
