package com.atul.ethereum.services.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.atul.ethereum.services.IWeb3jBasicOPSService;


@Service
public class Web3jBasicOPSService implements IWeb3jBasicOPSService {

	@Autowired
	private Web3j web3j;
	
	@Value("${app.wallet.location}")
	private String walletPath;

	@Value("${app.wallet.password}")
	private String defaultWalletPassword;
	
	public String sendAsynchronousRequest() throws IOException, InterruptedException, ExecutionException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
		return web3ClientVersion.getWeb3ClientVersion();
	}

	public String sendSynchronousRequest() throws IOException, InterruptedException, ExecutionException {
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		return web3ClientVersion.getWeb3ClientVersion();
	}

	public String createWallet() throws NoSuchAlgorithmException, NoSuchProviderException,
			InvalidAlgorithmParameterException, CipherException, IOException {
		String fileName = WalletUtils.generateFullNewWalletFile(defaultWalletPassword,
				new File(walletPath));

		Credentials credentials = WalletUtils.loadCredentials(defaultWalletPassword,
				walletPath + fileName);
		System.out.println("Users wallet address : " + credentials.getAddress());
		return "Users wallet address : " + credentials.getAddress();
	}

	public String etherBalance(String userAddress) throws InterruptedException, ExecutionException, NoSuchAlgorithmException,
			NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {

		EthGetBalance ethGetBalance = web3j.ethGetBalance(userAddress, DefaultBlockParameterName.LATEST)
				.sendAsync()
				.get();

		BigInteger wei = ethGetBalance.getBalance();
		
		return "Ether Balance = " + wei.toString();

	}

	
	
	public String transcat(String walletName, String walletPassPhres,String receiversAddress, double ethers) throws IOException, CipherException, InterruptedException, ExecutionException, TransactionException{
		
		
		Credentials credentials = WalletUtils.loadCredentials(defaultWalletPassword,
				walletPath + "/" + walletName);
		CompletableFuture<TransactionReceipt> transactionReceipt = Transfer.sendFunds(
		        web3j, credentials, receiversAddress, BigDecimal.valueOf(ethers), Convert.Unit.ETHER).sendAsync();
//		transactionReceipt.sendAsync()getClass();
//		String currentTime = java.time.LocalDateTime.now().toString(); 	
		/*String data = "Transfered " + ethers + " ETH from " + credentials.getAddress() +
					  " to ->" + receiversAddress + " at " + currentTime +
					  "\n\n Gas used : " + transactionReceipt.getGasUsed().send +
					  "\n Block Hash : " + transactionReceipt.getBlockHash() + 
					  "\n Transaction Hash : " + transactionReceipt.getTransactionHash() +
				  	  "\n Cumulative Gas Used : " + transactionReceipt.getCumulativeGasUsed() + 
				  	  "\n Block Number Raw : " + transactionReceipt.getBlockNumberRaw();*/
		
//		Constants.writeFile(data, "TransactionRecipt - " + currentTime.replace(":", "-")  + Constants.TXT);
		
		EthGetBalance ethGetBalance = web3j
				  .ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
				  .sendAsync()
				  .get();

		BigInteger wei = ethGetBalance.getBalance();
		
		return "Tansaction Completed your balance is " + wei.toString();
	}
	
	
}