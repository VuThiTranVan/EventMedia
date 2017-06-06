package com.framgia.dao;

import com.framgia.model.Image;

public interface ImageDAO {

	public Image findById(Integer id, boolean isLock);

	public void update(Image image);

	public Image getImageByUserCreate(String username, Integer idGroup);

	Long findByUserCreate(String username);

}
