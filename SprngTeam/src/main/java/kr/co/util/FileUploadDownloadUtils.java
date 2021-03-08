package kr.co.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDownloadUtils {

	// 유일 파일네임 생성
	public static String makeName(String originalFilename) {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+originalFilename;
		return savedName;
	}
	
	// 날짜(년,월,일) 추출
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		String yearPath = File.separator+year;
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(month);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(date);
		
		// 날짜별 폴더 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}

	// 날짜별 폴더 생성
	private static void makeDir(String uploadPath, String ... arr) {
		
		File fDate = new File(uploadPath, arr[2]);
		
		if(fDate.exists()) {
			return;
		}
		
		for (String path : arr) {
			File f = new File(uploadPath+path);
			if(!f.exists()) {
				f.mkdir();
			}
		}
		
	}

	// 파일 업로드 및 데이터 가공
	public static String upload(MultipartFile file, String uploadPath) throws Exception {
		// -----------  파일 업로드
		String savedName = makeName(file.getOriginalFilename());
		String datePath= calcPath(uploadPath);
		File target = new File(uploadPath+datePath, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		
		// ----------- 썸네일 
		// 확장명 추출
		String type = savedName.substring(savedName.lastIndexOf(".")+1);
		// 이미지 구분
		MediaType mType = MediaUtils.getMediaType(type);
		
		// 이미지 구분 및 썸네일 생성
		String uploadFileName ="";
		if(mType != null) {
			uploadFileName = FileUploadDownloadUtils.makeThumnail(uploadPath, datePath, savedName, type);
		}else {
			uploadFileName = datePath+File.separator+savedName;
		}
		// 반환하는 상대 경로의 역슬래시(\)를 슬래시(/)로 변환
		return uploadFileName.replace(File.separatorChar, '/');
		
	}

	// 썸네일 생성
	private static String makeThumnail(String uploadPath, String datePath, String savedName, String type) throws Exception {
		// 원본파일 객체
		File oriFile = new File(uploadPath+datePath, savedName);	//원본파일 객체
		// 썸네일 버퍼이미지 (더블버퍼링 기술)
		BufferedImage copyImg = ImageIO.read(oriFile);		// 1. 원본 이미지를 버퍼에 복사
		BufferedImage reSizingIng = Scalr.resize(copyImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);	// 2. 이미지 리사이즈
		// 썸네일 파일 객체(경로)
		String thumbNailPath =uploadPath+datePath+File.separator+"s_"+savedName;
		File thumbNail = new File(thumbNailPath);
		
		// 썸네일 성성(버퍼, 확장자, 파일객체)
		ImageIO.write(reSizingIng, type, thumbNail);
		
		// view에 보내줄 썸네일 상대 경로
		String thumbNailName = thumbNailPath.substring(uploadPath.length());
		
		return thumbNailName;
	}

	// 파일경로 메서드 반환
	public static String getRealPath(String uploadPath, HttpSession session) {
		
		return session.getServletContext().getRealPath(uploadPath);
	}
	
	// 파일 구분자 변환
	public static String changeToFileseparator(String path) {
		return path.replace('/', File.separatorChar);
	}
	public static String changeTOWebseparator(String path) {
		return path.replace(File.separatorChar, '/');
	}
	
	

}
