package com.cpf.veadsool.controller;


import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.dto.GradeDto;
import com.cpf.veadsool.dto.RulesDto;
import com.cpf.veadsool.dto.StudentDto;
import com.cpf.veadsool.entity.Grade;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.entity.Student;
import com.cpf.veadsool.service.IRulesService;
import com.cpf.veadsool.service.IStudentService;
import com.cpf.veadsool.util.ModelTransformUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学生 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private IStudentService iStudentService;

    @GetMapping("/list")
    public Result<List<StudentDto>> list() {
        List<Student> list = iStudentService.list();
        List<StudentDto> result = ModelTransformUtils.exchangeClassList(list, StudentDto.class);
        return ErrorConstant.getSuccessResult(result);
    }

    @PostMapping("/create")
    public Result create(Student student) {
        boolean save = iStudentService.save(student);
        if (save) {
            return ErrorConstant.getSuccessResult("新增成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "新增失败");
    }

    @PostMapping("/update")
    public Result update(Student student) {
        boolean update = iStudentService.update(student);
        if (update) {
            return ErrorConstant.getSuccessResult("修改成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "修改失败");
    }

    @PostMapping("/delete")
    public Result delete(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            boolean delete = iStudentService.removeByIds(ids);
            if (delete) {
                return ErrorConstant.getSuccessResult("删除成功");
            }
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "删除失败");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "删除失败");
    }

}
