package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.VeadSoolApplicationTest;
import com.cpf.veadsool.entity.GradeFiles;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author caopengflying
 * @time 2020/3/3
 */

public class GradeFilesServiceImplTest extends VeadSoolApplicationTest {
    @Resource
    private GradeFilesServiceImpl gradeFilesService;

    @Test
    public void testCreate() {
        GradeFiles gradeFiles = new GradeFiles();
        gradeFiles.setAveScore(BigDecimal.TEN);
        gradeFiles.setBadCount(1);
        gradeFiles.setGradeId(1);
        gradeFiles.setGreatCount(20);
        gradeFiles.setMaxScore(BigDecimal.ZERO);
        gradeFiles.setMinScore(BigDecimal.ZERO);
        gradeFiles.setCreateUser(30);
        gradeFilesService.save(gradeFiles);
    }

}