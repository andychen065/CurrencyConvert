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

import java.util.List;

public class Formatter {
	private Importer src= null;
	private Config cfg = null;
	private String COMMA = ",";
	public Formatter(Importer imp, Config cfg) {
		this.src = imp;
		this.cfg = cfg;
	}

	public void format() {
		try {
			for (int i=1; i < src.getRows().size(); i++){
				List row = (List)src.getRows().get(i);
				String strValue = (String)row.get(cfg.getField() - 1);
				String currency = strValue.replace("\"", "");//remove quota
				currency = currency.replace(COMMA, "");//remove comma
				double dbValue = Double.parseDouble(strValue);
				dbValue = dbValue *  cfg.getMultiplier().doubleValue();
				row.set(cfg.getField() - 1, cfg.getCurrencyFormatTo().format(dbValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * ****************************VSS GENERATED HISTORY***************************
 * $History:  $
 * ****************************************************************************
 */