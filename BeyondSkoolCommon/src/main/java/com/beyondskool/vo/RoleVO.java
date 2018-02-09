package com.beyondskool.vo;


public class RoleVO {
	
	private String roleId;
	private String roleName;
	private int serialNo;
	private String roleDesc;
		
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public RoleVO(String roleId) {
		this.roleId = roleId;
	}
	public RoleVO() {
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	
}