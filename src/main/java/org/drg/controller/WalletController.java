package org.drg.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.drg.dto.TransactionDto;
import org.drg.dto.WalletDto;
import org.drg.entity.Transaction;
import org.drg.entity.Wallet;
import org.drg.service.WalletTransactionService;
import org.drg.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wallet")
@ApiOperation("Wallet API")
public class WalletController {
	@Autowired
	public WalletTransactionService walletService;

	@ApiOperation(value = "Get a wallet by id", notes = "Returns wallet as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{walletId}")
	public WalletDto readById(@PathVariable @ApiParam(value = "Wallet id", example = "9876") Integer walletId) {
		Wallet wallet = walletService.readById(walletId);
		return ConverterUtil.convertToWalletDto(wallet);
	}

	@ApiOperation(value = "Create a wallet", notes = "Returns a wallet with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/create")
	public WalletDto createWallet(@RequestBody WalletDto walletDto) {
		Wallet wallet = ConverterUtil.convertToWallet(walletDto);
		walletService.createWallet(wallet);
		return ConverterUtil.convertToWalletDto(wallet);
	}

	@ApiOperation(value = "Create a transaction", notes = "Returns a transaction with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/transaction")
	public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
		Transaction transaction = ConverterUtil.convertToTransaction(transactionDto);
		walletService.createTransaction(transaction);
		return ConverterUtil.convertToTransactionDto(transaction);
	}

	@ApiOperation(value = "Unblock a wallet")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully unblocked"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/reset/{walletId}")
	public Boolean resetWalletBlock(@PathVariable @ApiParam(value = "Wallet id", example = "9876") Integer walletId) {
		return walletService.resetWalletBlock(walletId);
	}
}
