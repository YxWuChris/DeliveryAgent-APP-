package jobproject.smsf.money.com.deliveryagent;

import android.app.Application;


/**
 * Main Application
 */

public class MainApplication extends Application {

    public static MainApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }


    private void setDatabase() {
        GreenDaoHelper.getInstance().setupGreenDao(instances);
    }

    public static MainApplication getInstances() {
        return instances;
    }
}
