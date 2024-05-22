package com.eunhop.tmdbmovieapp.controller;

import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.service.UserService;
import com.eunhop.tmdbmovieapp.service.VideoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 회원가입 Controller
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final VideoService videoService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, HttpServletResponse response, Model model) {
        if(userService.alreadySigned(user)) {
            model.addAttribute("msg", "이미 회원가입된 이메일 입니다.");
            return "signup";
        } else {
            userService.registration(user);
            userService.login(response, user);
            // 회원가입 후 자동 로그인
            return "redirect:/";
        }
    }

    @GetMapping("/user/management")
    public String userManagement(Model model, @RequestParam int pageNo, @RequestParam(defaultValue = "id") String sort) {
        model.addAttribute("allReviews", videoService.findAnyReviews(pageNo, sort));
        return "user_management";
    }

    @PostMapping("/user/management/delete")
    @ResponseBody
    public String userReviewDelete(@RequestParam long id) {
        videoService.reviewDelete(id);
        return "success";
    }

    @GetMapping("/user/management/review")
    public String userManagementReview(Model model, @RequestParam long id, @RequestParam int pageNo, @RequestParam(defaultValue = "id") String sort) {
        model.addAttribute("allReviews", videoService.findUserReviews(id, pageNo, sort));
        return "user_management";
    }

    @PostMapping("/user/management/change")
    @ResponseBody
    public String userAuthorityChange(@RequestParam long id) {
        userService.authorityChange(id);
        return "success";
    }

    @PostMapping("/user/management/review/delete/all")
    @ResponseBody
    public String userReviewsDeleteAll(@RequestParam long id) {
        videoService.userReviewsDeleteAll(id);
        return "success";
    }
}
