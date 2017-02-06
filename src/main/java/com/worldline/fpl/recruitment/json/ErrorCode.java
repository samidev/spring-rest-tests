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

	UNEXISTED_ACCOUNT(HttpStatus.NOT_FOUND),
	UNEXISTED_TRANSACTION(HttpStatus.NOT_FOUND),
	INVALID_TRANSACTION(HttpStatus.BAD_REQUEST),
	TRANSACTION_NOT_BELONG_ACCOUNT(HttpStatus.FORBIDDEN);

	@Getter
	private HttpStatus httpStatus;
}
