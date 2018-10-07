package com.atul.ethereum.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import com.atul.ethereum.model.SendEtherModel;
import com.atul.ethereum.services.IWeb3jBasicOPSService;


@RestController
public class EthereumController {

	@Autowired
	private IWeb3jBasicOPSService web3jService;
	
	@GetMapping(value = "/sendAsyncRequest")
	public String sendAsyncRequest() throws IOException, InterruptedException, ExecutionException{
		return web3jService.sendAsynchronousRequest();
	}
	
	@GetMapping(value = "/sendSyncRequest")
	public String sendSyncRequest() throws IOException, InterruptedException, ExecutionException{
		return web3jService.sendAsynchronousRequest();
	}
	
	@PostMapping(value = "/createWallet")
	public String createWallet() throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{
		return web3jService.createWallet();
	}
	
	@GetMapping(value = "/getBalance/{user}")
	public String createWallet(@PathVariable("user") String user) throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{
		return web3jService.etherBalance(user);
	}
	
	@GetMapping(value = "/getAddressFromWallet/{walletName}")
	public String getAddressFromWallet(@PathVariable("walletName") String walletName) throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException{
		return web3jService.getPublicAddressFromWallet(walletName);
	}
	
	@PostMapping(value = "/sendEther")
	public String sendEther(@RequestBody SendEtherModel sendEtherModel) throws IOException, InterruptedException, ExecutionException, TransactionException, CipherException{
		return web3jService.transcat(sendEtherModel);
	}
	
}