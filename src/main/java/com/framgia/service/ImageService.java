package com.framgia.service;

import java.util.List;

import com.framgia.bean.ImageInfo;

public interface ImageService extends BaseService {

	ImageInfo findById(Integer id, boolean flgUpdate);

	boolean removeImageInGroup(Integer id);

	ImageInfo getImageByUserCreate(String username, Integer idGroup);

	List<ImageInfo> getListImage(String condition, int page);

	Integer getNoOfRecord(String condition);

	boolean addVote(Integer idImage, Integer idUser);

	boolean removeVote(Integer id, Integer idUser);
}
