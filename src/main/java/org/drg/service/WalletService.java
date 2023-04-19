package org.drg.service;

import org.apache.commons.lang3.StringUtils;
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

	private void validateCreation(Wallet wallet) {
		if (Objects.isNull(wallet)) {
			throw new ValidationException("Object is null.");
		}
		if (StringUtils.isEmpty(wallet.getEmail())) {
			throw new ValidationException("E-mail is empty.");
		}
		if (StringUtils.isEmpty(wallet.getPhone())) {
			throw new ValidationException("Phone is empty.");
		}
		if (StringUtils.isEmpty(wallet.getFirstName())) {
			throw new ValidationException("Firs name is empty.");
		}
		if (StringUtils.isEmpty(wallet.getLastName())) {
			throw new ValidationException("Last name is empty.");
		}
		if (Objects.isNull(wallet.getCurrency())) {
			throw new ValidationException("Currency is invalid.");
		}
	}

	public Wallet readById(Integer id) {
		validateId(id);
		return walletDao.readById(id);
	}

	public void create(Wallet wallet) {
		validateCreation(wallet);
		walletDao.create(wallet);
	}
}
