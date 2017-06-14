package com.framgia.dao;

import java.util.List;

import com.framgia.model.Image;

public interface ImageDAO extends InterfaceDAO<Integer, Image> {

	Image findById(Integer id, boolean isLock);

	Image getImageByUserCreate(String username, Integer idGroup);

	Long findByUserCreate(String username);

	Image findImage(String username);

	List<Image> getListImage(String condition, int first, int max);

	Integer getNoOfRecord(String condition);
}
