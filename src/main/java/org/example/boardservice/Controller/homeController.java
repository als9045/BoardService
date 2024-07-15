package org.example.boardservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/board/list"; // home.jsp 파일을 반환
    }
}
