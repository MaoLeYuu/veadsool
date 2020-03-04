package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.base.BusinessException;
import com.cpf.veadsool.entity.StudentCreditsFlow;
import com.cpf.veadsool.mapper.StudentCreditsFlowMapper;
import com.cpf.veadsool.service.IStudentCreditsFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学分变动流水 服务实现类
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@Service
public class StudentCreditsFlowServiceImpl extends ServiceImpl<StudentCreditsFlowMapper, StudentCreditsFlow> implements IStudentCreditsFlowService {

    @Override
    public boolean update(StudentCreditsFlow studentCreditsFlow) {
        if (studentCreditsFlow.getId() == null) {
            throw new BusinessException("数据一刷新请重试");
        }
        return this.updateById(studentCreditsFlow);
    }
}
