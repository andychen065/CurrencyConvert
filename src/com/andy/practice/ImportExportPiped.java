package com.andy.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Date;

public class ImportExportPiped {
	private Config cfg;
	public ImportExportPiped(Config cfg) throws Exception {
		this.cfg = cfg;
	}
	
	public void start() throws IOException {
		PipedWriter out = new PipedWriter(); 
		PipedReader in = new PipedReader();
		out.connect(in);
		writerToPipe w = new writerToPipe(out, cfg);
		readFromPipe r = new readFromPipe(in, cfg);
		Thread t1 = new Thread(w);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}

}
class writerToPipe implements Runnable{
    private PipedWriter out = null;
    private Config cfg = null;
    private String CR = new String(new byte[]{10});
    private String COMMA = ",";
    public void run() {
        String aLine = "";
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
        	if ("stdin".equalsIgnoreCase(cfg.getInput())){
        		isr = new InputStreamReader(System.in, "UTF-8");
        		System.out.println("Please input with correct format like in data.csv and type :q and Enter in newline to end input.");
        	}else{
        		isr = new InputStreamReader(new FileInputStream(cfg.getInput()), "UTF-8");
        		System.out.println("Start reading from file, date: " + new Date());
        	}
        	br = new BufferedReader(isr, 10*1024*1024);

        	aLine = br.readLine();
        	while (aLine != null && !":q".equals(aLine)) {
        		String[] strArr = aLine.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);
        		StringBuffer sb = new StringBuffer(1000);
        		int i = 0;
        		for (String strSpilit: strArr){
        			if (i == cfg.getField() - 1){
        				try{
        					String currency = strSpilit.replace("\"", "");//remove quota
        					currency = currency.replace(COMMA, "");//remove comma
        					double dbValue = Double.parseDouble(currency);
        					dbValue = dbValue * cfg.getMultiplier().doubleValue();
        					String formatStr = cfg.getCurrencyFormatTo().format(dbValue);
        					if (formatStr.indexOf(",") >= 0){
        						sb.append("\"").append(formatStr).append("\"");
        					}else{
        						sb.append(formatStr);
        					}
        				}catch(Exception e){
        					//if parse double value failed, maybe it is header or invalid value of the currency
        					sb.append(strSpilit);
        				}
        			}else{
        				sb.append(strSpilit);
        			}
        			if (i != strArr.length - 1){
        				sb.append(COMMA);
        			}
        			i++;
        		}
        		out.write(sb.toString());
        		aLine = br.readLine();
        		if (aLine != null && !":q".equals(aLine)){//print CR if the next line is not null
        			out.write(CR);
        		}
        	}
        	out.close();
        	br.close();
        	isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    writerToPipe(PipedWriter out, Config cfg){
        this.out = out;
        this.cfg = cfg;
    }
}
class readFromPipe implements Runnable{
    private PipedReader in = null;
    private Config cfg = null;
    public void run() { 
    	
    	
    	try {
    		int receive = 0;
    		if ("stdout".equalsIgnoreCase(cfg.getOutput())){
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
				while ((receive = in.read()) != -1) {
					bw.write(receive);
					bw.flush();
    			}
				bw.close();
    		}else{
    			FileOutputStream fos = new FileOutputStream(cfg.getOutput());
    			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
    			while ((receive = in.read()) != -1) {
    				osw.write(receive);
    				osw.flush();
    			}
    			System.out.println("Stop Writing to file, date: " + new Date());
    			osw.close();
    			fos.close();
    		}
    		in.close();
    	}catch (IOException e) {    
            e.printStackTrace();
        }
    }
    readFromPipe(PipedReader in, Config cfg){
        this.in = in;
        this.cfg = cfg;
    }   
}