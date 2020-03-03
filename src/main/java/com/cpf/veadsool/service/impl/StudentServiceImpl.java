package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.entity.Student;
import com.cpf.veadsool.mapper.StudentMapper;
import com.cpf.veadsool.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生 服务实现类
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
