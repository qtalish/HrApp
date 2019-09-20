package com.kgate.service;

import java.util.List;

import com.kgate.entity.UserDocument;

public interface UserDocumentService {

	UserDocument download(int id);

	List<UserDocument> findAll();

	void saveDocument(UserDocument userDocument);

	void deleteById(int id);

	UserDocument findById(int docId);

	List<UserDocument> findDoc(String empCode);
}
