package jobproject.smsf.money.com.deliveryagent.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zy18438
 */
@Entity
public class MoneyBean {

    @Id(autoincrement = true)
    private Long id;
    private String date;  //time
    private String money;
    private String type;
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 564571063)
    public MoneyBean(Long id, String date, String money, String type) {
        this.id = id;
        this.date = date;
        this.money = money;
        this.type = type;
    }
    @Generated(hash = 700720354)
    public MoneyBean() {
    }
   
}
