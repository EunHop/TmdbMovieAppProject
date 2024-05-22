package com.eunhop.tmdbmovieapp.controller;

import com.eunhop.tmdbmovieapp.dto.security.PrincipalUser;
import com.eunhop.tmdbmovieapp.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String notice(Model model, @RequestParam int pageNo, @RequestParam(defaultValue = "id") String sort) {
        model.addAttribute("noticeList", noticeService.findEnabledAll(pageNo, sort));
        model.addAttribute("enabled", true);
        return "notice";
    }

    @GetMapping("/notice/search")
    public String noticeSearch(
        Model model, @RequestParam int pageNo,
        @RequestParam(defaultValue = "id") String sort, @RequestParam String query, @RequestParam boolean enabled
    ) {
        model.addAttribute("noticeList", noticeService.findQueryEnabledAll(pageNo, sort, query, enabled));
        model.addAttribute("enabled", enabled);
        return "notice";
    }

    @GetMapping("/notice/disabled")
    public String noticeDisabled(Model model, @RequestParam int pageNo, @RequestParam(defaultValue = "id") String sort) {
        model.addAttribute("noticeList", noticeService.findDisabledAll(pageNo, sort));
        model.addAttribute("enabled", false);
        return "notice";
    }

    @PostMapping("/notice/modify")
    @ResponseBody
    public String noticeContentModify(@AuthenticationPrincipal PrincipalUser principalUser, @RequestParam String content, @RequestParam long id) {
        noticeService.modifyContent(principalUser, content, id);
        return "success";
    }

    @PostMapping("/notice/delete")
    @ResponseBody
    public String noticeDelete(@AuthenticationPrincipal PrincipalUser principalUser, @RequestParam long id) {
        noticeService.noticeDelete(principalUser, id);
        return "success";
    }

    @PostMapping("/notice/restore")
    @ResponseBody
    public String noticeRestore(@RequestParam long id) {
        noticeService.noticeRestore(id);
        return "success";
    }

    @GetMapping("/notice/add")
    public String noticeAdd(Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
        model.addAttribute("user", principalUser);
        return "notice_add";
    }

    @PostMapping("/notice/add/confirm")
    @ResponseBody
    public String noticeAddConfirm(
        @AuthenticationPrincipal PrincipalUser principalUser, @RequestParam String title, @RequestParam String content
    ) {
        noticeService.noticeCreate(principalUser, title, content);
        return "success";
    }

}
