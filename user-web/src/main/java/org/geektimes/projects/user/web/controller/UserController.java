package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/user")
public class UserController  implements PageController {

    private static UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

    @GET
    @POST
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        if (name == null || name.trim().equals("") || password == null || password.trim().equals("")) {
            request.setAttribute("error", "未输入用户名密码！");
            return "register.jsp";
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        if (!userService.register(user)) {
            request.setAttribute("error", "注册失败，用户名已被占用，请更改用户名再次重试！");
            return "register.jsp";
        }
        request.getSession().setAttribute("curUser", userService.queryUserByNameAndPassword(name, password));
        return "user.jsp";
    }

    @GET
    @Path("/info")
    public String userInfo(HttpServletRequest request, HttpServletResponse response)  throws Throwable {
        return "user.jsp";
    }

    @GET
    @POST
    @Path("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (name == null || name.trim().equals("") || password == null || password.trim().equals("")) {
            request.setAttribute("error", "未输入用户名或密码！");
            return "login-form.jsp";
        }
        User user = userService.queryUserByNameAndPassword(name, password);
        if (user == null) {
            request.setAttribute("error", "用户名和密码不匹配！");
            return "login-form.jsp";
        }
        request.getSession().setAttribute("curUser", userService.queryUserByNameAndPassword(name, password));
        return "user.jsp";
    }
}
