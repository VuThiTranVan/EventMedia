package com.framgia.util;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.framgia.security.CustomUserDetail;

public class Helpers {

	public static CustomUserDetail getUserDetail() {
		return (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static Integer getIdUser() {
		CustomUserDetail userDetail = null;
		try {
			userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetail == null) {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
		return Integer.valueOf(userDetail.getUserId());
	}

	public static String getUsername() {
		CustomUserDetail userDetail = null;
		try {
			userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetail == null) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return userDetail.getUsername();
	}

	public static boolean isEmpty(String value) {
		return value == null || value.equals("") || value.trim().equals("");
	}

	public static boolean isEmpty(List value) {
		return value == null || value.size() == 0;
	}

	// HieuPV Check format FIle
	public static Boolean checkFormatFile(String filename, String format) {
		String suffixesFile = null;
		try {
			suffixesFile = filename.substring(filename.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return false;
		}
		if (format.equals(suffixesFile)) {
			return true;
		} else {
			return false;
		}

	}
}
