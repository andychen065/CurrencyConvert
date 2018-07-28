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
* Program Name   : CurrencyConvert.java
* Nature	     : Application
* Description    : Class for convert currency by rate 
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


public class CurrencyConvert {
	public static void main(String[] args) {
		try {
			//System.out.println("Charset:"+ Charset.defaultCharset().displayName());
			//System.out.println(System.getProperty("file.encoding")); 
			
			//System.out.println("currencycovert start");
			Config cfg = new Config();
			if (validateArgs(args, cfg)){
					//support for standard i/o
					/*Importer imp = new Importer(cfg);
					if (imp.doImport()){
						Formatter fmt = new Formatter(imp, cfg);
						fmt.format();
						Exporter exp = new Exporter(cfg ,imp);
						exp.doExport();
					}*/
					//Process in piped
					ImportExportPiped iep= new ImportExportPiped(cfg);
					iep.start();

			}
			//System.out.println("currencycovert end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean validateArgs(String[] args, Config cfg){
		//System.out.println("validateArgs start");
		try{
			int iagrslen = args.length;
			if (iagrslen == 0 || "--help".equalsIgnoreCase(args[0])) {
				System.out.println("usage: CurrencyConvert<--field N> [--multiplier N] [-i input] [-o output]");
				System.out.println("");
				System.out.println("  --field N       Convert CSV field N");
				System.out.println("  --multiplier N  Multiply currency value by N for the current conversion rate");
				System.out.println("  -i input        Read from input file (or stdin)");
				System.out.println("  -o output       Write to output file (or stdout)");
				return false;
			}else{
				if (iagrslen == 1 ){
					System.out.println("Argument --field is mandatory. Please try again.");
					return false;
				}else{
					if (!"--field".equalsIgnoreCase(args[0])){
						System.out.println("Argument --field is mandatory. Please try again.");
						return false;
					}else{
						cfg.setField(Integer.parseInt(args[1]));
					}
				}

				if (iagrslen == 5 ){
					System.out.println("Argument input file is mandatory. Please try again.");
					return false;
				}

				if (iagrslen > 3){
					cfg.setMultiplier(new BigDecimal(args[3]));
				}

				if (iagrslen > 4){
					cfg.setInput(args[4]);
				}

				if (iagrslen > 5){
					cfg.setOutput(args[5]);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("validateArgs end");
		return true;
	}
}

/*
* ****************************VSS GENERATED HISTORY***************************
* $History:  $
* ****************************************************************************
*/