package com.msb;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String projectName = ""; // family_service_platform

        // 全局配置
        GlobalConfig gc=new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 设置输出路径，也可以使用本地磁盘全路径
        gc.setOutputDir(projectPath + projectName + "/src/main/java");
        gc.setAuthor("leiy") // 作者
                .setFileOverride(true) // 文件覆盖
                .setIdType(IdType.AUTO) // 设置主键生成策略
                .setServiceName("%sService")  // 接口名称 例如：%sBusinessImpl 生成 UserBusinessImpl
                //.setBaseResultMap(true) // 基本结果集合
                //.setBaseColumnList(true) // 设置基本列
                .setControllerName("%sController"); // 控制器名称 例如：%sAction 生成 UserAction

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/family_service_platform?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 设置要包含的表
        //strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //strategy.setTablePrefix("ptl_"); // 设置表名的前缀
        strategy.setNaming(NamingStrategy.underline_to_camel); // 映射实体时的命名策略
        // 设置全局大写命名
        strategy.setCapitalMode(true);
        // 设置要包含的表
        strategy.setInclude(); // 全部表


        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName(scanner("模块名"));
        pc.setParent("com.msb") //父包名
                .setEntity("bean").setMapper("mapper").setService("service").setXml("mapper")
                .setController("controller"); //

        mpg.setGlobalConfig(gc).setDataSource(dsc).setStrategy(strategy).setPackageInfo(pc);

        mpg.execute();
    }
}
