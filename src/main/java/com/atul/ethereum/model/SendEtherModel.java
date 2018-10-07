package com.atul.ethereum.model;

public class SendEtherModel {

	String walletName;
	String walletPassPhres;
	String sendersAddress;
	String receiversAddress;
	double ethers;
	public String getWalletName() {
		return walletName;
	}
	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
	public String getWalletPassPhres() {
		return walletPassPhres;
	}
	public void setWalletPassPhres(String walletPassPhres) {
		this.walletPassPhres = walletPassPhres;
	}
	public String getReceiversAddress() {
		return receiversAddress;
	}
	public void setReceiversAddress(String receiversAddress) {
		this.receiversAddress = receiversAddress;
	}
	public double getEthers() {
		return ethers;
	}
	public void setEthers(double ethers) {
		this.ethers = ethers;
	}
	public String getSendersAddress() {
		return sendersAddress;
	}
	public void setSendersAddress(String sendersAddress) {
		this.sendersAddress = sendersAddress;
	}
	@Override
	public String toString() {
		return "SendEtherModel [walletName=" + walletName + ", walletPassPhres=" + walletPassPhres + ", sendersAddress="
				+ sendersAddress + ", receiversAddress=" + receiversAddress + ", ethers=" + ethers + "]";
	}
	
	
}
