package com.myself.test.serivce.impl;

import com.myself.test.domain.business.Course;
import com.myself.test.domain.system.User;
import com.myself.test.mapper.business.CourseMapper;
import com.myself.test.mapper.system.UserMapper;
import com.myself.test.serivce.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类名称：UserServiceImpl<br>
 * 类描述：<br>
 * 创建时间：2018年12月27日<br>
 *
 * @author maopanpan
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public UserServiceImpl(UserMapper userMapper, CourseMapper courseMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(User user, Course course) throws Exception {
        userMapper.insert(user);
        courseMapper.insert(course);
        throw new Exception("操作异常");
    }
}
