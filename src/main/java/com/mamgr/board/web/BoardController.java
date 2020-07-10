package com.mamgr.board.web;

import com.mamgr.board.service.BoardService;
import com.mamgr.board.web.dto.request.BoardSaveRequest;
import com.mamgr.board.web.dto.request.BoardUpdateRequest;
import com.mamgr.board.web.dto.response.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService postsService;

    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequest request) {
        return postsService.save(request);
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequest request) {
        return postsService.update(id, request);
    }

    @GetMapping("/api/v1/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
