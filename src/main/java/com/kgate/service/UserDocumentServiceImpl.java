package com.kgate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.entity.UserDocument;
import com.kgate.repository.UserDocumentRepository;

@Service
public class UserDocumentServiceImpl implements UserDocumentService {

	@Autowired
	UserDocumentRepository userDocumentRepository;

	@Override
	public UserDocument download(int docCode) {
		// TODO Auto-generated method stub
		return userDocumentRepository.download(docCode);
	}

	@Override
	public List<UserDocument> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDocument(UserDocument userDocument) {
		// TODO Auto-generated method stub
		userDocumentRepository.save(userDocument);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserDocument> findDoc(String empCode) {
		// TODO Auto-generated method stub
		return userDocumentRepository.findDoc(empCode);
	}

}
