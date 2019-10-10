package com.example.demo.controller;

import com.example.demo.entity.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@RequestMapping("/huDemo")
@Controller
public class TemplatesController {

    @Autowired
    private UserService userService;

    @GetMapping("/templates")
    String test(HttpServletRequest request) {

        UserDto userDto = userService.getUser("");
        // 逻辑处理
        request.setAttribute("name", userDto.getUserName());
//       int i = userService.insertUser();
//       System.out.print("----------------------"+i);
//       int ij = userService.updateUser();
//       System.out.print("----------------------"+ij);
        return "/index";
    }

    @RequestMapping("/tableTest")
    public String tableTest(Model model) {
        List<UserDto> userDtos = userService.selectAll();
        // 逻辑处理
        model.addAttribute("userDtoList",userDtos);
        return "/index";
    }

    @PostMapping("/insertTestForPost")
    public String insertTestForPost( HttpServletRequest request, Model model) {
        UserDto userDto = new UserDto();
        userDto.setUserId((String)request.getParameter("userId"));
        userDto.setUserName((String) request.getParameter("userName"));
        userDto.setLoginName((String) request.getParameter("loginName"));
        userDto.setUserPass((String) request.getParameter("userPass"));
        int i = userService.insertUser(userDto);
        List<UserDto> userDtos = userService.selectAll();
        // 逻辑处理
        model.addAttribute("userDtoList",userDtos);
        return "/index";
    }

    @RequestMapping("/formTest")
    public String submitTest(Model model) {
        return "/formTest";
    }

    @GetMapping("/insertTestFotGet")
    public String insertTestFotGet(@RequestParam("userid") String userid,@RequestParam("username") String username,@RequestParam("mobile") String mobile,Model model) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userid);
        userDto.setUserName("");
        userDto.setLoginName("");
        userDto.setUserPass("");
        int i = userService.insertUser(userDto);
        List<UserDto> userDtos = userService.selectAll();
        // 逻辑处理
        model.addAttribute("userDtoList",userDtos);
        return "/index";
    }
}
