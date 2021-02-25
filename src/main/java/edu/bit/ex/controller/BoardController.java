package edu.bit.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageDTO;
import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;

//@Slf4j
@AllArgsConstructor
@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**********************
	 *   Object -> JSON
	 *********************/
	
//	@GetMapping("/json")
//	public List<BoardVO> jsonList() {
//		List<BoardVO> jsonList = boardService.getList();
//		
//		return jsonList;
//	}
	
	/**********************
	 *   Page: Thymeleaf
	 *********************/
	
//	// 글 목록
//	@GetMapping("/board")
//	public ModelAndView boardList(ModelAndView mav) throws Exception {
//		mav.addObject("list", boardService.getList());
//		
//		mav.setViewName("thymeleaf/list");
//		
//		return mav;
//	}
	
	/**********************
	 *      Page: JSP     
	 *********************/
	
//	// 글 목록
//	@GetMapping("/board")
//	public ModelAndView boardList(ModelAndView mav) throws Exception {
//		mav.setViewName("list");
//		mav.addObject("list", boardService.getList());
//		
//		return mav;
//	}
	
	// 글 목록 + 페이징 처리
	@GetMapping("/board")
	public ModelAndView pagingBoardList(Criteria cri, ModelAndView mav) throws Exception {
		mav.addObject("list", boardService.getListWithPaging(cri));
		mav.addObject("pageMaker", new PageDTO(cri, boardService.getTotalCount(cri)));
		
		mav.setViewName("page_list");
		
		return mav;
	}
	
	// 글 열람
	@GetMapping("/board/{bId}")
	public ModelAndView ContentView(BoardVO boardVO, ModelAndView mav) throws Exception {
		mav.addObject("content_view", boardService.read(boardVO.getbId()));
		
		mav.setViewName("content_view");
		
		return mav;
	}
	
	// 글 작성창
	@GetMapping("/board/write")
	public ModelAndView writeForm(ModelAndView mav) throws Exception {
		mav.setViewName("write_view");
		
		return mav;
	}
	
	// 답글 작성창
	@GetMapping("/board/reply/{bId}")
	public ModelAndView replyForm(BoardVO boardVO, ModelAndView mav) throws Exception {
		mav.addObject("reply_view", boardService.read(boardVO.getbId()));
		
		mav.setViewName("reply_view");
		
		return mav;
	}
	
	/**********************
	 *        CRUD     
	 *********************/
	
	// 글 작성
	@PostMapping("/board/write")
	public ResponseEntity<String> writePost(@RequestBody BoardVO boardVO, ModelAndView mav) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			boardService.write(boardVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 글 삭제
	@DeleteMapping("/board/{bId}")
	public ResponseEntity<String> deletePost(BoardVO boardVO, ModelAndView mav) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			boardService.delete(boardVO.getbId());
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 글 수정
	@PutMapping("/board/{bId}")
	public ResponseEntity<String> modifyPost(@RequestBody BoardVO boardVO, ModelAndView mav) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			boardService.modify(boardVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 답글 작성
	@PostMapping("/board/reply/{bId}")
	public ResponseEntity<String> writeReply(@RequestBody BoardVO boardVO, ModelAndView mav) throws Exception {
		ResponseEntity<String> entity = null;
		
		try {
			boardService.reply(boardVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}