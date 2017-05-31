package com.framgia.dao;

import java.util.List;

import com.framgia.model.Image;

public interface ImageDAO {

	public Image findById(Integer id, boolean isLock);

	public void update(Image image);

	public Image getImageByUserCreate(String username, Integer idGroup);

	Long findByUserCreate(String username);

	public List<Image> getListImage(String condition, int first, int max);

	public Integer getNoOfRecord(String condition);
}
