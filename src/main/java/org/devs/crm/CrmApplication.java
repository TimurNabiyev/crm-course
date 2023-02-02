package org.devs.crm;

import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.dao.impl.GroupDaoImpl;
import org.devs.crm.model.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CrmApplication {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        GroupDao groupDao = context.getBean(GroupDao.class);

        System.out.println(groupDao.findById(1L));
    }
}
