package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.base.BusinessException;
import com.cpf.veadsool.entity.StudentFiles;
import com.cpf.veadsool.mapper.StudentFilesMapper;
import com.cpf.veadsool.service.IStudentFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生档案 服务实现类
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
@Service
public class StudentFilesServiceImpl extends ServiceImpl<StudentFilesMapper, StudentFiles> implements IStudentFilesService {

    @Override
    public boolean update(StudentFiles studentFiles) {
        if (studentFiles.getId() == null) {
            throw new BusinessException("数据一刷新请重试");
        }
        return this.updateById(studentFiles);
    }
}
