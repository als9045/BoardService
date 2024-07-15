package org.example.boardservice.Controller;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.DTO.BoardDTO;
import org.example.boardservice.DTO.PageRequestDTO;
import org.example.boardservice.Entity.Board;
import org.example.boardservice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"/","/list"})
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        System.out.println("BoardList-----");
        System.out.println("Type = "+pageRequestDTO.getType());
        System.out.println("Type = "+pageRequestDTO.getKeyword());

        System.out.println("resutl = "+ boardService.getList(pageRequestDTO));
        model.addAttribute("result", boardService.getList(pageRequestDTO));

        return "Board/BoardList";
    }

    @GetMapping({"/searchlist"})
    public String saerchlist(PageRequestDTO pageRequestDTO, Model model) {
        System.out.println("BoardList-----");
        System.out.println("Type = "+pageRequestDTO.getType());
        System.out.println("Type = "+pageRequestDTO.getKeyword());

        System.out.println("searchListController = "+ boardService.searchList(pageRequestDTO));
        model.addAttribute("result", boardService.searchList(pageRequestDTO));

        return "Board/BoardList";
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("register===");

        return "Board/register";
    }

    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes redirectAttributes) {

        System.out.println("register Post===");

        Long bno = boardService.registerBoard(dto);

        System.out.println("bno = "+bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";

    }

    @GetMapping("/read")
    public String read(@ModelAttribute("requestDTP") PageRequestDTO requestDTO
                       , Long bno, Model model) {

        System.out.println("read===");
        System.out.println("bno = "+bno);
        System.out.println("requestDTO = "+requestDTO);

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println("boardDTO = "+boardDTO);

        model.addAttribute("dto", boardDTO);

        return "Board/read";
    }

    @GetMapping("/modify")
    public String modify(Long bno, Model model){
        System.out.println("modify");

        System.out.println("dtd = "+bno);

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println("boardDTO = "+boardDTO);

        model.addAttribute("dto", boardDTO);

        return "Board/modify";
    }
    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("reqeustDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
            System.out.println("modify Post = ");
            System.out.println("dto : "+dto);
            boardService.modify(dto);

            return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){

        System.out.println("remove");
        System.out.println("bno = "+bno);

        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

}
