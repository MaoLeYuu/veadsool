package com.cpf.veadsool.service.impl;

import com.cpf.veadsool.VeadSoolApplicationTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author caopengflying
 * @time 2020/3/3
 */

class GradeFilesServiceImplTest extends VeadSoolApplicationTest {
    @Resource
    private GradeFilesServiceImpl gradeFilesService;
    @Test
    public void testCreate(){
        GradeFiles gradeFiles = new GradeFiles();
        gradeFiles.setAveScore(BigDecimal.TEN);
        gradeFiles.setBadCount(1);
//        gradeFilesService.save()
    }

}