package com.atguigu.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.atguigu.lease.web.*.mapper")
//@MapperScan({"com.atguigu.lease.web.app.mapper", "com.atguigu.lease.web.admin.mapper"})
public class MybatisPlusConfiguration {

}