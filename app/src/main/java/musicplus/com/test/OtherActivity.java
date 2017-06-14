package musicplus.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherActivity extends AppCompatActivity {
    private ShareUtil shareUtil;
    @BindView(R.id.content)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        shareUtil = new ShareUtil(this);
        textView.setText("HEllO 等哈说恐龙当家阿拉山口打开");
    }

    @OnClick(R.id.content)
    public void onViewClicked() {
        shareUtil.shareVideo("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareImgUI", new File("/storage/emulated/0/test.mp4"));
    }
}
