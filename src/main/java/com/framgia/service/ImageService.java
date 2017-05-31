package com.framgia.service;

import com.framgia.bean.ImageInfo;

public interface ImageService extends BaseService {

	public ImageInfo findById(Integer id, boolean flgUpdate);

	public boolean removeImageInGroup(Integer id);
}
