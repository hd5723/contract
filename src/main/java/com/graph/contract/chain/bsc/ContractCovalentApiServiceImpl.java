package com.graph.contract.chain.bsc;

import com.graph.contract.chain.ContractApiService;
import com.graph.contract.chain.base.BaseContractApiService;
import com.graph.contract.chain.util.covalent.CovalentResult;
import com.graph.contract.chain.util.covalent.Page;
import com.graph.contract.chain.util.abi.AbiModel;
import com.graph.contract.chain.util.function.FunctionUtil;
import com.graph.contract.common.ResponseStatusConstant;
import com.graph.contract.model.ContractSign;
import com.graph.contract.util.BlockType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@Slf4j
public class ContractCovalentApiServiceImpl extends BaseContractApiService implements ContractApiService {

    private static final String BASE_URL = "https://api.covalenthq.com";
    private static final String chain_name = BlockType.BSC.name();
    private static final String COVALENT_API_KEY = "ckey_988a84c33314448288028638a1c";

    public ContractCovalentApiServiceImpl() {
        super(BASE_URL);
    }

    public List<AbiModel> queryContractAbi(String contractAddress) {
        return null;
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

    @Override
    public CovalentResult queryTransactionByHash(String transHash) {
        StringBuilder parmeter = new StringBuilder("/v1/")
                .append(BlockType.BSC.getCode())
                .append("/transaction_v2/")
                .append(transHash)
                .append("/?&key=")
                .append(COVALENT_API_KEY);
        CovalentResult result = http.sync(parmeter.toString())
                .get()                        // GET请求
                .getBody().toBean(CovalentResult.class);
        return result;
    }

    @Override
    public CovalentResult queryTransactionByContractAddress(String contractAddress , Page page) {
        if(null == page) {
            page = new Page(0,100);
        }
        StringBuilder parmeter = new StringBuilder("/v1/")
                .append(BlockType.BSC.getCode())
                .append("/address/")
                .append(contractAddress)
                .append("/transactions_v2/?quote-currency=USD&format=JSON&block-signed-at-asc=true&no-logs=false")
                .append("&page-number=").append(page.getPage_number())
                .append("&page-size=").append(page.getPage_size())
                .append("&key=").append(COVALENT_API_KEY);
        CovalentResult result = http.sync(parmeter.toString())
                .get()                        // GET请求
                .getBody().toBean(CovalentResult.class);
        return result;
    }


}
