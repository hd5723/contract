package com.graph.contract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.graph.contract.util.Web3jUilt;
import com.graph.contract.service.chian.EvmService;
import com.graph.contract.service.chian.EvmTokenService;
import com.graph.contract.util.BlockType;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSONObject;
import org.web3j.protocol.core.Request;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.Web3j;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.io.IOException;

@SpringBootTest
@Slf4j
class ContractApplicationTests {

	private @Autowired
    EvmService evmService;

	private @Autowired
	EvmTokenService evmTokenService;

	@Test
	void contextLoads() {
		String url = "https://eth.getblock.io/kovan/?api_key=ff78be83-cec1-4f04-8171-f95e25090d10";
		Web3j web3j = Web3jUilt.web3jMap.get(BlockType.BSC.name());
		Request<?, EthBlockNumber> request = web3j.ethBlockNumber();
		try {
			String result = JSONObject.toJSONString(request.send());
			System.out.println(result);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
	}

	@Test
	void bscContextLoads() {


		String blockType = BlockType.BSC.name();
		String height = evmService.getCurrentBlockNumber(blockType);
		System.out.println("height:" + height);

		System.out.println("version:" + evmService.findClientVersion(blockType));

		String hash = "0x93d4018f21c4844b6630e8af3ee2903365ee7806b01007f2de0bd4f657358d8e";
		String transaction = evmService.getTransactionByHash(blockType,hash);
		System.out.println("transaction:" + transaction);

		String account = "0x0fda1c484a810f7f2b39942397128aa9ec98b539";
		String bsc_eth_address = "0x2170ed0880ac9a755fd29b2688956bd959f933f8";
		BigDecimal balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType,account , bsc_eth_address));
		int decimal = evmTokenService.findEthTokenDecimalsByAddress(blockType,bsc_eth_address);
		BigDecimal amont = balance.divide(new BigDecimal(Math.pow(10, decimal)));
		System.out.println("account:" + account);
		System.out.println("eth balance:" + amont);

	}

	@Test
	void avaxContextLoads() {

		String blockType = BlockType.AVAX.name();
		String height = evmService.getCurrentBlockNumber(blockType);
		System.out.println("height:" + height);

		String hash = "0x7bb0cc437d19718a3ab98650f525bd23bfc643c163355811e7d743614598a1a9";
		String transaction = evmService.getTransactionByHash(blockType,hash);
		System.out.println("transaction:" + transaction);

		String account = "0x8ccef97219811727a2a8f39d75a186b6a5a3a44f";
		String tsd_address = "0x4fbf0429599460d327bd5f55625e30e4fc066095";
		BigDecimal balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType,account , tsd_address));
		int decimal = evmTokenService.findEthTokenDecimalsByAddress(blockType,tsd_address);
		BigDecimal amont = balance.divide(new BigDecimal(Math.pow(10, decimal)));
		System.out.println("account:" + account);
		System.out.println("tsd balance:" + amont);

	}


	@Test
	void ethContextLoads() {

		String blockType = BlockType.ETH.name();
		String height = evmService.getCurrentBlockNumber(blockType);
		System.out.println("height:" + height);

		String hash = "0x0be445db8b71d14c5ff8fd4ffe39fc6c2730f8fde7fa544095f6f32af7697855";
		String transaction = evmService.getTransactionByHash(blockType,hash);
		System.out.println("transaction:" + transaction);

		String account = "0x5157c43dc0bfe6019fa1111b2177ec152c865783";
		String pnk_address = "0x93ed3fbe21207ec2e8f2d3c3de6e058cb73bc04d";
		BigDecimal balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType,account , pnk_address));
		int decimal = evmTokenService.findEthTokenDecimalsByAddress(blockType,pnk_address);
		BigDecimal amont = balance.divide(new BigDecimal(Math.pow(10, decimal)));
		System.out.println("account:" + account);
		System.out.println("pnk balance:" + amont);

		String zrx_address = "0xe41d2489571d322189246dafa5ebde1f4699f498";
		BigDecimal zrx_balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType, account , zrx_address));
		System.out.println("zrx balance:" + zrx_balance.divide(new BigDecimal(Math.pow(10, evmTokenService.findEthTokenDecimalsByAddress(blockType,zrx_address)))));

		String ant_address = "0xa117000000f279d81a1d3cc75430faa017fa5a2e";
		BigDecimal ant_balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType, account , ant_address));
		System.out.println("ant balance:" + ant_balance.divide(new BigDecimal(Math.pow(10, evmTokenService.findEthTokenDecimalsByAddress(blockType,ant_address)))));



	}


	@Test
	void ftmContextLoads() {

		String blockType = BlockType.FANTOM.name();
		String height = evmService.getCurrentBlockNumber(blockType);
		System.out.println("height:" + height);

		String hash = "0x174f6d85a263276a416e67d8986feca3637628fadddc91158649fdf533ec35cf";
		String transaction = evmService.getTransactionByHash(blockType,hash);
		System.out.println("transaction:" + transaction);

		String account = "0x6ff6dfac4b8bea957dc48aad626d7631c2ba208c";
		String wftm_address = "0x21be370d5312f44cb42ce377bc9b8a0cef1a4c83";
		BigDecimal balance = new BigDecimal(evmTokenService.findEthTokenBalanceByAddress(blockType,account , wftm_address));
		int decimal = evmTokenService.findEthTokenDecimalsByAddress(blockType,wftm_address);
		BigDecimal amont = balance.divide(new BigDecimal(Math.pow(10, decimal)));
		System.out.println("account:" + account);
		System.out.println("ftm balance:" + amont);

	}

	@Test
	void getHash() {
//		String jsonStr = "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"logic\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"admin\",\"type\":\"address\"},{\"internalType\":\"bytes\",\"name\":\"data\",\"type\":\"bytes\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"previousAdmin\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"newAdmin\",\"type\":\"address\"}],\"name\":\"AdminChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"implementation\",\"type\":\"address\"}],\"name\":\"Upgraded\",\"type\":\"event\"},{\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"inputs\":[],\"name\":\"admin\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"admin_\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newAdmin\",\"type\":\"address\"}],\"name\":\"changeAdmin\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"implementation\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"implementation_\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newImplementation\",\"type\":\"address\"}],\"name\":\"upgradeTo\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"newImplementation\",\"type\":\"address\"},{\"internalType\":\"bytes\",\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"upgradeToAndCall\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"stateMutability\":\"payable\",\"type\":\"receive\"}]";
		String jsonStr = "[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_collector\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_tokenAddress\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint8\",\"name\":\"_mode\",\"type\":\"uint8\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_tokenAmount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"_id\",\"type\":\"bytes32\"}],\"name\":\"Collect\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"_id\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"_tokenAddr\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_tokenAmount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_expireTime\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_collectorCount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_fee\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_gasRefund\",\"type\":\"uint256\"}],\"name\":\"Create\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_sender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"_tokenAddr\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_tokenAmount\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_fee\",\"type\":\"uint256\"}],\"name\":\"Disperse\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"_id\",\"type\":\"bytes32\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_caller\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_remainCollector\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"_remainTokenAmount\",\"type\":\"uint256\"}],\"name\":\"Distribute\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"uint16\",\"name\":\"feeRate\",\"type\":\"uint16\"}],\"name\":\"FeeRateUpdate\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"receiver\",\"type\":\"address\"}],\"name\":\"FeeReceiverUpdate\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"oldOwner\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"newOwner\",\"type\":\"address\"}],\"name\":\"OwnershipUpdate\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_collector\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"_tokenAddr\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint8\",\"name\":\"_mode\",\"type\":\"uint8\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"RewardCollect\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_owner\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_feeReceiver\",\"type\":\"address\"}],\"name\":\"GetInitializeData\",\"outputs\":[{\"internalType\":\"bytes\",\"name\":\"\",\"type\":\"bytes\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"tokenAddr\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"addReward\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"rootSeed\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"seed\",\"type\":\"bytes32\"},{\"internalType\":\"uint256\",\"name\":\"remainAmount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"remainCount\",\"type\":\"uint256\"}],\"name\":\"calculateRandomAmount\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"rewardToken\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"forSender\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"forReceiver\",\"type\":\"uint256\"}],\"name\":\"configRewardRate\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"expireTime\",\"type\":\"uint256\"},{\"internalType\":\"uint8\",\"name\":\"mode\",\"type\":\"uint8\"},{\"internalType\":\"address\",\"name\":\"tokenAddr\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"tokenAmount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"collectorCount\",\"type\":\"uint256\"}],\"name\":\"create\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address payable[]\",\"name\":\"recipients\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"values\",\"type\":\"uint256[]\"}],\"name\":\"disperseEther\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"contract IERC20\",\"name\":\"token\",\"type\":\"address\"},{\"internalType\":\"address[]\",\"name\":\"recipients\",\"type\":\"address[]\"},{\"internalType\":\"uint256[]\",\"name\":\"values\",\"type\":\"uint256[]\"}],\"name\":\"disperseToken\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"luckyMoneyId\",\"type\":\"bytes32\"}],\"name\":\"distribute\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"luckyMoneyId\",\"type\":\"bytes32\"}],\"name\":\"expireTime\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"feeBase\",\"outputs\":[{\"internalType\":\"uint16\",\"name\":\"\",\"type\":\"uint16\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"feeOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"feeRate\",\"outputs\":[{\"internalType\":\"uint16\",\"name\":\"\",\"type\":\"uint16\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"feeReceiver\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getBlockAsSeed\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"_luckyMoneyId\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"_collector\",\"type\":\"address\"}],\"name\":\"getEthSignedMessageHash\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_creator\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"_startTime\",\"type\":\"uint256\"},{\"internalType\":\"address\",\"name\":\"_tokenAddr\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"_tokenAmount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"_collectorCount\",\"type\":\"uint256\"}],\"name\":\"getLuckyMoneyId\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"_luckyMoneyId\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"_collector\",\"type\":\"address\"}],\"name\":\"getMessageHash\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"luckyMoneyId\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"collector\",\"type\":\"address\"}],\"name\":\"hasCollected\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_owner\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_feeReceiver\",\"type\":\"address\"}],\"name\":\"initialize\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"luckyMoneys\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"creator\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"expireTime\",\"type\":\"uint256\"},{\"internalType\":\"uint8\",\"name\":\"mode\",\"type\":\"uint8\"},{\"internalType\":\"address\",\"name\":\"tokenAddr\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"tokenAmount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"collectorCount\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"nonce\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"refundAmount\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"_ethSignedMessageHash\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"_signature\",\"type\":\"bytes\"}],\"name\":\"recoverSigner\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"refundAmount\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"rewardBalances\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"name\":\"rewardConfigs\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"forSender\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"forReceiver\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"rewards\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint16\",\"name\":\"_feeRate\",\"type\":\"uint16\"}],\"name\":\"setFeeRate\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_receiver\",\"type\":\"address\"}],\"name\":\"setFeeReceiver\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"setRefundAmount\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address[]\",\"name\":\"rewardTokens\",\"type\":\"address[]\"}],\"name\":\"setRewardTokens\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes\",\"name\":\"sig\",\"type\":\"bytes\"}],\"name\":\"splitSignature\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"}],\"stateMutability\":\"pure\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"luckyMoneyId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"signature\",\"type\":\"bytes\"}],\"name\":\"submit\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"tokenAddr\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"withdrawReward\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

		JSONArray jsonArray = JSON.parseArray(jsonStr);
		System.out.println(jsonArray.size());
	}


	public String getHexString(String methodSignature) {
//		String methodSignature = "Upgraded(index_topic_1)";
		byte[] input = methodSignature.getBytes();
		byte[] hash = Hash.sha3(input);
		String result = Numeric.toHexString(hash).substring(0, 10);
		return result;
	}


}
