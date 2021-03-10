package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.projects.user.validator.bean.validation.UserLogin;
import org.geektimes.projects.user.validator.bean.validation.UserRegister;
import org.geektimes.web.mvc.controller.PageController;
import org.geektimes.web.mvc.header.annotation.CacheControl;
import org.geektimes.web.mvc.header.annotation.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Set;
import java.util.UUID;

@Path("/user")
@Controller(name = "userController")
public class UserController  implements PageController {

    @Resource(name = "bean/UserService")
    private UserService userService = new UserServiceImpl();
    @Resource(name = "bean/Validator")
    private Validator validator;

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
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        Set<ConstraintViolation<User>> violationSet = validator.validate(user, UserRegister.class);
        for (ConstraintViolation<User> userConstraintViolation: violationSet) {
            request.setAttribute("error", userConstraintViolation.getMessage());
            return "register.jsp";
        }
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
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Set<ConstraintViolation<User>> violationSet = validator.validate(user, UserLogin.class);
        for (ConstraintViolation<User> userConstraintViolation: violationSet) {
            request.setAttribute("error", userConstraintViolation.getMessage());
            return "login-form.jsp";
        }
        user = userService.queryUserByNameAndPassword(name, password);
        if (user == null) {
            request.setAttribute("error", "用户名和密码不匹配！");
            return "login-form.jsp";
        }
        request.getSession().setAttribute("curUser", userService.queryUserByNameAndPassword(name, password));
        return "user.jsp";
    }
}
