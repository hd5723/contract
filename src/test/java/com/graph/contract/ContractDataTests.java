package com.graph.contract;

import com.alibaba.fastjson.JSON;
import com.graph.contract.common.Constant;
import com.graph.contract.service.chian.EvmService;
import com.graph.contract.util.BlockType;
import com.graph.contract.util.Web3jUilt;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@Slf4j
class ContractDataTests {

	public Disposable ethSubscription;     //ETH交易事件订阅对象
	public Disposable ethMissSubscription;     //ETH交易空档事件订阅对象

	private @Autowired
    EvmService evmService;

	@Test
	void catchData() {
		BigInteger startBlockNum = BigInteger.valueOf(19938033);
		Web3j web3j = Web3jUilt.web3jMap.get(BlockType.BSC.name());
		//回放空档期间的交易
		BigInteger currentBlockNum=null;
		//获取当前区块号
		try {
			currentBlockNum = web3j.ethBlockNumber().send().getBlockNumber();
			System.out.println("  000 currentBlockNum= "+currentBlockNum.intValue());
			if(startBlockNum.compareTo(currentBlockNum) > 0) {
				return;  //测试曾经出现 currentBlockNum得到错误数字，比startBlockNum还小，这时不能启动监听
			}
		} catch (IOException e) {
			System.out.println("  111 getBlockNumber() Error: ");
			e.printStackTrace();
			return;   //出现异常不能启动监听
		}

		//创建开始与结束区块， 重放这段时间内的交易，防止遗漏
		DefaultBlockParameter startBlock = new DefaultBlockParameterNumber(startBlockNum);
		DefaultBlockParameter endBlock = new DefaultBlockParameterNumber(currentBlockNum);
		System.out.println("[ startTransferListen_ETH:  miss  startBlock="+startBlockNum+", endBlock="+currentBlockNum+"]");

		AtomicInteger block_EthMissSub = new AtomicInteger(startBlockNum.intValue());
		ethMissSubscription = web3j.replayPastTransactionsFlowable(startBlock, endBlock)
				.subscribe(tx -> {
					//更新检查过的区块高度
					block_EthMissSub.set(tx.getBlockNumber().intValue());

					String fromAddress = tx.getFrom();
					String toAddress   = tx.getTo();

//                  System.out.println("toAddress="+toAddress);
					if(Constant.Contract.listenerAddress.contains(fromAddress) || Constant.Contract.listenerAddress.contains(toAddress)) {  //发现了指定地址上的交易
						System.out.println("  ---replayPastTransactionsFlowable    block_EthMissSub = "+block_EthMissSub);
						System.out.println(JSON.toJSONString(tx));

						String txHash = tx.getHash();
						BigDecimal value = Convert.fromWei(tx.getValue().toString(), Convert.Unit.ETHER);
//						decodeInput(tx.getInput());
						Optional<TransactionReceipt> receipt = web3j.ethGetTransactionReceipt(txHash).send().getTransactionReceipt();
						executeLog(receipt.get().getLogs());

						String timestamp = "";
						try {
							EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(tx.getBlockNumber()), false).send();
							timestamp = String.valueOf(ethBlock.getBlock().getTimestamp());
						} catch (IOException e) {
							System.out.println("Block timestamp get failure,block number is {}" + tx.getBlockNumber());
							System.out.println("Block timestamp get failure,{}"+  e.getMessage());
						}
						// 监听以太坊上是否有系统生成地址的交易
						callBack_ETH(txHash,fromAddress,toAddress,value,timestamp);
					}
				}, error->{
					System.out.println("   ### replayPastTransactionsFlowable  error= "+ error);
					error.printStackTrace();

				});
	}

	public void decodeInput(String data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String inputData = data;
		String method = inputData.substring(0,10);
		log.info("Method >>>>>> " +method);
		String to = inputData.substring(10,74);
		String value = inputData.substring(74);
		Method refMethod = TypeDecoder.class.getDeclaredMethod("decode",String.class,int.class,Class.class);
		refMethod.setAccessible(true);
		Address address = (Address)refMethod.invoke(null,to,0,Address.class);
		log.info("Address>>>>>> " +address.toString());
		Uint256 amount = (Uint256) refMethod.invoke(null,value,0,Uint256.class);
		log.info("amount >>>>>> " +amount);
	}

	public void executeLog(List<Log> logs) {
		for (Log log : logs) {
//			Constant.Contract.listenerFunctions.contains()
			System.out.println(JSON.toJSONString(log) );
		}
	}

	//启动监听以太坊上的交易
	public void startTransactionListen_ETH() {
		Web3j web3j = Web3jUilt.web3jMap.get(BlockType.BSC.name());
		//监听当前区块以后的交易
		ethSubscription = web3j.transactionFlowable().subscribe(tx -> {
			//更新检查过的区块高度
			int block_EthSub = tx.getBlockNumber().intValue();
			System.out.println("  ---transactionFlowable  block_EthSub = "+block_EthSub);

			String txHash = tx.getHash();
			String fromAddress = tx.getFrom();
			String toAddress = tx.getTo();

			if(Constant.Contract.listenerAddress.contains(fromAddress) || Constant.Contract.listenerAddress.contains(toAddress)) {  //发现了指定地址上的交易
				BigDecimal value = Convert.fromWei(tx.getValue().toString(), Convert.Unit.ETHER);
				String timestamp = "";
				try {
					EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(tx.getBlockNumber()), false).send();
					timestamp = String.valueOf(ethBlock.getBlock().getTimestamp());

				} catch (IOException e) {
					System.out.println("Block timestamp get failure,block number is {}" + tx.getBlockNumber());
					System.out.println("Block timestamp get failure,{}"+  e.getMessage());
				}
				// 监听以太坊上是否有系统生成地址的交易
				callBack_ETH(txHash,fromAddress,toAddress,value,timestamp);
			}
		}, error->{
			System.out.println("   ### transactionFlowable  error= "+ error);
			error.printStackTrace();
		});

	}

	//token转账事件的处理函数
	public void  callBack_ETH(String txHash, String from, String to, BigDecimal value, String timestamp) {
		System.out.println("----callBack_Token:");
		System.out.println("    txHash = "+txHash);
		System.out.println("    from = "+from);
		System.out.println("    to = "+to);
		System.out.println("    value = "+value.doubleValue());

	}

	//token转账事件的处理函数
	public void  callBack_Token(String token, String txHash, String from, String to, BigDecimal value, String timestamp) {
		System.out.println("----callBack_Token:");
		System.out.println("    token = "+token);
		System.out.println("    txHash = "+token);
		System.out.println("    from = "+from);
		System.out.println("    to = "+to);
		System.out.println("    value = "+value.doubleValue());

	}

	@Test
	public void inputData() {
		List<String> addressList = new LinkedList<>();
		
		String inputData = "0xe63d38ed000000000000000000000000000000000000000000000000000000000000004000000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000004000000000000000000000000ff78b5eea770336c56d09f507af87cc3cf331589000000000000000000000000aae8ab3bc9076e4825dc46119ae97b670d7f49e80000000000000000000000001ce6af5648087ba303afdee849b9b48542da40560000000000000000000000006d76f5d89dc37d1425d16e6ee5809f3d2578864700000000000000000000000000000000000000000000000000000000000000040000000000000000000000000000000000000000000000000000b5e620f480000000000000000000000000000000000000000000000000000000b5e620f480000000000000000000000000000000000000000000000000000000b5e620f480000000000000000000000000000000000000000000000000000000b5e620f48000";
		String funtionSign = inputData.substring(0,10);
		System.out.println(funtionSign);
		System.out.println("0000000000000000000000000000000000000000000000000000000000000040".length());

		int length = inputData.length() -10;
		int index = 10;
		String address = null;
		while(index<length){
			address = inputData.substring(index , index + 64);
			addressList.add(address);
			index += 64;
		}
		System.out.println(JSON.toJSONString(addressList));
	}


}
