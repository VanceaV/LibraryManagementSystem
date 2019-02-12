package com.smoothstack.entity;

public class LibraryBranch extends Entity {

	private String branchName;
	private String branchAddress;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return getId() + ") BranchName " + branchName + " BranchAddress " + branchAddress;
	}

}
