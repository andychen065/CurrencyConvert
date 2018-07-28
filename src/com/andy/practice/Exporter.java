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
 * Program Name   : Exporter.java
 * Nature	     : Application
 * Description    : Class for export file 
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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Exporter {
	private Config cfg;
	private List lstcRows = null;
	private String CR = new String(new byte[]{10});
	public Exporter(Config cfg, Importer imp) {
		this.cfg = cfg;
		this.lstcRows = imp.getRows();
	}
	public void doExport() {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			if ("stdout".equalsIgnoreCase(cfg.getOutput())){
				System.out.println("Write data to standard output.");
				System.out.println("");
				System.out.println("");
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
				int rowsize = lstcRows.size();
				for (int i = 0; i < rowsize; i ++ ){
					if (i == rowsize - 1 ){
						writeScreen(bw, (List)lstcRows.get(i), false);
					}else{
						writeScreen(bw, (List)lstcRows.get(i), true);
					}
				}
			}else{
				fos = new FileOutputStream(cfg.getOutput());
				//osw = new OutputStreamWriter(fos);
				osw = new OutputStreamWriter(fos, "UTF-8");
				int rowsize = lstcRows.size();
				for (int i = 0; i < rowsize; i ++ ){
					if (i == rowsize - 1 ){
						writeFile(osw, (List)lstcRows.get(i), false);
					}else{
						writeFile(osw, (List)lstcRows.get(i), true);
					}
				}
				System.out.println("Write data to file : " + cfg.getOutput());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeFile(OutputStreamWriter osw,List row, boolean bnewline)	throws Exception {
		try {
			int size = row.size();	// No. of field of rows
			StringBuffer sb = new StringBuffer(1000);
			for (int i = 0; i < size; i ++) {
				Object obj = row.get(i);
				String fieldValue = (String)obj;
				if (i != 0 && i !=size) {
					sb.append(",");
				}
				if (fieldValue.indexOf(",") >= 0 && fieldValue.indexOf("\"") < 0){
					sb.append("\"").append(fieldValue).append("\"");
				}else{
					sb.append(fieldValue);
				}
			}

			if (bnewline){
				sb.append(CR);
			}
			osw.write(sb.toString());
			osw.flush();
			sb.setLength(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void writeScreen(BufferedWriter bw,List row, boolean bnewline)	throws Exception {
		try {
			int size = row.size();	// No. of field of rows
			StringBuffer sb = new StringBuffer(1000);
			for (int i = 0; i < size; i ++) {
				Object obj = row.get(i);
				String fieldValue = (String)obj;
				if (i != 0 && i !=size) {
					sb.append(",");
				}
				if (fieldValue.indexOf(",") >= 0 && fieldValue.indexOf("\"") < 0){
					sb.append("\"").append(fieldValue).append("\"");
				}else{
					sb.append(fieldValue);
				}
			}

			if (bnewline){
				sb.append(CR);
			}
			bw.write(sb.toString());
			bw.flush();
			sb.setLength(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
/*
 * ****************************VSS GENERATED HISTORY***************************
 * $History:  $
 * ****************************************************************************
 */