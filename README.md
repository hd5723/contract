使用步骤：
1、需要一台服务器安装geth客户端 也可以使用网络资源（不建议、也没做过）：我使用windows系统
   a、官网（https://geth.ethereum.org/downloads/）下载GETH windows客户端并安装
   b、命令（.\geth.exe -datadir data/rinkeby -syncmode full -rinkeby --rpcport 8545 --rpcaddr 10.0.40.23 -rpc --rpcapi "admin,db,eth,net,web3,personal" --allow-insecure-unlock  console）
      启动geth.exe
      rinkeby代表测试网络，正式网络去掉（geth详细命令：https://www.cnblogs.com/tinyxiong/p/7918706.html）

2、部署本demo，main启动。postman测试文件请导入eth_rinkeby.postman_collection.json

3、更多请联系QQ：1351009383