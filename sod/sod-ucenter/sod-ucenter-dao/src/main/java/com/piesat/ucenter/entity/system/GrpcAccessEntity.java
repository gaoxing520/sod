package com.piesat.ucenter.entity.system;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;

/**
 * @program: sod
 * @描述
 * @创建人 zzj
 * @创建时间 2019/11/25 10:38
 */
@Entity
@Data
@Table(name="T_SOD_GRPC_ACCESS")
public class GrpcAccessEntity {
    @Id
    @Column(length = 32)
    private String id;

    private String userId;

    private String serverId;

    private String serverIp;

    @Column(columnDefinition="blob")
    private String methods;


    @Column(nullable = false)
    private Date createTime;

    private Date updateTime;





}