package jobproject.smsf.money.com.deliveryagent.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import jobproject.smsf.money.com.deliveryagent.gen.MoneyBean;

import jobproject.smsf.money.com.deliveryagent.greendao.gen.MoneyBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig moneyBeanDaoConfig;

    private final MoneyBeanDao moneyBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        moneyBeanDaoConfig = daoConfigMap.get(MoneyBeanDao.class).clone();
        moneyBeanDaoConfig.initIdentityScope(type);

        moneyBeanDao = new MoneyBeanDao(moneyBeanDaoConfig, this);

        registerDao(MoneyBean.class, moneyBeanDao);
    }
    
    public void clear() {
        moneyBeanDaoConfig.clearIdentityScope();
    }

    public MoneyBeanDao getMoneyBeanDao() {
        return moneyBeanDao;
    }

}
