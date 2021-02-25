package edu.bit.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.page.Criteria;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<BoardVO> getList() {
		
		return boardMapper.getList();
	}

	@Override
	public BoardVO read(int bId) {
		boardMapper.upHit(bId);
		
		return boardMapper.read(bId);
	}

	@Override
	public void write(BoardVO boardVO) {
		boardMapper.write(boardVO);
		boardMapper.writeSort(boardVO);
	}

	@Override
	public void delete(int bId) {
		boardMapper.delete(bId);
	}

	@Override
	public void modify(BoardVO boardVO) {
		boardMapper.modify(boardVO);
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public void reply(BoardVO boardVO) {
		boardMapper.replySort(boardVO);
		boardMapper.reply(boardVO);
	}
}
