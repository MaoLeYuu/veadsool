package com.cpf.veadsool.service;

import com.cpf.veadsool.entity.StudentFiles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生档案 服务类
 * </p>
 *
 * @author caopengflying
 * @since 2020-03-03
 */
public interface IStudentFilesService extends IService<StudentFiles> {
    /**
     * 修改
     * @param studentFiles
     * @return
     */
    boolean update(StudentFiles studentFiles);
}
