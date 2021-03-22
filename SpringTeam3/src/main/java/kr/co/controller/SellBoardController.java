package kr.co.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.MemberVO;
import kr.co.domain.SellBoardVO;
import kr.co.service.SellBoardService;
import kr.co.util.FileUploadDownloadUtils;
import kr.co.util.MediaUtils;

@Controller
@RequestMapping(value = "/sellboard")
public class SellBoardController {

	@Inject
	private SellBoardService sellboardService; 
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(HttpSession session) {
		MemberVO memberVO =(MemberVO) session.getAttribute("login");
		
		if (memberVO.getmType() == 1004) {
			return "/sellboard/insert";
		}else {
			return "/member/login";
		}
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(SellBoardVO vo) {
		
		sellboardService.insert(vo);
		
		return "redirect:/sellboard/read/"+vo.getBnum();
	}
	
	@RequestMapping(value = "/update/{bnum}", method = RequestMethod.GET)
	public String update(@PathVariable("bnum") int bnum, Model model,HttpSession session) {
		MemberVO memberVO =(MemberVO) session.getAttribute("login");
		
		if (memberVO.getmType() == 1004) {
			SellBoardVO vo = sellboardService.updateUI(bnum);
			model.addAttribute("vo", vo);
			
			return "/sellboard/update";
		}else {
			return "/member/login";
		}
		

	}
	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SellBoardVO vo) {
		
		sellboardService.update(vo);		
		
		return "redirect:/sellboard/read/"+vo.getBnum();
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public String delete(int bnum) {
		
		sellboardService.delete(bnum);
		
		return "redirect:/sellboard/list";
	}
	
	
	
	@RequestMapping(value = "/read/{bnum}", method = RequestMethod.GET)
	public String read(@PathVariable("bnum")int bnum, Model model) {
		SellBoardVO vo = sellboardService.read(bnum);
		model.addAttribute("vo", vo);
		
		
		return "sellboard/read";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		List<SellBoardVO> list = sellboardService.list();
		model.addAttribute("list", list);
		
		return "sellboard/list";
	}
	


	
	@ResponseBody
	@RequestMapping(value = "/upload", 
					method = RequestMethod.POST,
					produces = "application/text;charset=utf8")
	public String upload(MultipartHttpServletRequest request,
			HttpSession session) throws Exception {
		String uploadPath = session.getServletContext()
									.getRealPath(this.uploadPath);
		
		MultipartFile file = request.getFile("file");
		
		String fileUploadPath = FileUploadDownloadUtils
								.upload(file, uploadPath);
		
		
		return fileUploadPath;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public void upload() {
		
	}
	
	
	 @ResponseBody
	 @RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	 public ResponseEntity<byte[]> displayFile(String filename, HttpSession session){
	      
	      ResponseEntity<byte[]> entity = null;
	      byte[] arr = null;
	      InputStream in = null;
	      
	      try {
	         int idx = filename.lastIndexOf(".")+1;
	         String type = filename.substring(idx);
	         
	         MediaType mType = MediaUtils.getMediaType(type);
	         
	         HttpHeaders headers = new HttpHeaders();
	         
	         String uploadPath = session.getServletContext()
	                              .getRealPath(this.uploadPath);
	         in = new FileInputStream(uploadPath+filename);
	         
	         
	         if (mType != null) {
	            headers.setContentType(mType);
	         } else {
	            String fileName = filename.substring(filename.indexOf("_")+1);
	            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	            headers.add("Content-Disposition", "attachment;filename=\""+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") +"\"");
	         }
	         
	         arr = IOUtils.toByteArray(in);
	         
	         entity = new ResponseEntity<byte[]>(arr, headers, HttpStatus.OK);
	      } catch (Exception e) {
	         e.printStackTrace();
	         entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
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
	
	@ResponseBody
	@RequestMapping(value = "/getAttaches/{bnum}")
	public List<String> getAttaches(@PathVariable("bnum")int bnum) {
		return sellboardService.getAttaches(bnum);
	}
	
	
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public void uploadForm(MultipartHttpServletRequest request,
			HttpSession session) throws Exception {
		
		MultipartFile file = request.getFile("file");
		
		String uploadPath = session.getServletContext().getRealPath(this.uploadPath);
		
		String savedName = FileUploadDownloadUtils.makeFileName(file.getOriginalFilename());
		
		FileUploadDownloadUtils.upload(file ,uploadPath, savedName);
		
	
	}
	
	
	   @ResponseBody
	   @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	   public String deleteFile(String fileName, HttpSession session) {
		   String result = null;
		   
		  fileName =  fileName.replace('/', File.separatorChar);
		  
		String uploadPath = session.getServletContext().getRealPath(this.uploadPath);
		
		int idx = fileName.lastIndexOf(".")+1;
		String type = fileName.substring(idx);
		MediaType mType = MediaUtils.getMediaType(type);
		
		boolean exe0 =true;
		
		if (mType != null) {
			String pre = fileName.substring(0,12);
			String suf = fileName.substring(14);
			
			String oriName = pre + suf;
			File f2 = new File(uploadPath+oriName);
			exe0 = f2.delete();
		}
		  
		  File f = new File(uploadPath+fileName);
		  boolean exe1 = f.delete();
		  
		  if (exe1 && exe0) {
			result = "o";
		}else {
			result = "x";
		}
		   
		  return result;
	}
	
}
