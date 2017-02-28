package com.example.progressdialogonthread;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button showProgressBt;
    TextView showSuccessTv;
    int count = 0;
    Thread thread1;
    Thread thread2;
    Thread thread3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSuccessTv = (TextView)findViewById(R.id.am_show_success_tv);
        showProgressBt = (Button)findViewById(R.id.am_show_progress_bt);
        showProgressBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setProgress(count);
                progressDialog.setMax(100);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("加载中......");
                progressDialog.setTitle("标题");
                count = progressDialog.getProgress();
                progressDialog.show();
                thread1.start();
                thread2.start();
                thread3.start();
                if (count >= 100) {
                    progressDialog.dismiss();
                }
            }
        });

        thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(count<100) {
                    progressDialog.incrementProgressBy(1);
                    count++;
                    if(count>=100){
                        handler.sendEmptyMessage(1);
                    }
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){

                    }
                }
            }
        });

        thread2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressDialog.getProgress()<100) {
                    progressDialog.incrementProgressBy(1);
                    count++;
                    if(count>=100){
                        handler.sendEmptyMessage(1);
                    }
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){

                    }
                }
            }
        });

        thread3 =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressDialog.getProgress()<100) {
                    progressDialog.incrementProgressBy(1);
                    count++;
                    if(count>=100){
                        handler.sendEmptyMessage(1);
                    }
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){

                    }
                }
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1) {
                progressDialog.dismiss();
                showSuccessTv.setVisibility(View.VISIBLE);
                showProgressBt.setVisibility(View.GONE);
            }

        }
    };



}
