// ******************************COPYRIGHT NOTICE*******************************
// All rights reserved.  This material is confidential and proprietary to Excel
// Technology International (Hong Kong) Limited and no part of this material 
// should be reproduced, published in any form by any means, electronic or
// mechanical including photocopy or any information storage or retrieval system
// nor should the material be disclosed to third parties without the express
// written authorization of Excel Technology International (Hong Kong) Limited.

/*
<PRE>
 * **************************VSS GENERATED VERSION NUMBER************************
 * $Revision:  $
 * ******************************PROGRAM DESCRIPTION*****************************
 * Program Name   : Formatter.java
 * Nature	     : Application
 * Description    : Class for format 
 * Creation Date  : 2018/07/13
 * Creator        : Andy Chen
 * ******************************MODIFICATION HISTORY****************************
 * Modify Date    : 
 * Modifier       : 
 * CR / SIR No.   : 
 * Description    : 
 * ******************************************************************************
</PRE>
 */
package com.andy.practice;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Config {
	private int field = 2;
	private BigDecimal multiplier = new BigDecimal("1");
	private String input = "data.csv";
	private String output = "data-fr.csv";
	private NumberFormat currencyFormatTo = NumberFormat.getCurrencyInstance(Locale.FRANCE); 

	public NumberFormat getCurrencyFormatTo() {
		return currencyFormatTo;
	}
	
	public NumberFormat getCurrencyFormatByLocale(Locale locale) {
		return NumberFormat.getCurrencyInstance(locale);
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}

/*
 * ****************************VSS GENERATED HISTORY***************************
 * $History:  $
 * ****************************************************************************
 */