package com.dennis.dao.mybatis;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

/**
 * Created by Mr.Dxs on 2018/7/17.
 * MyBatis 生成代码 自定义 类型转换器
 */
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    /**
     * tinyint 转换为 Integer
     * 注：tinyint 长度必须为 4
     */
    public MyJavaTypeResolver() {
        super();
        super.typeMap.put(-6, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }

}
