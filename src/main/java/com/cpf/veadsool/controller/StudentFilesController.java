package com.cpf.veadsool.controller;


import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.dto.RulesDto;
import com.cpf.veadsool.dto.StudentFilesDto;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.entity.StudentFiles;
import com.cpf.veadsool.service.IRulesService;
import com.cpf.veadsool.service.IStudentFilesService;
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
 * 学生档案 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/studentFiles")
public class StudentFilesController {
    @Resource
    private IStudentFilesService iStudentFilesService;

    @GetMapping("/list")
    public Result<List<StudentFilesDto>> list() {
        List<StudentFiles> list = iStudentFilesService.list();
        List<StudentFilesDto> result = ModelTransformUtils.exchangeClassList(list, StudentFilesDto.class);
        return ErrorConstant.getSuccessResult(result);
    }

    @PostMapping("/create")
    public Result create(StudentFiles studentFiles) {
        boolean save = iStudentFilesService.save(studentFiles);
        if (save) {
            return ErrorConstant.getSuccessResult("新增成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "新增失败");
    }

    @PostMapping("/update")
    public Result<List<RulesDto>> update(StudentFiles studentFiles) {
        boolean update = iStudentFilesService.update(studentFiles);
        if (update) {
            return ErrorConstant.getSuccessResult("修改成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "修改失败");
    }

    @PostMapping("/delete")
    public Result<List<RulesDto>> delete(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            boolean delete = iStudentFilesService.removeByIds(ids);
            if (delete) {
                return ErrorConstant.getSuccessResult("删除成功");
            }
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "删除失败");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "删除失败");
    }

}
