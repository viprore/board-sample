package com.mamgr.board.service;

import com.mamgr.board.domain.board.Board;
import com.mamgr.board.domain.board.BoardRepository;
import com.mamgr.board.web.dto.request.BoardSaveRequest;
import com.mamgr.board.web.dto.request.BoardUpdateRequest;
import com.mamgr.board.web.dto.response.BoardListResponseDto;
import com.mamgr.board.web.dto.response.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequest request) {
        return boardRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.update(request.getTitle(), request.getContent());

        return id;
    }

    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new BoardResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
    }
}
