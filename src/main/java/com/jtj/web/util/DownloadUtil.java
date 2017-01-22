package com.jtj.web.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadUtil {

	//excel下载
	public static void excelDownload(Workbook wb, HttpServletResponse response, String fileName){
    	OutputStream os = null;
    	try {
    		response.reset();
    		response.setContentType("application/vnd.ms-excel;charset=utf-8");
    		response.setHeader("Content-Disposition","attachment;filename="+new String((fileName+".xls").getBytes("GB2312"),"ISO8859-1"));
    		response.setContentType("application/msexcel");// 定义输出类型      
    		os = new BufferedOutputStream(response.getOutputStream());
			wb.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
