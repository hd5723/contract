package com.graph.contract.service.chian;

import com.alibaba.fastjson.JSONObject;
import com.graph.contract.util.InitWeb3j;
import com.graph.contract.util.Web3jUilt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.utils.Convert;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 * @author
 * @date 2020/3/31 17:25
 */
@Service
@Slf4j
public class EvmService {

    /**
     * 输入密码创建地址
     * @param password 密码（建议同一个平台的地址使用一个相同的，且复杂度较高的密码）
     * @return 地址hash
     * @throws IOException
     */
    public String newAccount(String type,String password) {
//        Admin admin = InitWeb3j.initAdmin(url);
//        Request<?, NewAccountIdentifier> request = admin.personalNewAccount(password);
//        NewAccountIdentifier result = null;
//        try {
//            result = request.send();
//            return JSONObject.toJSONString(result);
//        } catch (IOException e) {
//            log.error(e.getMessage(),e);
//        }
//        return null;
        /**
         * 创建钱包
         *
         * @param String password 密码
         * @param File  walletFilePath 存放钱包文件夹路径
         */
        String  walletFileName = "";
        try {
            walletFileName = WalletUtils.generateNewWalletFile(password, new File("\\\\DESKTOP-EJSN2MH\\keystore"), false);
        } catch (CipherException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        /**
         * 加载钱包文件
         *
         * @param String password 钱包密码
         * @param  File walleFilePath 钱包文件路径
         */
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(password, "\\\\DESKTOP-EJSN2MH\\keystore\\" + walletFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        // 获取钱包地址，私钥，公钥
        String address = credentials.getAddress();
        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);

        return address;
    }

    /**
     * 获得当前区块高度
     * @return 当前区块高度
     * @throws IOException
     */
    public String getCurrentBlockNumber(String type){
        Web3j web3j = Web3jUilt.getWeb3jByType(type);
        Request<?, EthBlockNumber> request = web3j.ethBlockNumber();
        try {
            return JSONObject.toJSONString(request.send());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据hash值获取交易详情
     * @param hash
     * @return
     * @throws IOException
     */
    public String getTransactionByHash(String type,String hash){
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        Request<?, EthTransaction> request = web3j.ethGetTransactionByHash(hash);
        try {
            return JSONObject.toJSONString(request.send());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据hash值获取交易详情
     * @param hash
     * @return
     * @throws IOException
     */
    public String getTransactionReceiptByHash(String type,String hash){
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        Request<?, EthGetTransactionReceipt> request = web3j.ethGetTransactionReceipt(hash);
        try {
            return JSONObject.toJSONString(request.send());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 获得ethblock
     * @param blockNumber 根据区块编号
     * @return
     * @throws IOException
     */
    public String getBlockEthBlock(String type,Integer blockNumber){
        Web3j web3j = Web3jUilt.web3jMap.get(type);
        DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(blockNumber);
        Request<?, EthBlock> request = web3j.ethGetBlockByNumber(defaultBlockParameter, true);
        EthBlock ethBlock = null;
        try {
            ethBlock = request.send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return JSONObject.toJSONString(ethBlock);
    }

    /**
     * 获取本地全部账户
     * @return
     */
    public List<String> findAllAccounts(String type){
/*        Admin admin = Web3jUilt.initAdmin(type);
        Request<?, PersonalListAccounts> request = admin.personalListAccounts();
        PersonalListAccounts personalListAccounts = null;
        try {
            personalListAccounts = request.send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return personalListAccounts.getAccountIds();*/
        return null;
    }

    public String findClientVersion(String type){
        Web3j web3 = Web3jUilt.web3jMap.get(type);
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3.web3ClientVersion().send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        return clientVersion;
    }

    /**
     * 根据地址查询eth余额
     * @param address
     * @return
     */
    public String findEthBalanceByAddress(String type,String address){
        Web3j web3 = Web3jUilt.web3jMap.get(type);
        EthGetBalance ethGetBlance = null;
        try {
            ethGetBlance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        // 格式转换 WEI(币种单位) --> ETHER
        BigDecimal bigDecimalBalance = new BigDecimal(ethGetBlance.getBalance());
        String balance = Convert.fromWei(bigDecimalBalance.toString(), Convert.Unit.ETHER).toPlainString();
        return balance;
    }

    /**
     * 根据hash查询区块确认次数
     * @param hash
     * @return
     */
    public BigInteger findEthTrueCountByHash(String type,String hash) {
        Web3j web3 = Web3jUilt.web3jMap.get(type);
        try {
            EthGetTransactionReceipt begin = web3.ethGetTransactionReceipt(hash).send();
            EthBlockNumber end = web3.ethBlockNumber().send();
            return end.getBlockNumber().subtract(begin.getResult().getBlockNumber()).add(new BigInteger("1"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            InitWeb3j.close(web3);
        }
        return new BigInteger("0");
    }
}
