package com.kgate.service;

import java.util.List;

import com.kgate.entity.UserDocument;

public interface UserDocumentService {

	
	UserDocument findById(int id);

	List<UserDocument> findAll();

	void saveDocument(UserDocument userDocument);

	void deleteById(int id);
}
