package kr.co.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDownloadUtils {
	

	
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		
		String yearPath = File.separator + year; // /2021
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(month);    ;// /2021/02
		String datePath = monthPath + File.separator+ new DecimalFormat("00").format(date);// /2021/02/22
		
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	
	private static void makeDir(String uploadPath, String ... arr) {
		
		File fDate = new File(uploadPath, arr[arr.length-1]);
		if(fDate.exists()) {
			return;
		}
		
		for(int i = 0; i< arr.length; i++) {
			String path = arr[i];
			
			File f = new File(uploadPath+path);
			if(!f.exists()) {
				f.mkdir();
			}
		}
	}


	public static String makeFileName(String originalFileName) {
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalFileName;
		
		return savedName;
		 
	}

	public static String upload(MultipartFile file, 
							  String uploadPath, String savedName) 
									  				throws Exception {
		
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath+savedPath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		// a.png
		int idx = savedName.lastIndexOf(".")+1;
		String type = savedName.substring(idx);
		
		MediaType mType = MediaUtils.getMediaType(type);
		
		String uploadFileName = null;
		
		if(mType != null) {
			uploadFileName = makeThumbnail(uploadPath, savedPath, savedName);
			
		}else {
			
			uploadFileName= (savedPath+File.separator+savedName)
							.replace(File.separatorChar, '/');
			
		}
		System.out.println(uploadFileName);
		return uploadFileName;
		
		
	}


	private static String makeThumbnail(String uploadPath, 
										String savedPath, 
										String savedName) throws Exception {
		File oriFile = new File(uploadPath+savedPath, savedName);
		
		BufferedImage sourceImg = ImageIO.read(oriFile);
		
		BufferedImage desImg = Scalr.resize(sourceImg, 
											Scalr.Method.AUTOMATIC, 
											Scalr.Mode.FIT_EXACT, 200);
		
		String thumbnailName = uploadPath+savedPath+File.separator+"s_"+savedName;
		int idx = savedName.lastIndexOf(".")+1;
		String type = savedName.substring(idx).toUpperCase();
		
		ImageIO.write(desImg, type, new File(thumbnailName));
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}


	public static String upload(MultipartFile file, 
								String uploadPath) throws Exception {

		String savedName= makeFileName(file.getOriginalFilename());
		
		
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath+savedPath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		// a.png
		int idx = savedName.lastIndexOf(".")+1;
		String type = savedName.substring(idx);
		
		MediaType mType = MediaUtils.getMediaType(type);
		
		String uploadFileName = null;
		
		if(mType != null) {
			uploadFileName = makeThumbnail(uploadPath, savedPath, savedName);
			
		}else {
			
			uploadFileName= (savedPath+File.separator+savedName)
							.replace(File.separatorChar, '/');
			
		}
		return uploadFileName;
		
		
	
		
	}

}
