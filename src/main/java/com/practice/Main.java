package com.practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.practice.config.Config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan(basePackages = "com.example")
public class Main {
	
	@Autowired
	private Config config;
	
	//Table欄位型態
	private static final String VARCHAR2 = "VARCHAR2";
	private static final String NVARCHAR2 = "NVARCHAR2";
	private static final String NUMBER = "NUMBER";
	private static final String TIMESTAMP = "TIMESTAMP";
	
	//將Table欄位轉為Bean
	private void transColumnToBean() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPwd());
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT COLUMN_NAME, DATA_TYPE ");
			sql.append(" FROM USER_TAB_COLUMNS ");
			sql.append(" WHERE TABLE_NAME = ? ");
			
			int p = 1;
			pstmt = con.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(p++, config.getTableName());
			
			rs = pstmt.executeQuery();
			
			//Java檔建立
			String fileName = prefixUpper(config.getTableName()) + ".java";
			String filePath = getFilePath() + fileName;
			FileWriter writer = new FileWriter(filePath);
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			
			//寫入Java程式
			bufferWriter.write("package " + config.getPackageName() + ";");
			bufferWriter.write("\r\n");
			bufferWriter.write("\r\n");
			
			bufferWriter.write("import java.math.BigDecimal;");
			bufferWriter.write("\r\n");
			bufferWriter.write("import java.time.LocalDateTime;");
			bufferWriter.write("\r\n");
			bufferWriter.write("\r\n");
			
			bufferWriter.write("public class " + prefixUpper(config.getTableName()) + " {");
			bufferWriter.write("\r\n\r\n");
			
			int count = 0;
			String dataType = "";
			String columnName = "";
			
			//寫入變數
			while(rs.next()) {
				
				if(!"".equals(rs.getString("DATA_TYPE"))) {
					dataType = rs.getString("DATA_TYPE");
				}
				
				if(!"".equals(rs.getString("COLUMN_NAME"))) {
					columnName = rs.getString("COLUMN_NAME");
				}
				
				if(dataType.contains(VARCHAR2)){
					bufferWriter.write(createVariable("String", columnName));
				}
				
				if(dataType.contains(NUMBER)) {
					bufferWriter.write(createVariable("BigDecimal", columnName));
				}
				
				if(dataType.contains(TIMESTAMP)) {
					bufferWriter.write(createVariable("LocalDateTime", columnName));
				}
				
				count++;
				
				log.info(columnName + ": " + dataType);
			}
			log.info("總共" + count + "欄");
			
			rs.beforeFirst();
			
			//寫入Get、Set method
			while(rs.next()) {
				
				if(!"".equals(rs.getString("DATA_TYPE"))) {
					dataType = rs.getString("DATA_TYPE");
				}
				
				if(!"".equals(rs.getString("COLUMN_NAME"))) {
					columnName = rs.getString("COLUMN_NAME");
				}
				
				if(dataType.contains(VARCHAR2)){
					bufferWriter.write(createGetSet("String", columnName));
				}
				
				if(dataType.contains(NUMBER)) {
					bufferWriter.write(createGetSet("BigDecimal", columnName));
				}
				
				if(dataType.contains(TIMESTAMP)) {
					bufferWriter.write(createGetSet("LocalDateTime", columnName));
				}
				
			}
			
			bufferWriter.write("}");
			
			bufferWriter.close();
			writer.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	//字串轉駝峰
	private String changeCamelCase(String str) {
		StringBuffer camelStr = new StringBuffer();
		if(str == null || str.isEmpty()) {
			return "";
		}else if(!str.contains("_")){
			return str.toLowerCase();
		}
		String[] words = str.split("_");
		for(String word : words) {
			if(word.isEmpty()) {
				continue;
			}
			if (camelStr.length() == 0) {
				camelStr.append(word.toLowerCase());
            } else {
            	camelStr.append(word.substring(0, 1).toUpperCase());
            	camelStr.append(word.substring(1).toLowerCase());
            }
		}
		return camelStr.toString();
	}
	
	//字串轉駝峰
	private String changeGetCamelCase(String str) {
		StringBuffer camelStr = new StringBuffer();
		if(str == null || str.isEmpty()) {
			return "";
		}else if(!str.contains("_")){
			return prefixUpper(str);
		}
		String[] words = str.split("_");
		for(String word : words) {
			if(word.isEmpty()) {
				continue;
			}
			if (camelStr.length() == 0) {
				camelStr.append(prefixUpper(word));
            } else {
            	camelStr.append(word.substring(0, 1).toUpperCase());
            	camelStr.append(word.substring(1).toLowerCase());
            }
		}
		return camelStr.toString();
	}
	
	//字串字首轉大寫
	private String 	prefixUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	//製造變數
	private String createVariable(String dataType, String columnName) {
		StringBuffer str = new StringBuffer();
		
		str.append("\t");
		str.append("private " + dataType + " " + changeCamelCase(columnName) + ";");
		str.append("\r\n\r\n");
		
		return str.toString();
	}
	
	//製造Get、Set method
	private String createGetSet(String dataType, String columnName) {
		StringBuffer str = new StringBuffer();
		
		//Get method
		str.append("\t");
		str.append("public " + dataType +" get" + changeGetCamelCase(columnName) + "() {");
		str.append("\r\n");
		str.append("\t\t");
		str.append("return " + changeCamelCase(columnName) + ";");
		str.append("\r\n");
		str.append("\t");
		str.append("}");
		str.append("\r\n\r\n");
		
		//Set method
		str.append("\t");
		str.append("public void set" + changeGetCamelCase(columnName) + "(" + dataType + " " + changeCamelCase(columnName) + ") {");
		str.append("\r\n");
		str.append("\t\t");
		str.append("this." + changeCamelCase(columnName) + " = " + changeCamelCase(columnName) + ";");
		str.append("\r\n");
		str.append("\t");
		str.append("}");
		str.append("\r\n\r\n");
		
		return str.toString();
	}
	
	//寫入檔案位置
	private String getFilePath() {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		return path + "../../src/main/java/com/example/bean/";
	}
	
	//Main
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
		Main main = ctx.getBean(Main.class);
		main.transColumnToBean();
		ctx.close();
	}
	
}
