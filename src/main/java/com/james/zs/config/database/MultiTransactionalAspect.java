package com.james.zs.config.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Stack;

@Aspect
@Component
public class MultiTransactionalAspect {
//    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @Around(value="@annotation(multiTransactional)")
    public Object around(ProceedingJoinPoint pjp,
                         MultiTransactional multiTransactional) throws Throwable {

        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<DataSourceTransactionManager>();
        Stack<TransactionStatus> transactionStatuStack = new Stack<TransactionStatus>();

        try {

            if (!openTransaction(dataSourceTransactionManagerStack,
                    transactionStatuStack, multiTransactional)) {
                return null;
            }

            Object ret = pjp.proceed();

            commit(dataSourceTransactionManagerStack, transactionStatuStack);

            return ret;
        } catch (Throwable e) {

            rollback(dataSourceTransactionManagerStack, transactionStatuStack);

//            logger.error(String.format(
//                    "MultiTransactionalAspect, method:%s-%s occors error:", pjp
//                            .getTarget().getClass().getSimpleName(), pjp
//                            .getSignature().getName()), e);
            throw e;
        }
    }


    private boolean openTransaction(
            Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
            Stack<TransactionStatus> transactionStatuStack,
            MultiTransactional multiTransactional) {

        String[] transactionMangerNames = multiTransactional.values();
        if (multiTransactional.values() == null || multiTransactional.values().length == 0) {
            return false;
        }

        for (String beanName : transactionMangerNames) {
            DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) context
                    .getBean(beanName);
            TransactionStatus transactionStatus = dataSourceTransactionManager
                    .getTransaction(new DefaultTransactionDefinition());
            transactionStatuStack.push(transactionStatus);
            dataSourceTransactionManagerStack
                    .push(dataSourceTransactionManager);
        }
        return true;
    }

    /**
     * @author Zhu
     * @date 2015-7-25下午7:56:39
     * @description
     * @param dataSourceTransactionManagerStack
     * @param transactionStatuStack
     */
    private void commit(
            Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
            Stack<TransactionStatus> transactionStatuStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            dataSourceTransactionManagerStack.pop().commit(
                    transactionStatuStack.pop());
        }
    }

    /**
     * @author Zhu
     * @date 2015-7-25下午7:56:42
     * @description
     * @param dataSourceTransactionManagerStack
     * @param transactionStatuStack
     */
    private void rollback(
            Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
            Stack<TransactionStatus> transactionStatuStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            dataSourceTransactionManagerStack.pop().rollback(
                    transactionStatuStack.pop());
        }
    }
}
