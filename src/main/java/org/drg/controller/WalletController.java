package org.drg.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.drg.dto.WalletDto;
import org.drg.entity.Wallet;
import org.drg.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import org.drg.utils.ConverterUtil;
@RestController
@RequestMapping(value = "/wallet")
@ApiOperation("Wallet API")
public class WalletController {
	@Autowired
	public WalletService walletService;

	@ApiOperation(value = "Get a wallet by id", notes = "Returns wallet as per the id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{walletId}")
	public String readById(@PathVariable @ApiParam(value = "Wallet id", example = "9876") Long walletId) {
		//Wallet wallet = walletService.readById(reservationId);
		return "Yo"+ walletId;
	}

	@ApiOperation(value = "Create a wallet", notes = "Returns a wallet with created id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 400, message = "Invalid object was provided") })
	@PostMapping("/")
	public WalletDto createWallet(@RequestBody WalletDto walletDto) {
		Wallet reservation = ConverterUtil.convertToWallet(walletDto);
		walletService.create(reservation);
		return ConverterUtil.convertToWalletDto(reservation);
	}
}
