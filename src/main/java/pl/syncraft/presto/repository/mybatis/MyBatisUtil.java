package pl.syncraft.presto.repository.mybatis;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Daniel Zawlocki
 * @date 2019/11/05
 */
public class MyBatisUtil {
    public static SqlSessionFactory buildSessionFactory() throws IOException {
        LogFactory.useLog4JLogging();
        String config = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        return builder.build(inputStream);
    }
}
