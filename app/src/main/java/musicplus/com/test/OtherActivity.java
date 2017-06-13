package musicplus.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class OtherActivity extends AppCompatActivity {
    private ShareUtil shareUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shareUtil = new ShareUtil(this);
        TextView textView = (TextView)findViewById(R.id.content);
        textView.setText("HEllO 等哈说恐龙当家阿拉山口打开");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUtil.shareVideo("com.tencent.mm",
                        "com.tencent.mm.ui.tools.ShareImgUI", new File("/storage/emulated/0/test.mp4"));
            }
        });

    }
}
