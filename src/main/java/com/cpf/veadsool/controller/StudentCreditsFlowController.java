package com.cpf.veadsool.controller;


import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.dto.RulesDto;
import com.cpf.veadsool.dto.StudentCreditsFlowDto;
import com.cpf.veadsool.dto.StudentDto;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.entity.StudentCreditsFlow;
import com.cpf.veadsool.service.IRulesService;
import com.cpf.veadsool.service.IStudentCreditsFlowService;
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
 * 学分变动流水 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/student-credits-flow")
public class StudentCreditsFlowController {
    @Resource
    private IStudentCreditsFlowService iStudentCreditsFlowService;

    @GetMapping("/list")
    public Result<List<StudentCreditsFlowDto>> list() {
        List<StudentCreditsFlow> list = iStudentCreditsFlowService.list();
        List<StudentCreditsFlowDto> result = ModelTransformUtils.exchangeClassList(list, StudentCreditsFlowDto.class);
        return ErrorConstant.getSuccessResult(result);
    }

    @PostMapping("/create")
    public Result create(StudentCreditsFlow studentCreditsFlow) {
        boolean save = iStudentCreditsFlowService.save(studentCreditsFlow);
        if (save) {
            return ErrorConstant.getSuccessResult("新增成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "新增失败");
    }

    @PostMapping("/update")
    public Result<List<RulesDto>> update(StudentCreditsFlow studentCreditsFlow) {
        boolean update = iStudentCreditsFlowService.update(studentCreditsFlow);
        if (update) {
            return ErrorConstant.getSuccessResult("修改成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "修改失败");
    }

    @PostMapping("/delete")
    public Result<List<RulesDto>> delete(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            boolean delete = iStudentCreditsFlowService.removeByIds(ids);
            if (delete) {
                return ErrorConstant.getSuccessResult("删除成功");
            }
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "删除失败");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "删除失败");
    }

}
