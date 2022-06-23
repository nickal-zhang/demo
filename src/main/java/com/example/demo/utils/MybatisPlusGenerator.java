package com.example.demo.utils;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @className: MybatisPlusGenerator
 * @description:
 * @author: sh.Liu
 * @date: 2022-01-26 11:19
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/demo?useUnicode=true&useSSL=false&characterEncoding=utf8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("springBoot-Learning") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("demo") // 设置父包模块名
                            // .service()  // 设置自定义service路径,不设置就是默认路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("usc_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_","usc_")
                            // 设置自动填充的时间字段
                            .entityBuilder().addTableFills(
                                    new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.INSERT_UPDATE))
                    ; // 设置过滤表前缀

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.apache.ibatis.reflection.MetaObject;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Date;
//
//@EnableTransactionManagement
//@Configuration
//@MapperScan("com.example.demo.mapper")
//public class MybatisPlusConfig implements MetaObjectHandler {
//
//    /**
//     * 分页插件
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("isDeleted", false, metaObject);
//        this.setFieldValByName("createId", "1", metaObject);
//        this.setFieldValByName("createName", "创建人姓名", metaObject);
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("updateId", "2", metaObject);
//        this.setFieldValByName("updateName", "更新人姓名", metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateId", "2", metaObject);
//        this.setFieldValByName("updateName", "更新人姓名", metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//    }
//
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 配置监控服务器
//     *
//     * @return 返回监控注册的servlet对象
//     */
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
//        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        // 添加IP白名单
////        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
//        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
////        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
//        // 添加控制台管理用户
//        servletRegistrationBean.addInitParameter("loginUsername", "blog_admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "43kqZ$WG");
//        // 是否能够重置数据
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//        return servletRegistrationBean;
//    }
//}
