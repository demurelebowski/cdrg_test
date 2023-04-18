package org.drg.service;

import org.drg.dao.WalletDao;
import org.drg.entity.Wallet;
import org.drg.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WalletService {
	private final WalletDao walletDao;

	public WalletService(@Autowired WalletDao walletDao) {
		this.walletDao = walletDao;
	}

	private void validateId(Integer id) {
		if (Objects.isNull(id) || id < 0) {
			throw new ValidationException("Invalid id.");
		}
	}

	public Wallet readById(Integer id) {
		validateId(id);
		return walletDao.readById(id);
	}

	public void create(Wallet wallet) {

	}
}
