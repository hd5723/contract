package com.graph.contract.service;

import com.graph.contract.model.Token;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelvin
 * @since 2022-07-28
 */
public interface ITokenService extends IService<Token> {

    /**
     * 根据名称查询token信息
     * @param name
     * @return
     */
    List<Token> queryTokensByName(String chain,String name);

    /**
     * 根据地址查询token信息
     * @param address
     * @return
     */
    Token queryTokenByAddress(String chain,String address);

    /**
     * 根据地址查询token价格
     * @param address
     * @return
     */
    double queryTokenPriceByAddress(String chain,String address);
}
