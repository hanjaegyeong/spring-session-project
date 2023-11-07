package com.example.springsessionproject.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {
    @RequestMapping("/upload")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try {
                // 파일을 원하는 경로에 저장
                String uploadDir = "C:\\Users\\user\\Desktop\\spring-session-project\\src\\main\\resources\\savedFiles\\"; // 파일을 저장할 경로로 수정
                String fileName = file.getOriginalFilename();
                file.transferTo(new File(uploadDir + fileName));

                redirectAttributes.addFlashAttribute("message", "파일 업로드 성공: " + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + file.getOriginalFilename());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "업로드된 파일이 비어있습니다.");
        }
        return "redirect:/"; // 업로드 후 다시 홈페이지로 리다이렉트
    }
}
