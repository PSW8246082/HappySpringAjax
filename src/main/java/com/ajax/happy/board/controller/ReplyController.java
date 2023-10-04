package com.ajax.happy.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ajax.happy.board.domain.Reply;
import com.ajax.happy.board.service.ReplyService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService rService;
	
	@ResponseBody
	@RequestMapping(value="/add.kh", method=RequestMethod.POST)
	public String insertReply(ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {
		
		String replyWriter = "khuser01";
		int result = 0;
		if(replyWriter != null && !replyWriter.equals("")) {
			reply.setReplyWriter(replyWriter);
			result = rService.insertReply(reply);
		}
		if(result>0) {
			return "success";
		} else {
			return "fail";
		}
//		try {
//			String replyWriter = (String)session.getAttribute("memberId");
//			if(replyWriter != null && !replyWriter.equals("")) {
//				reply.setReplyWriter(replyWriter);
//				int result = rService.insertReply(reply);
//				url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
//				if(result > 0) {
//					mv.setViewName("redirect:"+url);
//				}else {
//					mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
//					mv.addObject("error", "댓글 등록 실패");
//					mv.addObject("url", url);
//					mv.setViewName("common/errorPage");
//				}
//			}else {
//				mv.addObject("msg", "로그인이 되지 않았습니다.");
//				mv.addObject("error", "로그인 정보확인 실패");
//				mv.addObject("url", "/index.jsp");
//				mv.setViewName("common/errorPage");
//			}
//			
//		} catch (Exception e) {
//			mv.addObject("msg", "관리자에게 문의바랍니다.");
//			mv.addObject("error", e.getMessage());
//			mv.addObject("url", url);
//			mv.setViewName("common/errorPage");
//		}
//		
//		return mv;
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/update.kh", method=RequestMethod.POST)
	public String updateReply(ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {
		
		String replyWriter = "khuser02";
		int result = 0;
		
		if(replyWriter != null && !replyWriter.equals("")) {
			reply.setReplyWriter(replyWriter);
		    result = rService.updateReply(reply);
		} 
		if(result > 0) {
			return "success";
		} 
		else {
			return "fail";
		}
	
	}
//		try {
//			String replyWriter = (String)session.getAttribute("memberId");
//			if(replyWriter != null && !replyWriter.equals("")) {
//				reply.setReplyWriter(replyWriter);
//				url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
//				int result = rService.updateReply(reply);
//				mv.setViewName("redirect:"+url);
//			}else {
//				mv.addObject("msg", "로그인이 되지 않았습니다.");
//				mv.addObject("error", "로그인 정보확인 실패");
//				mv.addObject("url", "/index.jsp");
//				mv.setViewName("common/errorPage");
//			}
//		} catch (Exception e) {
//			mv.addObject("msg", "관리자에게 문의바랍니다.");
//			mv.addObject("error", e.getMessage());
//			mv.addObject("url", url);
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//	}
	
	@ResponseBody
	@RequestMapping(value="/delete.kh", method=RequestMethod.POST)
	public String deleteReply(ModelAndView mv
			, @ModelAttribute Reply reply
			, HttpSession session) {

				String memberId = "khuser01";
				String replyWriter = "khuser01";

				reply.setReplyWriter(replyWriter);
				int result = 0;
				
				if(replyWriter != null && replyWriter.equals(memberId)) {
				result = rService.deleteReply(reply);
				}
				
				if(result > 0) {
						return "success";
					} 
				else {
						return "fail";
				}
		}
	
//		// DELETE FROM REPLY_TBL WHERE REPLY_NO = 샵{replyNo } AND R_STATUS = 'Y'
//		// UPDATE REPLY_TBL SET R_STATUS = 'N' WHERE REPLY_NO = 샵{replyNo }
//		String url = "";
//		try {
//			String memberId = (String)session.getAttribute("memberId");
//			String replyWriter = reply.getReplyWriter();
//			url = "/board/detail.kh?boardNo="+reply.getRefBoardNo();
//			if(replyWriter != null && replyWriter.equals(memberId)) {
////				Reply reply = new Reply();
////				reply.setReplyNo(replyNo);
////				reply.setReplyWriter(replyWriter);
//				int result = rService.deleteReply(reply);
//				if(result > 0) {
//					// 성공
//					mv.setViewName("redirect:"+url);
//				}else {
//					// 실패
//					mv.addObject("msg", "댓글 삭제가 완료되지 않았습니다.");
//					mv.addObject("error", "댓글 삭제 실패");
//					mv.addObject("url", url);
//					mv.setViewName("common/errorPage");
//				}
//			}else {
//				mv.addObject("msg", "자신의 댓글만 삭제할 수 있습니다.");
//				mv.addObject("error", "댓글 삭제 불가");
//				mv.addObject("url", url);
//				mv.setViewName("common/errorPage");
//			}
//			
//		} catch (Exception e) {
//			mv.addObject("msg", "관리자에게 문의바랍니다.");
//			mv.addObject("error", e.getMessage());
////			mv.addObject("url", "/board/detail.kh?boardNo="); // 나중에 이걸로 수정할거임
//			mv.addObject("url", url);
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//	}
	
	@ResponseBody
	@RequestMapping(value = "/list.kh"
			, produces = "application/json; charset=utf-8"
			, method = RequestMethod.GET)
	public String showReplyList() {
		List<Reply> rList = rService.selectReplyList(412);
		
		//List 데이터를 json형태로 만드는 방법
		//1.JSOBJECT, JSONArray
		//2.Gson
		//3.HashMap
		
		Gson gson = new Gson();
		return gson.toJson(rList);
	}
	
			
}


































