package jobproject.smsf.money.com.deliveryagent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import jobproject.smsf.money.com.deliveryagent.greendao.gen.DaoMaster;
import jobproject.smsf.money.com.deliveryagent.greendao.gen.DaoSession;


/**
 *
 * Created by zy18483 on 2019/5/21
 */

public  class GreenDaoHelper {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoHelper() {

    }

    public static GreenDaoHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final GreenDaoHelper INSTANCE = new GreenDaoHelper();
    }

    /**
     * Initial DAO
     *
     *
     */
    public void setupGreenDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "money-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     *
     */
    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    /**
     *
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}


