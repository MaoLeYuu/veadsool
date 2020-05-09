package com.cpf.veadsool.dto;


import com.cpf.veadsool.entity.Grade;
import com.cpf.veadsool.entity.StudentFiles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author
 * @time 2020/3/4
 */
@Getter
@Setter
@ToString
public class StudentFilesDto extends StudentFiles {
    private String studentNo;
    private String studentName;
    private String gradeName;
}
