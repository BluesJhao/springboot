package com.example.springboot.utils;

import java.util.Random;

public class StringUtil {
	/**
     * 交易流水号末尾的随机数位数
     */
	public static final int SERVER_SN_END_LENGTH = 5;
	/**
	 * 生成指定长度的随机数
	 * @param length
	 * @return
	 */
	public static long getRandom(int length){
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<length;i++){
		    str.append(random.nextInt(10));
		}
		//将字符串转换为数字并输出
		long num=Long.parseLong(str.toString());
		return num;
	}
	
	/**
	 * 获取请求流水号
	 * @return
	 */
	public static String getServiceSn(String channel){
		if (channel == null)
			channel = "";
		return channel + DateUtil.formatDateTime2() + getRandom(SERVER_SN_END_LENGTH);
	}
}
