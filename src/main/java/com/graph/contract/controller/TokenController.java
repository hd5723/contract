package com.graph.contract.controller;


import com.alibaba.fastjson.JSON;
import com.graph.contract.common.CommonResult;
import com.graph.contract.common.ResponseStatusConstant;
import com.graph.contract.service.ITokenService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  token前端控制器
 * </p>
 *
 * @author kelvin
 * @since 2022-07-28
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private ITokenService tokenService;

    /**
     * @return 根据名称查询token信息
     */
    @GetMapping(value = "/queryTokensByName/{chain}/{name}")
    public String queryTokensByName(@PathVariable("chain") String chain,
                                    @PathVariable("name") String name){
        CommonResult result = CommonResult.success(ResponseStatusConstant.OK.getMessage(), tokenService.queryTokensByName(chain,name));
        return JSON.toJSONString(result);
    }

    /**
     * @return 根据地址查询token信息
     */
    @GetMapping(value = "/queryTokensByAddress/{chain}/{address}")
    public String queryTokensByAddress(@PathVariable("chain") String chain,
                                    @PathVariable("address") String address){
        CommonResult result = CommonResult.success(ResponseStatusConstant.OK.getMessage(), tokenService.queryTokenByAddress(chain,address));
        return JSON.toJSONString(result);
    }

    /**
     * @return 根据地址查询token价格
     */
    @GetMapping(value = "/queryTokenPriceByAddress/{chain}/{address}")
    public String queryTokenPriceByAddress(@PathVariable("chain") String chain,
                                       @PathVariable("address") String address){
        CommonResult result = CommonResult.success(ResponseStatusConstant.OK.getMessage(), tokenService.queryTokenPriceByAddress(chain,address));
        return JSON.toJSONString(result);
    }



}
