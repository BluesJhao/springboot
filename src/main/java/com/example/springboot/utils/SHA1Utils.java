/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.example.springboot.utils;

import java.security.MessageDigest;

/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 * @author chenrenfu
 */
public class SHA1Utils {

	/**
	 * 用SHA1算法生成安全签名
	 * @param content 请求的内容
	 * @param saltFigure 票据(盐值)
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 安全签名
	 * @throws Exception 
	 */
	public static String SHA1Sign(String content,String saltFigure, String timestamp, String nonce) throws Exception
			  {
		try {
//			String[] array = new String[] { saltFigure, timestamp, nonce, content };
			StringBuffer sb = new StringBuffer();
			// 字符串排序
//			Arrays.sort(array);
//			for (int i = 0; i < 4; i++) {
//				sb.append(array[i]);
//			}
			sb.append(content).append(saltFigure).append(timestamp).append(nonce);
			String str = sb.toString();
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
