package com.example.hello;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ouyangshen on 2017/6/26.
 */

public class EasyJavaActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        TextView tv_hello = (TextView) findViewById(R.id.tv_hello);
        tv_hello.setText("你好呀");

        final Button btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_click.setText("您点了一下下");
            }
        });

        final Button btn_click_long = (Button) findViewById(R.id.btn_click_long);
        btn_click_long.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btn_click_long.setText("您长按了一小会");
                return true;
            }
        });

        final Button btn_toast = (Button) findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EasyJavaActivity.this, "小提示：您点了一下下", Toast.LENGTH_SHORT).show();
            }
        });

        final Button btn_toast_long = (Button) findViewById(R.id.btn_toast_long);
        btn_toast_long.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(EasyJavaActivity.this, "长提示：您长按了一小会", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }
}
