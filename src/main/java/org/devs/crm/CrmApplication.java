package org.devs.crm;

import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.config.DaoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CrmApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        GroupDao groupDao = context.getBean(GroupDao.class);

        System.out.println(groupDao.findById(1L));
    }
}
