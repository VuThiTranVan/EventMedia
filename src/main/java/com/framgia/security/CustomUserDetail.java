package com.framgia.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.framgia.security.CustomUserDetail;

public class CustomUserDetail implements UserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	public static final int ZERO_NUMBER = 0;
	private String userId;
	private String password;
	private String username;
	private Collection<GrantedAuthority> authorities;

	public CustomUserDetail() {
		super();
	}

	public CustomUserDetail(String userId, String username, String password, Collection<GrantedAuthority> authorities) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public void eraseCredentials() {
		password = null;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return username.equals(((CustomUserDetail) obj).username);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

}
