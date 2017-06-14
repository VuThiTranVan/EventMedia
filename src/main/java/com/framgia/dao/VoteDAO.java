package com.framgia.dao;

import com.framgia.model.Vote;

public interface VoteDAO extends InterfaceDAO<Integer, Vote> {
	Vote findVoteToDelete(Integer idImage, Integer idUser);
}
