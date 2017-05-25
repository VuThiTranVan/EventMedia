package com.framgia.security;

public enum PermissionType {

	ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER"), USER("ROLE_USER");

	String permissionType;

	private PermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionType() {
		return permissionType;
	}

}
