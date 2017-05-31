package com.framgia.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.PermissionInfo;
import com.framgia.bean.UserInfo;
import com.framgia.bean.VoteInfo;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.model.Vote;

public class ConvetBeanAndModel {
	public static User convertUserBeanToModel(UserInfo userInfo) {
		if (userInfo != null) {
			User user = new User();
			user.setId(userInfo.getId());
			user.setUsername(userInfo.getUsername());
			user.setPassword(passwordEncoderToString(userInfo.getPassword()));
			user.setName(userInfo.getName());
			user.setGender(userInfo.getGender());
			user.setBirthday(userInfo.getBirthday());
			user.setPhone(userInfo.getPhone());
			user.setEmail(userInfo.getEmail());
			user.setStatusJoin(userInfo.getStatusJoin());
			user.setIdGroup(userInfo.getIdGroup());
			user.setDeleteFlag(Constants.DEL_FLG);
			user.setDateCreate(userInfo.getDateCreate());
			user.setUserCreate(userInfo.getUserUpdate());
			user.setDateUpdate(userInfo.getDateUpdate());
			user.setUserUpdate(userInfo.getUserUpdate());

			if (null != userInfo.getPermission()) {
				Permission permission = new Permission();
				permission.setId(userInfo.getPermission().getId());
			} else {
				Permission permission = new Permission();
				permission.setId(Constants.PERMISSION_CODE_USER);
				user.setPermission(permission);
				user.setStatusJoin(Constants.STATUSJOIN_CODE_FREE);
			}

			return user;
		}
		return null;
	}

	public static UserInfo convertUserModelToBean(User user) {

		if (user != null) {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(user.getId());
			userInfo.setUsername(user.getUsername());
			userInfo.setPassword(user.getPassword());
			userInfo.setName(user.getName());
			userInfo.setGender(Constants.GENDER_VALUE_MALE);

			if (Constants.GENDER_CODE_FMALE.equals(user.getGender())) {
				userInfo.setGender(Constants.GENDER_VALUE_FMALE);
			}

			userInfo.setBirthday(user.getBirthday());
			userInfo.setPhone(user.getPhone());
			userInfo.setEmail(user.getEmail());
			userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_FREE);

			if (user.getStatusJoin().equals(Constants.STATUSJOIN_CODE_APPOVE))
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_APPOVE);

