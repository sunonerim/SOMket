package com.sunonerim.util;

import java.io.*;


public class FileUtil {
	
	public FileUtil(){
		
	}
	
	/**
	 * ������ ������ String ���� �����Ѵ�.
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String getContents(File file) throws Exception{

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine())!=null){
			sb.append(str+"\r\n");
		}
		br.close();
		return sb.toString();
	}
	
	/**
	 * ������ ���뿡 line �� ���ڿ��� ���ٿ� �����Ѵ�.
	 * @param file
	 * @param line
	 * @return
	 * @throws Exception
	 */
	public static String getContents(File file, String line) throws Exception{

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine())!=null){
			sb.append(str+line);
		}
		br.close();
		return sb.toString();
	}
	
	/**
	 * file1���� file2�� ī���Ѵ�.
	 * @param file1
	 * @param file2
	 * @throws Exception
	 */
	public static void fileCopy(File file1, File file2) throws Exception{
		FileInputStream fis= new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);
        int readByte = 0;
        while ((readByte = fis.read()) != -1) {
        	fos.write(readByte);
        }
        fis.close();
        fos.close();
		
	}
	
	/**
	 * ���ڿ��� ���Ͼ���
	 * 
	 * @param f		���� path ( ��θ�?+ ���ϸ�)
	 * @param s		������ �����ϰ��ϴ� ���� ���ڿ�
	 * @throws Exception
	 */
	public static void createFileFromString(String f, String s) throws Exception{
		FileUtil.createOutputFile(f);
		
		FileWriter fw = new FileWriter( new File(f) );
		fw.write(s);
		fw.close();
	}
	
	/**
	 * create a file. When file_path exist, it rename exist file 
	 * and create directory and file.
	 * 
	 * @param file_path
	 * @return
	 */
	public static File	createOutputFile(String file_path){
		File	dirFile	= new File(FileUtil.getDir(file_path));
		if( ! dirFile.exists()){
			dirFile.mkdirs();
		}
		
		File	outputFile = new File(file_path);
		if (outputFile.exists()) {
		    System.out.println("========== Already file exist so backup the previous file and create new file....");
			outputFile.renameTo( new File(file_path + ".backup"));
		}
		return outputFile;
	}
	
	public	static 	String	getFilename( String file_path ) {
		if (file_path == null ) return "";
		
		if ( file_path.lastIndexOf( File.separator )==-1){
			return  file_path;
		}
				
		return file_path.substring(file_path.lastIndexOf( File.separator )+1, file_path.length() );
		
	}
	/**
	 * get the directory name from file path string.
	 * 
	 * @param file_path
	 * @return
	 */
	public static	String	getDir(String file_path){
		if (file_path == null ||		file_path.lastIndexOf(File.separator)==-1){
			return "";
		}
		return file_path.substring(0,file_path.lastIndexOf(File.separator)+1);
		
	}
	/**
	 * extract file extension from file name
	 * 
	 * @param file_name
	 * @return
	 * @throws Exception
	 */
	public static String getExtention(String file_name) throws Exception{
		if (file_name == null ||
				file_name.lastIndexOf(".")==-1){
			return "";
		}
		return file_name.substring(file_name.lastIndexOf(".")+1,file_name.length());
	}

	public	static	String	getNextFilePath( String file_name , int next_index){
		int	last_index = file_name.lastIndexOf(".");
		
		file_name = file_name.substring(0, last_index) + next_index + file_name.substring(last_index, file_name.length());  
		return file_name;
	}

	public	static	String	getNextFilePath( String file_name , int next_index, String ext ){
		int	last_index = file_name.lastIndexOf(".");
		
		file_name = file_name.substring(0, last_index) + next_index + "." + ext ;  
		return file_name;
	}

	public	static	String	changeExtention( String file_name , String ext ){
		int	last_index = file_name.lastIndexOf(".");
		
		file_name = file_name.substring(0, last_index) + "." + ext ;  
		return file_name;
	}

	public	static	String	getNewFilePath( String file_name , String suffix, String ext ){
		int	last_index = file_name.lastIndexOf(".");
		
		file_name = file_name.substring(0, last_index) + suffix + "." + ext ;  
		return file_name;
	}

	
	public	static	String	getDirWithYMD(String base_directory){
		String	yyyyMM = DateUtil.today_yyyyMMdd();
		
		StringBuffer  sb = new StringBuffer(base_directory);
		sb.append(File.separator).append(yyyyMM);
		
		return sb.toString();
	}
	
	public	static void main(String[] args) throws Exception{
		// String file_name = "/rim/test/source/apple/dungho.test.gif";
		String file_name = "\\rim\\test\\source\\apple\\dungho.gif";
		System.out.println("File PATH : " + file_name);
		System.out.println("File name : " + FileUtil.getFilename(file_name));
		System.out.println("File EXT : " + FileUtil.getExtention(file_name));
		System.out.println("DIR  : " + FileUtil.getDir(file_name));
		System.out.println("File EXT : " + FileUtil.getNextFilePath(file_name, 2));
		System.out.println("File EXT : " + FileUtil.getNextFilePath(file_name, 1, "png"));
		
		
	}
}
