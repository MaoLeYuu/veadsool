package com.cpf.veadsool.controller;


import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.dto.GradeDto;
import com.cpf.veadsool.dto.RulesDto;
import com.cpf.veadsool.entity.Grade;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.service.IGradeService;
import com.cpf.veadsool.service.IRulesService;
import com.cpf.veadsool.util.ModelTransformUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 班级 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    @Resource
    private IGradeService iGradeService;

    @GetMapping("/list")
    public Result<List<GradeDto>> list() {
        List<Grade> list = iGradeService.list();
        List<GradeDto> result = ModelTransformUtils.exchangeClassList(list, GradeDto.class);
        return ErrorConstant.getSuccessResult(result);
    }

    @PostMapping("/create")
    public Result create(Grade grade) {
        boolean save = iGradeService.save(grade);
        if (save) {
            return ErrorConstant.getSuccessResult("新增成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "新增失败");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Grade grade) {
        boolean update = iGradeService.update(grade);
        if (update) {
            return ErrorConstant.getSuccessResult("修改成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "修改失败");
    }

    @PostMapping("/delete")
    public Result delete(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            boolean delete = iGradeService.removeByIds(ids);
            if (delete) {
                return ErrorConstant.getSuccessResult("删除成功");
            }
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "删除失败");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "删除失败");
    }

}