			if (user.getStatusJoin().equals(Constants.STATUSJOIN_CODE_REQUEST)) {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_REQUEST);
			}

			userInfo.setIdGroup(user.getIdGroup());
			userInfo.setDeleteFlag(user.getDeleteFlag());
			userInfo.setDateCreate(user.getDateCreate());
			userInfo.setUserCreate(user.getUserUpdate());
			userInfo.setDateUpdate(user.getDateUpdate());
			userInfo.setUserUpdate(user.getUserUpdate());

			if (null != user.getPermission()) {
				PermissionInfo permissionInfo = new PermissionInfo();
				permissionInfo.setId(user.getPermission().getId());
				userInfo.setPermission(permissionInfo);
			}
			return userInfo;
		}
		return null;
	}

	public static GroupInfo convertGroupModelToBean(Group group) {
		if (group != null) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setId(group.getId());

			if (group.getUserCreate() != null) {
				groupInfo.setUserCreate(convertUserModelToBean(group.getUserCreate()));
			}

			groupInfo.setName(group.getName());
			groupInfo.setDescription(group.getDescription());
			groupInfo.setNote(group.getNote());
			groupInfo.setType(group.getType().toString());
			groupInfo.setStatus(group.getStatus().toString());
			if (group.getDateStart() != null) {
				groupInfo.setDateStart(DateUtil.convertDatetoString(group.getDateStart()));
			}
			if (group.getDateEnd() != null) {
				groupInfo.setDateEnd(DateUtil.convertDatetoString(group.getDateEnd()));
			}
			groupInfo.setDeleteFlag(group.getDeleteFlag());
			groupInfo.setDateCreate(group.getDateCreate());
			groupInfo.setUserUpdate(group.getUserUpdate());
			groupInfo.setDateUpdate(group.getDateUpdate());

			// list Image
			if (group.getImage() != null) {
				List<ImageInfo> listImageInfo = new ArrayList<ImageInfo>();
				for (Image item : group.getImage()) {
					if (item == null)
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					ImageInfo imageInfo = convertImageModelToBean(item);
					listImageInfo.add(imageInfo);

				}
				groupInfo.setImage(listImageInfo);
			}

			// list User
			if (group.getUser() != null) {
				List<UserInfo> listUserInfo = new ArrayList<UserInfo>();
				for (User item : group.getUser()) {
					if (item == null)
						continue;

					if (item.getPermission().getId().equals(Constants.PERMISSION_CODE_MANAGER))
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					UserInfo userInfo = convertUserModelToBean(item);
					listUserInfo.add(userInfo);
				}
				groupInfo.setUser(listUserInfo);
			}
			return groupInfo;
		}
		return null;
	}

	public static Group convertGroupBeanToModel(GroupInfo groupInfo) {
		if (groupInfo != null) {
			Group group = new Group();
			group.setId(groupInfo.getId());
			if (groupInfo.getUserCreate() != null) {
				group.setUserCreate(convertUserBeanToModel(groupInfo.getUserCreate()));
			}
			group.setName(groupInfo.getName());
			group.setDescription(groupInfo.getDescription());
			group.setNote(groupInfo.getNote());
			group.setType(Integer.parseInt(groupInfo.getType()));
			group.setStatus(Integer.parseInt(groupInfo.getStatus()));
			group.setDateStart(DateUtil.convertStringtoDate(groupInfo.getDateStart()));
			group.setDateEnd(DateUtil.convertStringtoDate(groupInfo.getDateEnd()));
			group.setDeleteFlag(groupInfo.getDeleteFlag());
			group.setDateCreate(groupInfo.getDateCreate());
			group.setUserUpdate(groupInfo.getUserUpdate());
			group.setDateUpdate(groupInfo.getDateUpdate());

			// list Image
			if (groupInfo.getImage() != null) {
				List<Image> listImage = new ArrayList<Image>();
				for (ImageInfo item : groupInfo.getImage()) {
					if (item == null)
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					Image image = convertImageBeanToModel(item);
					listImage.add(image);

				}
				group.setImage(listImage);
			}

			// list User
			if (groupInfo.getUser() != null) {
				List<User> listUser = new ArrayList<User>();
				for (UserInfo item : groupInfo.getUser()) {
					if (item == null)
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					User user = convertUserBeanToModel(item);
					listUser.add(user);

				}
				group.setUser(listUser);
			}

			return group;
		}
		return null;
	}

	public static ImageInfo convertImageModelToBean(Image image) {
		if (image != null) {
			ImageInfo imageInfo = new ImageInfo();
			imageInfo.setId(image.getId());
			imageInfo.setUrl(image.getUrl());
			imageInfo.setDescription(image.getDescription());
			imageInfo.setTitle(image.getTitle());
			imageInfo.setDeleteFlag(image.getDeleteFlag());
			imageInfo.setUserCreate(image.getUserCreate());
			imageInfo.setDateCreate(image.getDateCreate());
			imageInfo.setUserUpdate(image.getUserUpdate());
			imageInfo.setDateUpdate(image.getDateUpdate());

			if (image.getVote() != null) {
				List<VoteInfo> listVoteInfo = new ArrayList<VoteInfo>();
				for (Vote item : image.getVote()) {
					if (item == null)
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					VoteInfo voteInfo = new VoteInfo();
					UserInfo userInfo = new UserInfo();
					userInfo.setId(item.getUser().getId());
					userInfo.setUsername(item.getUser().getUsername());
					userInfo.setIdGroup(item.getUser().getIdGroup());
					voteInfo.setUser(userInfo);
					listVoteInfo.add(voteInfo);

				}
				imageInfo.setVotes(listVoteInfo);
			}

			return imageInfo;
		}
		return null;
	}

	public static Image convertImageBeanToModel(ImageInfo imageInfo) {
		if (imageInfo != null) {
			Image image = new Image();
			image.setId(imageInfo.getId());
			image.setUrl(imageInfo.getUrl());
			image.setDescription(imageInfo.getDescription());
			image.setTitle(imageInfo.getTitle());
			image.setDeleteFlag(imageInfo.getDeleteFlag());
			image.setUserCreate(imageInfo.getUserCreate());
			image.setDateCreate(imageInfo.getDateCreate());
			image.setUserUpdate(imageInfo.getUserUpdate());
			image.setDateUpdate(imageInfo.getDateUpdate());

			if (imageInfo.getVotes() != null) {
				List<Vote> listVote = new ArrayList<Vote>();
				for (VoteInfo item : imageInfo.getVotes()) {
					if (item == null)
						continue;

					if (item.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
						continue;

					Vote vote = new Vote();
					User user = new User();
					user.setId(item.getUser().getId());
					user.setUsername(item.getUser().getUsername());
					user.setIdGroup(item.getUser().getIdGroup());
					vote.setUser(user);
					listVote.add(vote);

				}
				image.setVote(listVote);
			}

			return image;
		}
		return null;
	}

	public static VoteInfo convertVoteModelToBean(Vote vote) {
		if (vote != null) {
			VoteInfo voteInfo = new VoteInfo();
			voteInfo.setId(vote.getId());

			if (vote.getImage() != null) {
				voteInfo.setImage(convertImageModelToBean(vote.getImage()));
			}

			if (vote.getUser() != null) {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(vote.getUser().getId());
				userInfo.setUsername(vote.getUser().getUsername());
				userInfo.setIdGroup(vote.getUser().getIdGroup());
				voteInfo.setUser(userInfo);
			}

			voteInfo.setDeleteFlag(vote.getDeleteFlag());
			voteInfo.setDateCreate(vote.getDateCreate());
			voteInfo.setUserUpdate(vote.getUserUpdate());
			voteInfo.setDateUpdate(vote.getDateUpdate());

			return voteInfo;
		}
		return null;
	}

	public static Vote convertVoteBeanToModel(VoteInfo voteInfo) {
		if (voteInfo != null) {
			Vote vote = new Vote();
			vote.setId(voteInfo.getId());

			if (voteInfo.getImage() != null) {
				vote.setImage(convertImageBeanToModel(voteInfo.getImage()));
			}

			if (voteInfo.getUser() != null) {
				User user = new User();
				user.setId(voteInfo.getUser().getId());
				user.setUsername(voteInfo.getUser().getUsername());
				user.setIdGroup(voteInfo.getUser().getIdGroup());
				vote.setUser(user);
			}

			vote.setDeleteFlag(voteInfo.getDeleteFlag());
			vote.setDateCreate(voteInfo.getDateCreate());
			vote.setUserUpdate(voteInfo.getUserUpdate());
			vote.setDateUpdate(voteInfo.getDateUpdate());

			return vote;
		}
		return null;
	}

	// encode password
	public static String passwordEncoderToString(String token) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(token);
		return hashedPassword;
	}
}
