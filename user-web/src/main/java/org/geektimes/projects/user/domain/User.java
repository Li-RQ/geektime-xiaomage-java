package org.geektimes.projects.user.domain;

import org.geektimes.projects.user.validator.bean.validation.SearchUserById;
import org.geektimes.projects.user.validator.bean.validation.UserLogin;
import org.geektimes.projects.user.validator.bean.validation.UserRegister;
import org.geektimes.projects.user.web.controller.UserController;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

/**
 * 用户领域对象
 *
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @NotNull(groups = SearchUserById.class)
    private Long id;

    @Column
    @Length(min = 4, groups = {UserRegister.class, UserLogin.class}, message = "用户名应大于等于4位")
    @NotNull(groups = {UserRegister.class, UserLogin.class}, message = "未输入用户名")
    private String name;

    @Column
    @Length(min = 6, max = 32, groups = UserRegister.class, message = "密码应在6到32位")
    @NotNull(groups = {UserRegister.class, UserLogin.class}, message = "未输入密码")
    private String password;

    @Column
    @Email(groups = UserRegister.class, message = "邮箱格式异常")
    @NotNull(groups = {UserRegister.class}, message = "未输入邮箱")
    private String email;

    @Column
    @Length(min = 11, max = 11, groups = UserRegister.class, message = "手机号码应为11位")
    @NotNull(groups = {UserRegister.class}, message = "未输入手机号码")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
