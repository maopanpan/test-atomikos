package com.myself.test.serivce;


import com.myself.test.domain.business.Course;
import com.myself.test.domain.system.User;

/**
 * 类名称：UserService<br>
 * 类描述：<br>
 * 创建时间：2018年12月27日<br>
 *
 * @author maopanpan
 * @version 1.0.0
 */
public interface UserService {

    public void add(User user, Course course) throws Exception;
}
