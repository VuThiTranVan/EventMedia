package com.framgia.dao;

import com.framgia.model.Image;

public interface ImageDAO {

	public Image findById(Integer id, boolean isLock);

	public void update(Image image);
}
