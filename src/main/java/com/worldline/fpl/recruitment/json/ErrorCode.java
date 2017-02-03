package com.worldline.fpl.recruitment.json;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

/**
 * Error code
 * 
 * @author A525125
 *
 */
@AllArgsConstructor
public enum ErrorCode {

	INVALID_ACCOUNT(HttpStatus.NOT_FOUND),
	INVALID_TRANSACTION(HttpStatus.NOT_FOUND),
	TRANSACTION_NOT_BELONG_ACCOUNT(HttpStatus.FORBIDDEN);

	@Getter
	private HttpStatus httpStatus;
}
