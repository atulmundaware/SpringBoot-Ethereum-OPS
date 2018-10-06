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
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

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
	
	/*@RequestMapping(value = "/sendEther/{to}/{ethers}")
	public String sendEther(@PathVariable("to") String to,
			@PathVariable("ethers") double ethers) throws IOException, InterruptedException, ExecutionException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, TransactionTimeoutException{
		return web3jService.transcat(to,ethers);
	}*/
	
}