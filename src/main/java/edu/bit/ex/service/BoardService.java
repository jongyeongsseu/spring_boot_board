package edu.bit.ex.service;

import java.util.List;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;

public interface BoardService {
	
	// 글 목록
	public List<BoardVO> getList();
	
	// 글 목록 + 페이징 처리
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 글 내용
	public BoardVO read(int bId);
	
	// 글 작성
	public void write(BoardVO boardVO);

	// 글 삭제
	public void delete(int bId);
	
	// 글 수정
	public void modify(BoardVO boardVO);
	
	// 답글 작성
	public void reply(BoardVO boardVO);
	
	// 글 갯수
	public int getTotalCount(Criteria cri);
}
