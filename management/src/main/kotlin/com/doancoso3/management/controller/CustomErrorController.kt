package com.doancoso3.management.controller


import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CustomErrorController : ErrorController {

    @GetMapping("/error")
    fun handleError(): String {
        // Xử lý lỗi và trả về trang lỗi tùy chỉnh
        return "errorPage"
    }

    fun getErrorPath(): String {
        return "/error"
    }
}
