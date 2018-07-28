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
 * Program Name   : Importer.java
 * Nature	     : Application
 * Description    : Class for import file
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

public class Importer {
	private Config cfg;
	private List rows = null;
	public Importer(Config cfg) throws Exception {
		this.cfg = cfg;
	}
	public boolean doImport() throws Exception {
		BufferedReader br = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		List vtr = new Vector();

		if ("stdin".equalsIgnoreCase(cfg.getInput())){
			isr = new InputStreamReader(System.in, "UTF-8");
			System.out.println("Read data from standard input.");
			System.out.println("Please input with correct format like in data.csv and press Ctrl+Z as End.");
			System.out.println("");
			System.out.println("");
		}else{
			fis = new FileInputStream(cfg.getInput());
			isr = new InputStreamReader(fis,"UTF-8");
			System.out.println("Read data from file : " + cfg.getInput());
		}

		br = new BufferedReader(isr);
		this.readFileToVector(br, vtr);

		this.rows = vtr;
		br.close();
		if (isr != null){
			isr.close();
		}
		if (fis != null){
			fis.close();
		}
		return true;
	}
	private boolean readFileToVector(BufferedReader br, List vtr) throws Exception {
		try {
			boolean result = false;
			String aLine;
			while ((aLine = br.readLine()) != null) {
				List row = this.parseOneLineByDelimiter(aLine);
				vtr.add(row);
				result = true;
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	private List parseOneLineByDelimiter(String aLine) throws Exception {
		try {
			List row = new Vector();
			String[] strArr = aLine.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);
			for (String strSpilit: strArr){
				row.add(strSpilit);
			}
			return row;
		} catch (Exception e) {
			throw e;
		}
	}
	public List getRows(){
		return this.rows;
	}
}
/*
 * ****************************VSS GENERATED HISTORY***************************
 * $History:  $
 * ****************************************************************************
 */