package com.example.exp4.Controller;

import com.example.exp4.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Controller
public class controller {
    private List<User> userList = new ArrayList<User>();
    private int id = 0;         // 记录id号，用于修改、删除

    // 返回登陆页面
    @RequestMapping("/login")
    public String Login(Model model)    {
        return "login";
    }

    // 通讯录页面
    @RequestMapping("/table")
    public ModelAndView Index(Model model) {
        model.addAttribute("user", userList);
        ModelAndView modelAndView = new ModelAndView("table", "userModel", model);
        return modelAndView;
    }

    // 添加
    @RequestMapping("/add")
    public ModelAndView Add(Model model) {
        id += 1;            // id增加
        model.addAttribute("user", new User(id));
//        System.out.println("id"+id);
//        System.out.println("id"+model.getAttribute("user"));
        model.addAttribute("title", "添加联系人");
        ModelAndView modelAndView = new ModelAndView("add", "userModel", model);
        return modelAndView;
    }

    // 获取用户名和密码，判断是否正确
    @PostMapping(value = "/login/input")
    public String Login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) throws IOException {
        if (username.equals("admin") && password.equals("123456")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "admin");
            return "redirect:/table";           // 用户名密码正确则进入通讯录页面
        }
        else {
            return "redirect:/login";           // 否则返回登录页面
        }
    }

    // 进入add页面输入时，更新添加页面
    @PostMapping(value = "/add/post")
    public String ADD(User user) {
        userList.add(user);
//        System.out.println(user.getCurrentID());
        return "redirect:/table";               // 添加完毕返回通讯录页面
    }

    // 在通讯录页面进行删除时，更新页面
    @PostMapping(value = "/table/delete/{currentID}")
    public String deleteUser(@PathVariable("currentID")int ID) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getCurrentID() == ID) {
                iterator.remove();              // 若ID匹配，则删除该项
                break;
            }
        }
        // 更改其他项的ID
        Integer changeID = 1;
        while (iterator.hasNext()) {
            User user = iterator.next();
            user.setCurrentID(changeID);
        }
        id -= 1;        // 总id减1
        return "redirect:/table";               // 删除完成返回通讯录页面
    }

    @GetMapping(value = "/table/edit/{currentID}")
    public ModelAndView edit(@PathVariable("currentID")int ID, Model model) {
        System.out.println("更新"+ID);
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getCurrentID() == ID) {
                model.addAttribute("user", currentUser);
                System.out.println(currentUser.getCurrentID() + currentUser.getName());
                break;
            }
        }
        ModelAndView modelAndView = new ModelAndView("edit", "userModel", model);
        return modelAndView;
    }

    @PostMapping(value = "/edit/post/{currentID}")
    public String editPost(@PathVariable("currentID")int ID, User user, Model model) {
        int temp = user.getCurrentID();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getCurrentID() == temp) {
                Collections.replaceAll(userList, currentUser, user);
                break;
            }
        }
        return "redirect:/table";
    }

    @RequestMapping("/edit/{currentID}")
    public String Edit(@PathVariable("currentID")int ID, Model model) {
        return "edit";
    }

    @RequestMapping("/table/edit/{currentID}")
    public String tableEdit(@PathVariable("currentID")int ID, Model model) {
        return  "edit";
    }

    /*登出信息提交*/
    @RequestMapping("/logout")
    public String logout(RedirectAttributes attributes, HttpServletRequest request){
        HttpSession session = request.getSession();
        /*执行登出*/
        session.removeAttribute("username");

        return "redirect:/";
    }
}
