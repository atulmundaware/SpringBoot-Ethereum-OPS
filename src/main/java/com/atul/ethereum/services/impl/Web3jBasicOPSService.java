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
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.atul.ethereum.model.SendEtherModel;
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
				walletPath + "/" + fileName);
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
	
	
	public String getPublicAddressFromWallet(String walletName) throws NoSuchAlgorithmException, NoSuchProviderException,
	InvalidAlgorithmParameterException, CipherException, IOException {
		Credentials credentials = WalletUtils.loadCredentials(defaultWalletPassword,
				walletPath + "/" + walletName);
		System.out.println("Users wallet address : " + credentials.getAddress());
		return "Users wallet address : " + credentials.getAddress();
	}
	
	
	public String transcat(SendEtherModel sendEtherModel) throws IOException, CipherException, InterruptedException, TransactionException, ExecutionException{
		
		
		Admin web3jAdmin = Admin.build(new HttpService());
		
		
		Credentials credentials = WalletUtils.loadCredentials(defaultWalletPassword,
				walletPath + "/" + sendEtherModel.getWalletName());
		
		PersonalUnlockAccount personalUnlockAccount = web3jAdmin.personalUnlockAccount(sendEtherModel.getSendersAddress(), defaultWalletPassword).send();
		if(personalUnlockAccount.accountUnlocked()) {
			BigInteger value = Convert.toWei(sendEtherModel.getEthers()+"", Convert.Unit.ETHER).toBigInteger();
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
					sendEtherModel.getSendersAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

			BigInteger gasLimit = new BigInteger("21000");
			BigInteger gasPrice = new BigInteger("21000");
			
		    BigInteger nonce = ethGetTransactionCount.getTransactionCount();
			RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
		             nonce, gasPrice, gasLimit, sendEtherModel.getReceiversAddress(), value);
//			rawTransaction.send();
//			rawTransaction.
			
			System.out.println(rawTransaction);
		}
//		
//		CompletableFuture<TransactionReceipt> transactionReceipt = Transfer.sendFunds(
//		        web3j, credentials, sendEtherModel.getReceiversAddress(), BigDecimal.valueOf(sendEtherModel.getEthers()), Convert.Unit.ETHER).sendAsync();
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