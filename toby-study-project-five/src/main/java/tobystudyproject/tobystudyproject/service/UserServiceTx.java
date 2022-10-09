package tobystudyproject.tobystudyproject.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tobystudyproject.tobystudyproject.User;

public class UserServiceTx implements UserService{
    UserService userService;
    PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add(User user) {
        this.userService.add(user);
    }

    @Override
    public void upgradeLevels() {
        //부가기능 수행
        TransactionStatus  status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            //위임
            userService.upgradeLevels();

            //부가기능 수행
            this.transactionManager.commit(status);
        } catch(RuntimeException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
        userService.upgradeLevels();
    }
}
