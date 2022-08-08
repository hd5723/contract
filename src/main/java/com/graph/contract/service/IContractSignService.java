package com.graph.contract.service;

import com.graph.contract.model.ContractSign;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kelvin
 * @since 2022-07-29
 */
public interface IContractSignService extends IService<ContractSign> {


    /**
     * 根据地址查询签名信息
     * @param address
     * @return
     */
    ContractSign querySignInfoByAddress(String chain,String address,String name);

    /**
     * 保存签名信息
     * @param signInfo
     * @return
     */
    boolean saveOrUpdate(ContractSign signInfo);

}
