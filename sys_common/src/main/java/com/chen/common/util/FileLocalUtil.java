package com.chen.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

/**
 * 本地文件工具
 */
public class FileLocalUtil {

	/** 支持的图片文件类型 */
	public static final List<String> IMAGE_SUFFIXES = Arrays.asList("image/png", "image/jpeg", "image/bmp", "image/jpg");

	/** 支持的视频文件类型 */
	public static final List<String> VIDEO_SUFFIXES = Arrays.asList("video/mp4");

	/**
	 * 根据文件的名称判断文件的Mine值
	 * @param fileName 文件名
	 * @return MineType
	 */
	public static String getMediaType(String fileName) {
		FileNameMap map = URLConnection.getFileNameMap();
		String contentTypeFor = map.getContentTypeFor(fileName);
		if (contentTypeFor == null) {
			contentTypeFor = "application/octet-stream";
		}
		return contentTypeFor;
	}

	/**
	 * 将文件保存到本地
	 * @param inputStream 文件流
	 * @param fileName 文件名称
	 * @param fileFold 保存的文件夹
	 * @return 文件的相对路径
	 */
	public static String saveFile(InputStream inputStream, String fileName, String fileFold){
		if(inputStream == null || StringUtils.isBlank(fileName) || StringUtils.isBlank(fileFold)){
			return null;
		}

		//生成文件路径
		String saveFilePath = fileFold + "/" + DateUtil.timestampToStr(DateUtil.getCurTime(), "yyyyMMdd")
				+ "_" + NoUtil.createInvitationCode(16) + "_" + fileName;

		File file = new File(saveFilePath);
		if (!file.getParentFile().exists()) {
			if(!file.getParentFile().mkdirs()){
				return null;
			}
		}

		try{
			BufferedInputStream in = new BufferedInputStream(inputStream);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			int len;
			byte[] b = new byte[1024];
			while((len = in.read(b)) != -1){
				out.write(b,0, len);
			}
			in.close();
			out.close();
			return saveFilePath;
		}catch (Exception ex){
			LogUtil.printException(ex);
			return null;
		}
	}

	/**
	 * 获取文件的相对路径
	 * @param fileUrl 文件地址全路径
	 * @param urlRootPath 文件地址根路径
	 * @return 去除根路径后的文件相对路径
	 */
	public static String getRelativeUrl(String fileUrl, String urlRootPath) {
		if(StringUtils.isBlank(fileUrl) || StringUtils.isBlank(urlRootPath)){
			return fileUrl;
		}

		if(fileUrl.indexOf(urlRootPath) == 0){
			return fileUrl.substring(urlRootPath.length());
		}else{
			return fileUrl;
		}
	}

}
