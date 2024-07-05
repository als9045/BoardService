package org.example.boardservice.Controller;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.DTO.PageRequestDTO;
import org.example.boardservice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping({"/","/list"})
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        System.out.println("Board-----");

        System.out.println("resutl = "+ boardService.getList(pageRequestDTO));
        model.addAttribute("result", boardService.getList(pageRequestDTO));
        return "Board/BoardList";

    }
}
