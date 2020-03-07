package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.base.BusinessException;
import com.cpf.veadsool.dto.StudentDto;
import com.cpf.veadsool.entity.Grade;
import com.cpf.veadsool.entity.Student;
import com.cpf.veadsool.mapper.StudentMapper;
import com.cpf.veadsool.service.IGradeService;
import com.cpf.veadsool.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private IGradeService iGradeService;
    @Override
    public boolean update(Student student) {
        if (student.getId() == null) {
            throw new BusinessException("数据一刷新请重试");
        }
        return this.updateById(student);
    }

    @Override
    public List<StudentDto> listStudent() {
        List<Student> list = this.list();
        if (CollectionUtils.isNotEmpty(list)){
            List<Integer> studentIdList = list.parallelStream().map(Student::getGradeId).collect(Collectors.toList());
            List<Grade> gradeList = iGradeService.listByIds(studentIdList);
            Map<Integer, String> collect = gradeList.parallelStream().collect(Collectors.toMap(Grade::getId, Grade::getGradeName));
            return list.parallelStream().map(e -> {
                StudentDto studentDto = new StudentDto();
                BeanUtils.copyProperties(e, studentDto);
                studentDto.setGradeName(collect.get(e.getGradeId()));
                return studentDto;
            }).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
