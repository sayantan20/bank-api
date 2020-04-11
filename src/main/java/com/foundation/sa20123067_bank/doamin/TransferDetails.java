package com.foundation.sa20123067_bank.doamin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferDetails {

	private Long fromAccountNumber;
 
	private Long toAccountNumber;
 
	private Double transferAmount;
}
