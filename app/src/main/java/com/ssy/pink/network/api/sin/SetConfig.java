package com.ssy.pink.network.api.sin;

public class SetConfig {
	
	private String servertime;
	
	private String nonce;
	
	private String rsakv;
	
	private String pcid;
	
	private String pubkey;
	
	private String exectime;

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	public String getPubkey() {
		return pubkey;
	}

	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}

	public String getExectime() {
		return exectime;
	}

	public void setExectime(String exectime) {
		this.exectime = exectime;
	}

	public String getServertime() {
		return servertime;
	}

	public void setServertime(String servertime) {
		this.servertime = servertime;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getRsakv() {
		return rsakv;
	}

	public void setRsakv(String rsakv) {
		this.rsakv = rsakv;
	}

	@Override
	public String toString() {
		return "SetConfig{" +
				"servertime='" + servertime + '\'' +
				", nonce='" + nonce + '\'' +
				", rsakv='" + rsakv + '\'' +
				", pcid='" + pcid + '\'' +
				", pubkey='" + pubkey + '\'' +
				", exectime='" + exectime + '\'' +
				'}';
	}
}
