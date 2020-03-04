package com.cpf.veadsool.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.cpf.veadsool.dto.RulesDto;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.service.IRulesService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 基础规则 前端控制器
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/rules")
public class RulesController {
    @Resource
    private IRulesService iRulesService;

    @GetMapping("/list")
    public Result<List<RulesDto>> list(Integer ruleType) {
        Rules rules = new Rules();
        rules.setRuleType(ruleType);
        List<RulesDto> list = iRulesService.list(rules);
        return ErrorConstant.getSuccessResult(list);
    }

    @PostMapping("/create")
    public Result create(Rules rules) {
        boolean save = iRulesService.save(rules);
        if (save) {
            return ErrorConstant.getSuccessResult("新增成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "新增失败");
    }

    @PostMapping("/update")
    public Result update(Rules rules) {
        boolean update = iRulesService.update(rules);
        if (update) {
            return ErrorConstant.getSuccessResult("修改成功");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "修改失败");
    }

    @PostMapping("/delete")
    public Result delete(List<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            boolean delete = iRulesService.removeByIds(ids);
            if (delete) {
                return ErrorConstant.getSuccessResult("删除成功");
            }
            return ErrorConstant.getErrorResult(ErrorConstant.FAIL, "删除失败");
        }
        return ErrorConstant.getErrorResult(ErrorConstant.PARAM_IS_NULL, "删除失败");
    }

}
