package com.graph.contract.service.chian;

import com.alibaba.fastjson.JSONObject;
import com.graph.contract.util.Web3jUilt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * 以太坊、代币转账 业务逻辑
 * @author
 * @date 2020/4/3 9:03
 */
@Service
@Slf4j
public class TransactionService {

    /**
     * ETH转账
     * @param fromAddress
     * @param toAddress
     * @return
     */
    public String ethTransaction(String type, String fromAddress,String toAddress,String value){
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        unlockAccount(type,fromAddress,"123",new BigInteger("10"));
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("123","\\\\DESKTOP-EJSN2MH\\keystore\\UTC--2020-04-03T02-57-06.837566300Z--36ee06839453996efc5a2698ee4717afd13ab64e");
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return "";
        } catch (CipherException e) {
            log.error(e.getMessage(),e);
            return "";
        }

        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = web3j.ethGetTransactionCount(
                        fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
            return "";
        } catch (ExecutionException e) {
            log.error(e.getMessage(),e);
            return "";
        }

        BigInteger nonce ;
        try {
            nonce = ethGetTransactionCount.getTransactionCount();
        }catch (Exception e){
            log.error("该账户未激活");
            return "该账户未激活";
        }

        String bigDecimal = Convert.toWei(value, Convert.Unit.ETHER).toString();
//        BigInteger bigInteger = new BigInteger();
        int i = bigDecimal.indexOf(".");
        if (i < 0){
            value = bigDecimal;
        }else {
            value = bigDecimal.substring(0,i);
        }
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                nonce, Convert.toWei("6", Convert.Unit.GWEI).toBigInteger(),
                Convert.toWei("45000", Convert.Unit.WEI).toBigInteger(), toAddress,new BigInteger(value) );
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
            return "";
        } catch (ExecutionException e) {
            log.error(e.getMessage(),e);
            return "";
        }
        if (ethSendTransaction.hasError()) {
            log.info("transfer error:", ethSendTransaction.getError().getMessage());
            return "";
        } else {
            String transactionHash = ethSendTransaction.getTransactionHash();
            log.info("Transfer transactionHash:" + transactionHash);
        }
        lockAccount(type,fromAddress);
        return JSONObject.toJSONString(ethSendTransaction);
    }

    /**
     * 解锁账户，发送交易前需要对账户进行解锁
     * @param address  地址
     * @param password 密码
     * @param duration 解锁有效时间，单位秒
     * @return
     * @throws IOException
     */
    public Boolean unlockAccount(String type, String address, String password, BigInteger duration) {
       /* Admin admin = InitWeb3j.initAdmin(type);
        Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
        PersonalUnlockAccount account = null;
        try {
            account = request.send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return account.accountUnlocked();*/
        return null;
    }

    /**
     * 账户锁定，使用完成之后需要锁定
     * @param address
     * @return
     * @throws IOException
     */
    public Boolean lockAccount(String type, String address){
       /* Geth geth = InitWeb3j.initGeth(type);
        Request<?, BooleanResponse> request = geth.personalLockAccount(address);
        BooleanResponse response = null;
        try {
            response = request.send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return response.success();*/
        return null;
    }

    /**
     * ETH代币转账
     * @param fromAddress
     * @param toAddress
     * @return
     */
    public String ethTokenTransaction(String type, String fromAddress,String toAddress,String contractAddress,String value){
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        Credentials credentials = null;
        unlockAccount(type,fromAddress,"123",new BigInteger("10"));
        try {
            credentials = WalletUtils.loadCredentials("123","\\\\DESKTOP-EJSN2MH\\keystore\\UTC--2020-04-03T02-57-06.837566300Z--36ee06839453996efc5a2698ee4717afd13ab64e");
        } catch (IOException e) {
            return "keystore 异常";
        } catch (CipherException e) {
            return "keystore 异常";
        }

        EthGetTransactionCount transactionCount = null;
        try {
            transactionCount = web3j.ethGetTransactionCount(
                    fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e,"");
        } catch (ExecutionException e) {
            log.error(e.getMessage(),e,"");
        }
        if (transactionCount.hasError()){
            log.error("账号异常");
        }
        BigInteger nonce = transactionCount.getTransactionCount();

        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(toAddress), new Uint256(new BigInteger(value))),
                Arrays.asList(new TypeReference<Type>() {
                }));

        String encodedFunction = FunctionEncoder.encode(function);

        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, Convert.toWei("18", Convert.Unit.GWEI).toBigInteger(),
                Convert.toWei("100000", Convert.Unit.WEI).toBigInteger()
                , contractAddress, encodedFunction);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        log.debug("transfer hexValue:" + hexValue);
        String transactionHash = "";
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
            return "转账异常";
        } catch (ExecutionException e) {
            log.error(e.getMessage(),e);
            return "转账异常";
        }
        if (ethSendTransaction.hasError()) {
            log.info("transfer error:", ethSendTransaction.getError().getMessage());
        } else {
            transactionHash  = ethSendTransaction.getTransactionHash();
            log.info("Transfer transactionHash:" + transactionHash);

        }
        lockAccount(type,fromAddress);
        return transactionHash;
    }

}
