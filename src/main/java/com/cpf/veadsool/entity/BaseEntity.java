package com.cpf.veadsool.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author caopengflying
 * @time 2020/3/3
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Integer updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
