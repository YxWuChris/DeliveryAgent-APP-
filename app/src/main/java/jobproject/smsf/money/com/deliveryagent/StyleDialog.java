package jobproject.smsf.money.com.deliveryagent;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by zy18483 on 2019/1/16.
 */

public class StyleDialog extends Dialog {


    public static final int WRAP_CONTENT = WindowManager.LayoutParams.WRAP_CONTENT;
    public static final int MATCH_PARENT = WindowManager.LayoutParams.MATCH_PARENT;

    private Window window;
    private Context mContext;

    private TextView title_text;
    private EditText content_text;
    private Button canlece_btn;
    private Button yes_btn;
    private Button yes_btn1;
    private Spinner spinner;
    private String [] mStringArray;
    private ArrayAdapter<String> mAdapter ;


    public StyleDialog(Context context) {
        super(context, R.style.custom_dialog);
        mContext = context;
        init();
    }

    private void init() {
        window = getWindow();
        window.setContentView(R.layout.dialog_style_layout);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = (int) ScreenUtils.pixelWidth;
        wlp.height = WRAP_CONTENT;
        window.setAttributes(wlp);

        title_text = findViewById(R.id.title_text);
        content_text = findViewById(R.id.content_text);
        canlece_btn = findViewById(R.id.canlece_btn);
        yes_btn = findViewById(R.id.yes_btn);
        spinner = findViewById(R.id.spinner);
        yes_btn1 = findViewById(R.id.yes_btn1);
        spinner = findViewById(R.id.spinner);
        mStringArray=mContext.getResources().getStringArray(R.array.study_view_spinner_values);
        mAdapter = new TestArrayAdapter(mContext,mStringArray);
        spinner.setAdapter(mAdapter);

    }


    public StyleDialog onSetTextTile(CharSequence str) {
        if (!TextUtils.isEmpty(str)) {
            title_text.setText(str);
        }
        return this;
    }



    public StyleDialog onSetCanleceBtn(Button.OnClickListener canleceListenet) {
        canlece_btn.setOnClickListener(canleceListenet);
        return this;
    }

    public StyleDialog onSetyesBtn(Button.OnClickListener canleceListenet) {
        yes_btn.setOnClickListener(canleceListenet);
        return this;
    }

    public StyleDialog onSetyes1Btn(Button.OnClickListener canleceListenet) {
        yes_btn1.setOnClickListener(canleceListenet);
        return this;
    }

    public StyleDialog onSetyes1Btn(Button.OnClickListener canleceListenet, CharSequence str) {
        yes_btn1.setOnClickListener(canleceListenet);
        yes_btn1.setText(str);
        return this;
    }




}
