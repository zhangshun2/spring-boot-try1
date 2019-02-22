package com.baizhi.csa.controller;

import com.baizhi.csa.dao.UserMapper;
import com.baizhi.csa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("showall")

    public Object showall(HttpSession session) {
        session.setAttribute("all", userMapper.selectAll());
        return "emplist";
    }

    @RequestMapping("login")

    public Object login(String username, String password) {
        if (username != null) {
            User user = userMapper.selectByusername(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return "redirect:showall";
                }
            }
        }

        return "login";

    }

    @RequestMapping("add")

    public Object add(User user) {
        int i = userMapper.insertSelective(user);
        if (i != 0) {
            return "redirect:showall";
        }
        return "addEmp";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        System.out.println("id: =================" + id + "========");
        if (id == null) {
            return "redirect:showall";
        }
        int i = userMapper.deleteByPrimaryKey(id);
        return "redirect:showall";
    }


    @RequestMapping("/get")
    public String get(Integer id, HttpServletRequest req) {
        User user = userMapper.selectByPrimaryKey(id);
        req.setAttribute("users", user);
        return "updateEmp";
    }

    /**
     * 修改员工信息
     *
     * @param users
     * @return
     */
    @RequestMapping("/update")
    public String update(User users) {
        if (users == null) {
            return "redirect:showall";
        }
        int i = userMapper.updateByPrimaryKeySelective(users);
        return "redirect:showall";
    }


    @RequestMapping("regist")
    public Object regist(User user, HttpSession session, String codeget) {
        System.out.println(user);
        String code = (String) session.getAttribute("code");
        System.out.println("验证码:" + code);
        System.out.println(code);
        if (codeget.equals(code)) {
            if (user != null) {
                if (user.getUsername() != null) {
                    User user1 = userMapper.selectByusername(user.getUsername());
                    if (user1 == null) {
                        userMapper.insertSelective(user);
                        return "redirect:showall";
                    }
                }
            }
        }

        return "redirect:showall";
    }

}






