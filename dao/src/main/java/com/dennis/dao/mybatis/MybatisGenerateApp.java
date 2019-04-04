package com.dennis.dao.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Dxs on 2018/7/17.
 */
public class MybatisGenerateApp {


    public static void main(String[] args) {

        // TODO: 自动生成目标文件  记得要注释 避免再次生成造成覆盖
        // TODO: 分库 自动生成时注意 指向那个数据库  以及生成代码的位置
        // myGenerate();

    }

    public static void myGenerate(){
        List<String> warnings = new ArrayList<>();
        try {

            String configFilePath = System.getProperty("user.dir").concat("/dao/src/main/resources/mybatis/generatorConfig.xml");
            boolean overwrite = true;
            File configFile = new File(configFilePath);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                    callback, warnings);
            myBatisGenerator.generate(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
