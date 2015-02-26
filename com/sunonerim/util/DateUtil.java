package com.sunonerim.util;

import java.text.*;
import java.util.*;



public class DateUtil {

    /**
     * 오늘일자를 "2003년 12월 17일" 과 같은 형식으로 포맷.
     * @return
     */
    public static String simpleFormat() {
        String pattern = "yyyy년 MM월 dd일";
        return format(pattern);
    }
    
   
    /**
     * 오늘 일자를 원하는 패턴으로 포맷한다.
     * @param pattern
     * @return
     */
    public static String format(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,
            java.util.Locale.KOREA);
        return sdf.format(new java.util.Date());
    }

    /**
     * 오늘 일짜에 원하는 날만큼 가감하여 주어진 패턴으로 포맷한다.
     * @param pattern
     * @param field
     * @param amount
     * @return
     */
    public static String format(String pattern, int field, int amount){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,
            java.util.Locale.KOREA);
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(field, amount);
        return sdf.format(gc.getTime());
    }

    /**
     * 날짜 형식의 문자열의 패턴을 바꾼다.
     * @param source
     * @param sourcePattern
     * @param targetPattern
     * @return
     */
    public static String changeFormat(String source, String sourcePattern,
                                      String targetPattern) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(sourcePattern,
            java.util.Locale.KOREA);
        SimpleDateFormat sdf2 = new SimpleDateFormat(targetPattern,
            java.util.Locale.KOREA);

        String result = "";

        try {
            java.util.Date date = sdf1.parse(source);
            result = sdf2.format(date);
        }
        catch (Exception e) {
//            logger.error(e);
        }
        return result;
    }
    
    /**
     * yyyyMMdd형식의 날짜문자열을 targetPattern 포맷으로 변경
     * @param source
     * @param targetPattern
     * @return
     */
    public static String changeFormat(String source, String targetPattern){
    	String result = "";
    	if (source!=null && !source.equals("00000000")){
    		result = changeFormat(source, "yyyyMMdd", targetPattern);
    	}
    	return result;
    }
    
    /**
     * 주어진 패턴 형식의 날짜 문자열을 파싱하여 Date객체를 얻는다.
     * @param source
     * @param pattern
     * @return
     */
    public static java.util.Date parse(String source, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,
            java.util.Locale.KOREA);

        java.util.Date result = null;

        try {
            result = sdf.parse(source);
        }
        catch (Exception e) {
//            logger.error(e);
        }
        return result;
    }

    /**
     * 원하는 포맷으로 date객체를 format한다.
     * @param pattern
     * @param date
     * @return
     */
    public static String format(String pattern, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,java.util.Locale.KOREA);
        String result = "";
        try{
            result = sdf.format(date);
        }catch(Exception e){
//            logger.error(e);
        }
        return result;
    }
    
    /**
     * 주이전 날짜를 가감하여 원하는 포맷을로 날짜를 출력한다.
     * @param source input 입력날짜
     * @param sourcePattern 입력날짜 포맷
     * @param targetPattern 출력날짜 포맷
     * @param field 가감할 날짜필드
     * @param amount 가감할 숫자
     * @return
     */
    public static String format(String source, String sourcePattern, String targetPattern, int field, int amount){
    	SimpleDateFormat ssdf = new SimpleDateFormat(sourcePattern, java.util.Locale.KOREA);
    	SimpleDateFormat tsdf = new SimpleDateFormat(targetPattern, java.util.Locale.KOREA);
    	String result = "";
    	
    	try{
    		java.util.Date date = ssdf.parse(source);
    		GregorianCalendar gc = new GregorianCalendar();
    		gc.setTime(date);
            gc.add(field, amount);
            result = tsdf.format(gc.getTime());
    	}catch(Exception e){
    		result = source;
    	}
    	return result;
    }
    
    /**
     * 현재 시점의 java.sql.Date객체를 구한다.
     * @return Date
     */
    public static java.sql.Date getSqlDate(){
        GregorianCalendar gc = new GregorianCalendar();
        long d = gc.getTimeInMillis();
        return new java.sql.Date(d);
    }

    /**
     * 현재 시점의 java.sql.Timestamp 객체를 구한다.
     * @return Timestamp
     */
    public static java.sql.Timestamp getSqlTimestamp(){
        GregorianCalendar gc = new GregorianCalendar();
        long d = gc.getTimeInMillis();
        return new java.sql.Timestamp(d);
    }
    
    public static  String today_yyyyMMddHHmmss(){
        Date curDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", new Locale("ko","KOREA"));
        return formatter.format(curDate);
    }

    public static  String today_yyyyMMdd(){
        Date curDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("ko","KOREA"));
        return formatter.format(curDate);
    }
    
    public static  String today_yyyyMM(){
        Date curDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", new Locale("ko","KOREA"));
        return formatter.format(curDate) + "0101";
    }
    
    public static  String firstDateOfYear(){
        Date curDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", new Locale("ko","KOREA"));
        return formatter.format(curDate) + "0101";
    }
    
    public static String lastDateOfYear() {
        Date curDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", new Locale("ko","KOREA"));
        return formatter.format(curDate) + "1231"; 
    }
    
	/**
	 * <p>날짜 기간수 구하기</p>
	 * @param f_s_date 2009-11-18 format
	 * @param f_e_date 2009-11-18 format
	 */
	public static int getDiffDateNums (String  f_s_date ,String f_e_date)
	{
		int rtn_val=0;
		if (f_s_date==null) f_s_date="";
		if (f_e_date==null) f_e_date="";
		if (f_s_date.equals("")||f_e_date.equals(""))return 0;
		if (f_s_date.length()==10&&f_e_date.length()==10){
			String[] f_s_date_list=f_s_date.split("-");
			String[] f_e_date_list=f_e_date.split("-");
			String f_s_year=f_s_date_list[0];
			String f_s_month=f_s_date_list[1];
			String f_s_day=f_s_date_list[2];
			String f_e_year=f_e_date_list[0];
			String f_e_month=f_e_date_list[1];
			String f_e_day=f_e_date_list[2];	
			
			rtn_val=getDiffDateNums(f_s_year,f_s_month,f_s_day,f_e_year,f_e_month,f_e_day);
		}
		else return 0;//자리수 안맞아서
		return rtn_val;
	}

	/**
	 * <p>날짜  8자리 10자리로 변경 -20091118=>2009-11-18</p>
	 * @param f_date 20091118 format 
	 */
	public static String  FormatChangeLongDate (String  f_date)
	{  
		if (f_date==null) return "";
		if (f_date.equals("")) return "";
		return f_date.substring(0,4) + "-" + f_date.substring(4,6)  + "-" + f_date.substring(6); 
	}	
	
	/**
	 * <p>날짜 기간수 구하기</p>
	 * @param f_s_date 2009-11-18 format
	 * @param f_e_date 2009-11-18 format
	 */
	public static int getDiffDateNums2 (String  f_s_date ,String f_e_date)
	{ 
		String f_set_s_date=f_s_date.substring(0,4) + "-" + f_s_date.substring(4,6)  + "-" + f_s_date.substring(6);
		String f_set_e_date=f_e_date.substring(0,4) + "-" + f_e_date.substring(4,6)  + "-" + f_e_date.substring(6);
		return getDiffDateNums(f_set_s_date,f_set_e_date);
	}	
	
	/**
	 * <p>날짜 기간수 구하기</p>
	 */
	public static int getDiffDateNums (String f_s_year, String f_s_month, String f_s_day ,String f_e_year, String f_e_month, String f_e_day)
	{
		return getDiffDateNums(Integer.parseInt(f_s_year),Integer.parseInt(f_s_month), Integer.parseInt(f_s_day) , Integer.parseInt(f_e_year),Integer.parseInt(f_e_month),Integer.parseInt(f_e_day));
	}
	/**
	 * <p>날짜 기간수 구하기</p>
	 */
	public static int getDiffDateNums (int f_s_year, int f_s_month, int f_s_day ,int f_e_year, int f_e_month, int f_e_day)
	{
		Calendar cal = Calendar.getInstance ( );
		int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0;
	
		if ( f_e_year > f_s_year )
		{
			for ( int i = f_s_year; i < f_e_year; i++ ) 
			{
			cal.set ( i, 12, 0 );
			nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
			}
			nTotalDate1 += nDiffOfYear;
		}
		else if ( f_e_year < f_s_year )
		{
			for ( int i = f_e_year; i < f_s_year; i++ )
			{
				cal.set ( i, 12, 0 );
				nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
			}
			nTotalDate2 += nDiffOfYear;
		} 
		
		cal.set ( f_e_year, f_e_month-1, f_e_day );
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
		nTotalDate1 += nDiffOfDay;
	
		cal.set ( f_s_year, f_s_month-1, f_s_day );
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR );
		nTotalDate2 += nDiffOfDay;
	
		return nTotalDate1-nTotalDate2;
	}    
	
	
	public	static int	diffSecondFromNow( String date14 ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long	time = sdf.parse(date14).getTime();
		long	diff = System.currentTimeMillis() - time;
		
		return (int) (diff/1000); 
	}
	

	public	static int	diffMinuteFromNow( String date14 ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		long	time = sdf.parse(date14).getTime();
		long	diff = System.currentTimeMillis() - time;
		
		return (int) (diff/1000/60); 
	}

	public	static String	todayFotmatYYYYMMDDhhmmssFromTimestamp( long timestamp ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(  timestamp );
	}
	
	static public void main(String args[]){
		
		Calendar	curDate =Calendar.getInstance();
		
		System.out.println(curDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd  HH:mmss", new Locale("ko","KOREA"));
		
		System.out.println( formatter.format(curDate.getTime() ));
		
		curDate.add(Calendar.MINUTE, 30); 
	
		System.out.println(  " Cur :" + todayFotmatYYYYMMDDhhmmssFromTimestamp( System.currentTimeMillis()) );

		
	}
}