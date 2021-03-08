package kr.co.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.util.FileUploadDownloadUtils;
import kr.co.util.MediaUtils;

@Controller
public class FileUploadController {

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// test용
	@RequestMapping(value = "/board/testRead2", method = RequestMethod.GET)
	public String testRead() {
		return "/board/testRead2";
	}
	
	
	// 업로드 파일 삭제(서버 파일)
	@ResponseBody	// 데이터
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public boolean deleteFile(String sFileLink, HttpSession session) {
		boolean ok = false;
		// 파일 구분자 변경 
		sFileLink = FileUploadDownloadUtils.changeToFileseparator(sFileLink);
		// 경로 조합 메서드
		String realPath = FileUploadDownloadUtils.getRealPath(uploadPath, session);
		// 경로 + 받은 데이터로 파일 객체 생성
		File sFile = new File(realPath+sFileLink);
		// 파일 삭제
		ok = sFile.delete();
		// 확장자 추출 및 미디어타입확인 메서드 호출
		MediaType mType = MediaUtils.getLinkToMediaType(sFileLink);
		// 썸네일이 삭제 성공이고 이미지 파일이면 원본파일 삭제
		if(mType != null && ok) {
			// 이미지 타입이 있으면 삭제된건 썸네일이므로 원본파일이름을 추출하여 마저 삭제
			String pre= sFileLink.substring(0, 12);
			String suf= sFileLink.substring(14);
			File file = new File(realPath+pre+suf);
			ok = file.delete();
		}
		
		return ok;
	}
	
	
	
	// 원본파일 새창열기, 기타파일 다운로드
	@ResponseBody
	@RequestMapping(value = "/displyFile",method = RequestMethod.GET)
	public ResponseEntity<byte[]> displyFile(String fileLink, HttpSession session) {
		// 파일정보를 배열로 보낸다, 파일 이름을 변경하기위해 헤더스객체가 필요
		ResponseEntity<byte[]> entity = null;
		byte[] arr = null;
		InputStream in = null;
		
		// IO try~catch 구조
		try {
			// 실제 파일이 위치한 절대 경로를 구한다
			String realPath = FileUploadDownloadUtils.getRealPath(uploadPath, session);
			// 파일의 주소로 FileInputStream 객체 생성
			in = new FileInputStream(realPath+fileLink);
			// 확장자 추출
			String Type= MediaUtils.getType(fileLink);
			// 확장자로 미디어타입을 구분
			MediaType mType= MediaUtils.getMediaType(Type);
			// 
			HttpHeaders headers = new HttpHeaders();
			if(mType != null) {
				// 이미지 라면 미디어 타입을 헤더스에 세팅
				headers.setContentType(mType);
			}else {
				// 이미지가 아닌 파일이라면
				// uid 문자열을 제거하고 업로드했던 이름을 추출
				String uploadFileName = fileLink.substring(fileLink.indexOf("_")+1);
				// 추출한 파일이름을 인코딩
				String encodingName = new String(uploadFileName.getBytes("utf-8"), "8859_1");
				// 미디어 타입을 APPLICATION_OCTET_STREAM(다운로드용)으로 세팅
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// 인코딩한 파일이름을 헤더스에 추가
				headers.add("Content-Disposition", "attachment;filename=\""+encodingName+"\"");
			}
			// 배열에 파일의 byte 정보를 스트림객체로 넣는다
			arr = IOUtils.toByteArray(in);
			// 배열과 해더를 같이 담는다
			entity = new ResponseEntity<byte[]>(arr, headers, HttpStatus.OK);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// 실패했을 때.. 필요한가?
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return entity; 
	}
	
	
	// ajax를 이용한 파일 업로드
	@ResponseBody
	@RequestMapping(value = "ajaxtest"
					, method = RequestMethod.POST
					, produces = "application/text;charset=utf-8")	// 반환 String이 깨질때
	public String ajaxtest(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		MultipartFile file = request.getFile("file");
		String realPath = session.getServletContext().getRealPath(uploadPath);
		String uploadFileName= FileUploadDownloadUtils.upload(file, realPath);
		System.out.println(uploadFileName);
		return uploadFileName;
	}
	
	@RequestMapping(value = "ajaxtest", method = RequestMethod.GET)
	public void ajaxtest() {
		
	}
	
	
}
