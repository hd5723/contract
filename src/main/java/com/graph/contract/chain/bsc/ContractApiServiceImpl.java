package com.graph.contract.chain.bsc;

import com.graph.contract.chain.base.BaseContractApiService;
import com.graph.contract.chain.util.function.FunctionUtil;
import com.graph.contract.common.Constant;
import com.graph.contract.common.ResponseStatusConstant;
import com.graph.contract.chain.ContractApiService;
import com.graph.contract.chain.util.abi.AbiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import com.graph.contract.chain.util.ApiResult;
import org.springframework.stereotype.Service;
import com.graph.contract.model.ContractSign;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.TypeReference;
import com.graph.contract.util.BlockType;
import com.alibaba.fastjson.JSON;
import java.util.List;

@Service
@Slf4j
public class ContractApiServiceImpl extends BaseContractApiService implements ContractApiService {

    private static final String BASE_URL = "https://api.bscscan.com";
    private static final String chain_name = BlockType.BSC.name();
    private static final String API_KEY = "ETZ7T8AJW8N8Q5I93BH61E2TSV3EUE2X25";

    public ContractApiServiceImpl() {
        super(BASE_URL);
    }

    public List<AbiModel> queryContractAbi(String contractAddress) {
        String parmeter = Constant.ContractApi.GETABI_URL + "&address=" + contractAddress + "&apikey=" + API_KEY;
        ApiResult result = http.sync(parmeter)
                .get()                          // GET请求
                .getBody().toBean(ApiResult.class);

        if (result.getStatus() != 1) {
            return null;
        }
        List<AbiModel> abiModels = JSON.parseObject(result.getResult(), new TypeReference<List<AbiModel>>() {
        });
        return abiModels;
    }

    public boolean saveAbiList(String contractAddress , List<AbiModel> abiModels) {
        if(CollectionUtils.isEmpty(abiModels)) return false;

        for (AbiModel abi : abiModels) {
            if (StringUtils.isEmpty(abi.getName()) == true) {
                continue;
            }

//			if("disperseEther".equalsIgnoreCase(abi.getName())==false){
//				continue;
//			}

            String funtionSign = FunctionUtil.getFuntionSign(abi);
            String sign = FunctionUtil.getSortHexString(funtionSign);
            ContractSign oneSign = contractSignService.querySignInfoByAddress(BlockType.BSC.name(), contractAddress, sign);
            if (null == oneSign) {
                ContractSign signInfo = new ContractSign();
                signInfo.setName(abi.getName());
                signInfo.setAddress(contractAddress);
                signInfo.setChain(chain_name);
                signInfo.setSign(FunctionUtil.getSortHexString(funtionSign));
                signInfo.setSignText(funtionSign);
                signInfo.setSignTextView(FunctionUtil.getFuntionSignSource(abi));
                signInfo.setType(abi.getType());
                contractSignService.saveOrUpdate(signInfo);
                log.info("保存签名：{} , message:{} " , abi.getName() , ResponseStatusConstant.OK.getMessage());
            } else {
                log.info("签名：{} , 已存在" , abi.getName());
            }
        }
        return true;
    }

}
