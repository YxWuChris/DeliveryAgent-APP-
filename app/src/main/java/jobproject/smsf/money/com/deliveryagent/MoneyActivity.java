package jobproject.smsf.money.com.deliveryagent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import jobproject.smsf.money.com.deliveryagent.gen.MoneyBean;
import jobproject.smsf.money.com.deliveryagent.greendao.gen.MoneyBeanDao;

/**
 * Created by zy18483 on 2019/5/23.
 */

public class MoneyActivity extends AppCompatActivity{

    private TextView id_text,date_text,type_text,money_text;
    private MoneyBeanDao mMoneyBeanDao;
    private Long id;
    private RelativeLayout back_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        id = getIntent().getLongExtra("id",0);
        id_text = findViewById(R.id.id_text);
        date_text = findViewById(R.id.date_text);
        type_text = findViewById(R.id.type_text);
        money_text = findViewById(R.id.money_text);
        mMoneyBeanDao = GreenDaoHelper.getInstance().getDaoSession().getMoneyBeanDao();

        List<MoneyBean> moneyBeans = mMoneyBeanDao.queryBuilder().where(MoneyBeanDao.Properties.Id.eq(id)).list();
        if (moneyBeans != null && moneyBeans.size() > 0) {

            id_text.setText(moneyBeans.get(0).getId()+"");
            date_text.setText(moneyBeans.get(0).getDate()+"");
            type_text.setText(moneyBeans.get(0).getType()+"");
            money_text.setText(moneyBeans.get(0).getMoney()+"");
        }
        back_img  = findViewById(R.id.back_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
