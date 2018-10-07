package com.atul.ethereum.services;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import com.atul.ethereum.model.SendEtherModel;

public interface IWeb3jBasicOPSService {

	public String sendAsynchronousRequest() throws IOException, InterruptedException, ExecutionException;

	public String sendSynchronousRequest() throws IOException, InterruptedException, ExecutionException; 

	public String createWallet() throws NoSuchAlgorithmException, NoSuchProviderException,
	InvalidAlgorithmParameterException, CipherException, IOException;
	
	public String etherBalance(String user) throws InterruptedException, ExecutionException, NoSuchAlgorithmException,
	NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException;

	public String getPublicAddressFromWallet(String walletName) throws NoSuchAlgorithmException, NoSuchProviderException,
	InvalidAlgorithmParameterException, CipherException, IOException;
	
	public String transcat(SendEtherModel sendEtherModel) throws IOException, CipherException, InterruptedException, TransactionException, ExecutionException;
		
}

