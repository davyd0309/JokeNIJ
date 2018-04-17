package pl.dawydiuk.fixture;

import fit.ColumnFixture;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.UserServiceImpl;

public class UserServiceAddUserTestFixture extends ColumnFixture implements ApplicationContextAware {


    private UserService userService;
    private ApplicationContext ctx;



    public void execute() {

        userService = (UserService) ctx.getBean("userServiceImpl");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.ctx = context;
    }
}
