//package com.example.demo.utils;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * mybatis-plus自动生成代码工具类
// */
//public class Generator {
//
//    @Data
//    static class ConfigureParams {
//        private String auth;
//        private String packageName;
//        private String path;
//        private String url;
//        private String username;
//        private String password;
//        private String driverName;
//        private String[] tablePrefix;
//        private String logicDeleteFieldName;
//        private String[] include;
//        private String controllerPackage;
//        private String entityPackage;
//        private String mapperPackage;
//        private String xmlPackage;
//        private String[] autoFillInsertAndUpdate;
//        private String[] autoFillInsert;
//
//        public ConfigureParams(String auth, String packageName, String path, String url, String username, String password, String driverName, String[] tablePrefix, String logicDeleteFieldName, String[] include, String controllerPackage, String entityPackage, String mapperPackage, String xmlPackage, String[] autoFillInsertAndUpdate, String[] autoFillInsert) {
//            this.auth = auth;
//            this.packageName = packageName;
//            this.path = path;
//            this.url = url;
//            this.username = username;
//            this.password = password;
//            this.driverName = driverName;
//            this.tablePrefix = tablePrefix;
//            this.logicDeleteFieldName = logicDeleteFieldName;
//            this.include = include;
//            this.controllerPackage = controllerPackage;
//            this.entityPackage = entityPackage;
//            this.mapperPackage = mapperPackage;
//            this.xmlPackage = xmlPackage;
//            this.autoFillInsertAndUpdate = autoFillInsertAndUpdate;
//            this.autoFillInsert = autoFillInsert;
//        }
//    }
//
//    /**
//     * 生成代码直接运行该main方法即可，注意需修改一些配置信息
//     * auth：//创建人
//     * packageName：//包前缀
//     * path：//项目路径
//     * url：//数据库连接地址xxx.xxx.x.xxx:端口/数据库名称
//     * username：//数据库连接用户名
//     * password：//数据库连接密码
//     * driverName：//数据库驱动
//     * tablePrefix：//生成的实体类去掉前缀，可以配置多个
//     * logicDeleteFieldName：//逻辑删除属性名称
//     * include：//要生成代码的表名，多个表名用英文逗号分割  ".*." 生成全部
//     * controllerPackage：//controller包名
//     * entityPackage：//实体类包名
//     * mapperPackage：//mapper包名
//     * xmlPackage：//xml包名
//     * autoFillInsertAndUpdate：//表中新增或修改时需自动填充的字段
//     * autoFillInsert：//表中新增时需自动填充的字段
//     */
//    public static void main(String[] args) {
//
//        String auth = "ZM";
//        String packageName = "com.example";
//        String path = "D:\\demo";
//        String url = "127.0.0.1:3306/demo";
//        String username = "root";
//        String password = "root";
//        String driverName = "com.mysql.cj.jdbc.Driver";
//        String[] tablePrefix = new String[]{"sys_", "usc_"};
//        String logicDeleteFieldName = "is_deleted";
//        String[] include = new String[]{".*."};
//        String controllerPackage = "controller";
//        String entityPackage = "entity";
//        String mapperPackage = "mapper";
//        String xmlPackage = "classpath:mapper";
//        String[] autoFillInsertAndUpdate = new String[]{"update_id", "update_name", "update_time"};
//        String[] autoFillInsert = new String[]{"create_id", "create_name", "create_time"};
//        ConfigureParams configureParams = new ConfigureParams(auth, packageName, path, url, username, password, driverName, tablePrefix, logicDeleteFieldName, include, controllerPackage, entityPackage, mapperPackage, xmlPackage, autoFillInsertAndUpdate, autoFillInsert);
//
//        //加载配置信息并执行
//        generate(configureParams);
//    }
//
//    private static void generate(ConfigureParams configureParams) {
//        //******************************全局配置******************************//
//        GlobalConfig config = new GlobalConfig();
//        //设置是否支持AR模式
//        config.setActiveRecord(true)
//                //设置生成代码的作者
//                .setAuthor(configureParams.getAuth())
//                //设置输出代码的位置
//                .setOutputDir(configureParams.getPath())
//                // XML 二级缓存
//                .setEnableCache(false)
//                // XML ResultMap
//                .setBaseResultMap(true)
//                // XML columList
//                .setBaseColumnList(true)
//                //.setKotlin(true) 是否生成 kotlin 代码
//                // 是否使用Swagger2
//                .setSwagger2(true)
//                // 设置是否覆盖原来的代码
//                .setFileOverride(false);
//
//        //******************************数据源配置******************************//
//        //数据库连接url
//        String dbUrl = "jdbc:mysql://" + configureParams.getUrl() + "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//        //数据源配置
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        //数据库类型 枚举
//        dataSourceConfig.setDbType(DbType.MYSQL)
//                //设置url
//                .setUrl(dbUrl)
//                //设置用户名
//                .setUsername(configureParams.getUsername())
//                //设置密码
//                .setPassword(configureParams.getPassword())
//                //设置数据库驱动
//                .setDriverName(configureParams.getDriverName())
//                // 自定义数据库表字段类型转换【可选】
//                .setTypeConvert(new MySqlTypeConvert() {
//                    @Override
//                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                        System.out.println("转换类型：" + fieldType);
//                        //将数据库中datetime转换成date
//                        if (fieldType.toLowerCase().contains("datetime")) {
//                            return DbColumnType.DATE;
//                        }
//                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//                    }
//                });
//
//        //******************************策略配置******************************//
//        // 自定义需要填充的字段 数据库中的字段
//        List<TableFill> tableFillList = new ArrayList<>();
//        for (String autoFillName : configureParams.getAutoFillInsertAndUpdate()) {
//            tableFillList.add(new TableFill(autoFillName, FieldFill.INSERT_UPDATE));
//        }
//        for (String autoFillName : configureParams.getAutoFillInsert()) {
//            tableFillList.add(new TableFill(autoFillName, FieldFill.INSERT));
//        }
//
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                //全局大写命名是否开启
//                .setCapitalMode(true)
//                //【实体】是否为lombok模型
//                .setEntityLombokModel(true)
//                //表名生成策略  下划线转驼峰
//                .setNaming(NamingStrategy.underline_to_camel)
//                //生成的去掉前缀，可以配置多个
//                .setTablePrefix(configureParams.getTablePrefix())
//                //自动填充设置
//                .setTableFillList(tableFillList)
//                //逻辑删除属性名称
//                .setLogicDeleteFieldName(configureParams.getLogicDeleteFieldName()).setRestControllerStyle(true)
//                //要生成代码的表名
//                .setInclude(configureParams.getInclude());
//        //集成注入设置 注入全局设置
//        new AutoGenerator().setGlobalConfig(config)
//                //注入数据源配置
//                .setDataSource(dataSourceConfig)
//                //注入策略配置
//                .setStrategy(strategyConfig)
//                //设置包名信息
//                .setPackageInfo(new PackageConfig()
//                        //提取公共父级包名
//                        .setParent(configureParams.getPackageName())
//                        //设置controller信息
//                        .setController(configureParams.getControllerPackage())
//                        //设置实体类信息
//                        .setEntity(configureParams.getEntityPackage())
//                        //设置mapper信息
//                        .setMapper(configureParams.getMapperPackage()).setXml(configureParams.getXmlPackage()))
//                //设置自定义模板
//                .setTemplate(new TemplateConfig()
//                        //.setXml(null)//指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
//                        //注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
//                        // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
//                        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
//                        // .setController("...");
//                        // .setEntity("...");
//                        // .setMapper("...");
//                        // .setXml("...");
//                        // .setService("...");
//                        .setServiceImpl("templates/serviceImpl.java"))
//                //开始执行代码生成
//                .execute();
//    }
//}