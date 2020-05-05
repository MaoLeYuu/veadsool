package com.cpf.veadsool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cpf.veadsool.base.BusinessException;
import com.cpf.veadsool.constants.ApplicationConstants;
import com.cpf.veadsool.constants.RulesEnum;
import com.cpf.veadsool.constants.StudentCreditsFlowEnum;
import com.cpf.veadsool.dto.StudentDto;
import com.cpf.veadsool.dto.StudentFilesDto;
import com.cpf.veadsool.entity.Rules;
import com.cpf.veadsool.entity.StudentCreditsFlow;
import com.cpf.veadsool.entity.StudentFiles;
import com.cpf.veadsool.mapper.StudentFilesMapper;
import com.cpf.veadsool.service.IRulesService;
import com.cpf.veadsool.service.IStudentCreditsFlowService;
import com.cpf.veadsool.service.IStudentFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpf.veadsool.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @Resource
    private IStudentCreditsFlowService studentCreditsFlowService;
    @Resource
    private IRulesService rulesService;
    @Resource
    private IStudentService studentService;
    @Override
    public boolean update(StudentFiles studentFiles) {
        if (studentFiles.getId() == null) {
            throw new BusinessException("数据一刷新请重试");
        }
        return this.updateById(studentFiles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(StudentFiles studentFiles) {
        LambdaQueryWrapper<StudentCreditsFlow> qw = new LambdaQueryWrapper<>();
        qw.eq(StudentCreditsFlow::getStudentId, studentFiles.getStudentId())
        .eq(StudentCreditsFlow::getStatus, StudentCreditsFlowEnum.StatusEnum.NO.getCode());
        List<StudentCreditsFlow> list = studentCreditsFlowService.list(qw);
        List<Integer> roleIdList = list.parallelStream().map(StudentCreditsFlow::getRuleId).collect(Collectors.toList());
        List<Rules> rules = rulesService.listByIds(roleIdList);
        BigDecimal attendanceScore = ApplicationConstants.INIT_ATTENDANCE_SCORE;
        BigDecimal otherScore = ApplicationConstants.INIT_OTHER_SCORE;
        for (Rules rule : rules) {
            if (rule.getRuleType().equals(RulesEnum.RuleTypeEnum.attendance.getCode())){
                if (rule.getRuleFlag()){
                    attendanceScore = attendanceScore.add(rule.getChangePoints());
                }else {
                    attendanceScore = attendanceScore.subtract(rule.getChangePoints());
                }
            }
            if (rule.getRuleType().equals(RulesEnum.RuleTypeEnum.rule.getCode())){
                if (rule.getRuleFlag()){
                    otherScore = otherScore.add(rule.getChangePoints());
                }else {
                    otherScore = otherScore.subtract(rule.getChangePoints());
                }
            }
        }
        studentFiles.setAttendanceScore(attendanceScore);
        studentFiles.setOtherScore(otherScore);
        studentFiles.setRealScore(attendanceScore.add(otherScore).add(new BigDecimal(studentFiles.getCulturalSubjectScore())));
        this.save(studentFiles);
        for (StudentCreditsFlow studentCreditsFlow : list) {
            studentCreditsFlow.setStatus(StudentCreditsFlowEnum.StatusEnum.YES.getCode());
        }
        studentCreditsFlowService.updateBatchById(list);
    }

    @Override
    public List<StudentFilesDto> listFiles() {
        List<StudentFiles> list = this.list();
        List<Integer> studentIdList = list.parallelStream().map(StudentFiles::getStudentId).collect(Collectors.toList());
        List<StudentDto> studentDtos = studentService.listStudentByIds(studentIdList);
        Map<Integer, StudentDto> studentDtoMap = studentDtos.parallelStream().collect(Collectors.toMap(StudentDto::getId, Function.identity()));
        return list.parallelStream().map(e -> {
            StudentDto studentDto = studentDtoMap.get(e.getStudentId());
            StudentFilesDto studentFilesDto = new StudentFilesDto();
            BeanUtils.copyProperties(e, studentFilesDto);
            studentFilesDto.setGradeName(studentDto.getGradeName());
            studentFilesDto.setStudentNo(studentDto.getStudentNo());
            studentFilesDto.setStudentName(studentDto.getStudentName());
            return studentFilesDto;
        }).collect(Collectors.toList());
    }


}
