import com.kaishengit.entity.User;
import com.kaishengit.entity.UserDao;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringTest {


    @Test
    public void init() {
        //FileSystemXmlApplicationContext("D：///") 在硬盘上找到配置文件
        //ClassPathXmlAppliactiontext 从spring中加载appli。。。xml配置文件
        //ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("Ioc.xml");
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassPathXmlApplicationContext classPathXml = new ClassPathXmlApplicationContext("applicationContext.xml");
      //  UserDao userDao = (UserDao) classPathXmlApplicationContext.getBean("user");
        //UserDao userDao = (UserDao) classPathXmlApplicationContext.getBean("userDao");
       // UserService userService = (UserService) classPathXmlApplicationContext.getBean("userDao");
       // userService.save();
      // userDao.siel();
       // userDao.save();






    }
}
