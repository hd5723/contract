package com.graph.contract.service.chian;

import com.graph.contract.util.Web3jUilt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/4/2 14:57
 */
@Service
@Slf4j
public class EvmTokenService {

    /**
     * 根据地址、合约查询ETH代币余额
     * @param fromAddress
     * @param contractAddress
     * @return
     */
    public String findEthTokenBalanceByAddress(String type, String fromAddress, String contractAddress) {
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        String methodName = "balanceOf";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();
        Address address = new Address(fromAddress);
        inputParameters.add(address);

        TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
        };
        outputParameters.add(typeReference);
        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);


        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);

        EthCall ethCall;
        String balanceValue = "0";
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            if (results == null || results.size() < 1){
                throw new Exception("查询地址不存在或区块同步落后");
            }
            Type balanceValue1 = results.get(0);
            balanceValue = balanceValue1.getValue().toString();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return balanceValue;
    }

    /**
     * 根据地址、合约查询ETH代币的精度
     * @param contractAddress
     * @return
     */
    public int findEthTokenDecimalsByAddress(String type, String contractAddress) {
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        String methodName = "decimals";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();

        TypeReference<Uint8> typeReference = new TypeReference<Uint8>() {
        };
        outputParameters.add(typeReference);
        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(null, contractAddress, data);

        EthCall ethCall;
        int decimalsValue = 18;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            if (results == null || results.size() < 1){
                throw new Exception("查询地址不存在或区块同步落后");
            }
            Type balanceValue1 = results.get(0);
            decimalsValue = Integer.parseInt(balanceValue1.getValue().toString());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return decimalsValue;
    }

    /**
     * 根据地址查询Evm代币的feeReceiver(手续费)
     * @param contractAddress
     * @return
     */
    public String findEvmTokenFeeReceiverByAddress(String type, String contractAddress) {
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        String methodName = "feeReceiver";
        List<Type> inputParameters = new ArrayList<>();
        List<TypeReference<?>> outputParameters = new ArrayList<>();

        TypeReference<Address> typeReference = new TypeReference<Address>() {
        };
        outputParameters.add(typeReference);
        Function function = new Function(methodName, inputParameters, outputParameters);
        String data = FunctionEncoder.encode(function);
        Transaction transaction = Transaction.createEthCallTransaction(null, contractAddress, data);

        EthCall ethCall;
        try {
            ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            if (results == null || results.size() < 1){
                throw new Exception("查询地址不存在或区块同步落后");
            }
            Type feeReceiver1 = results.get(0);
            String feeReceiver = feeReceiver1.getValue().toString();
            return feeReceiver;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
