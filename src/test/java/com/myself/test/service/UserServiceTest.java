package com.myself.test.service;

import com.myself.test.BaseTestCase;
import com.myself.test.domain.business.Course;
import com.myself.test.domain.system.User;
import com.myself.test.serivce.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 类名称：UserServiceTest<br>
 * 类描述：<br>
 * 创建时间：2018年12月27日<br>
 *
 * @author maopanpan
 * @version 1.0.0
 */
public class UserServiceTest extends BaseTestCase {
    @Autowired
    private UserService userService;

    @Test
    public void add() {
        User user = new User();
        user.setUserName("lichao");
        user.setPassword("31231311");
        user.setCreateTime(new Date());
        Course course = new Course();
        course.setCourse("C#");
        course.setScore(99);
        course.setCreateTime(new Date());
        try {
            userService.add(user, course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
