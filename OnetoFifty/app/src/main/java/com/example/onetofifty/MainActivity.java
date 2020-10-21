package com.example.onetofifty;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static String TAG="MainActivity";
    View[] include=new View[2];
    TextView now_number;
    RelativeLayout[] btn=new RelativeLayout[25];
    TextView[] btn_text=new TextView[25];
    TextView successTime;
    RelativeLayout ok_btn;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        all_view();
        include[0].setVisibility(View.VISIBLE);
        include[1].setVisibility(View.GONE);
        setOnClick();
        okBtnClick();
        game();
    }

    HashMap<Integer, Integer> numMap1, numMap2;
    private void setRandomNumber(){
        numMap1=new HashMap<>();
        numMap2=new HashMap<>();

        Random random=new Random();
        HashMap<Integer, Integer> map=new HashMap<>();
        int randomNum, idx=0;
        while(idx<25){
            randomNum=random.nextInt(25);
            if(map.containsKey(randomNum)) continue;
            btn_text[idx].setText(String.valueOf(randomNum+1));
            numMap1.put(idx, randomNum+1);
            map.put(randomNum, idx++);
        }

        idx=0;
        map=new HashMap<>();
        // 26~50
        while(map.size()<25){
            randomNum=random.nextInt(25);
            if(map.containsKey(randomNum)) continue;
            numMap2.put(idx, randomNum+26);
            map.put(randomNum, idx++);
        }

    }

    int match;
    private void game(){
        setRandomNumber();
        next=false;
        match=1;
        now_number.setText(String.valueOf(match));
        chronometer.start();
    }

    boolean next;
    private void setOnClick(){
        for(int i=0;i<25;i++){
            final int btnIdx=i;
            btn[i].setOnClickListener(new View.OnClickListener(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view){
                    makeToast(String.valueOf(btnIdx));
                    //클릭한 버튼 숫자와 match가 같다면
                    int value=next? numMap2.getOrDefault(btnIdx, -1) : numMap1.getOrDefault(btnIdx,-1);
                    Log.i(TAG, value+" "+match);
                    if(value==match){
                        String nextNum=next? "" : String.valueOf(numMap2.get(btnIdx));
                        btn_text[btnIdx].setText(nextNum);
                        if(match==25) next=true;
                        setNow_number();
                    }
                    else{ // 틀린 숫자 클릭
                        int change=getResources().getColor(R.color.colorPrimary);
                        btn[btnIdx].setBackgroundColor(change); // 틀린 값 색깔 바꾸고
                        final int origin=getResources().getColor(R.color.colorBG);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn[btnIdx].setBackgroundColor(origin);
                            }
                        }, 200); // 250ms후 원래대로
                    }
                }
            });
        }
    }

    private void okBtnClick(){
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                include[0].setVisibility(View.VISIBLE);
                include[1].setVisibility(View.GONE);
                chronometer.setBase(SystemClock.elapsedRealtime());
                game();
            }
        });
    }

    private void setNow_number(){
        Log.i(TAG, "setNow_number()");
        if(match==50){
            chronometer.stop();
            successTime.setText(chronometer.getText());
            include[1].setVisibility(View.VISIBLE);
            include[0].setVisibility(View.GONE);
            return;
        }
        now_number.setText(String.valueOf(++match));
    }

    private void makeToast(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
    }

    private void all_view(){
        include[0]=findViewById(R.id.include_game_view);
        include[1]=findViewById(R.id.include_success_view);
        chronometer=(Chronometer)findViewById(R.id.chronometer);
        now_number=(TextView)findViewById(R.id.now_number);
        btn[0]=(RelativeLayout)findViewById(R.id.btn_1);
        btn[1]=(RelativeLayout)findViewById(R.id.btn_2);
        btn[2]=(RelativeLayout)findViewById(R.id.btn_3);
        btn[3]=(RelativeLayout)findViewById(R.id.btn_4);
        btn[4]=(RelativeLayout)findViewById(R.id.btn_5);
        btn[5]=(RelativeLayout)findViewById(R.id.btn_6);
        btn[6]=(RelativeLayout)findViewById(R.id.btn_7);
        btn[7]=(RelativeLayout)findViewById(R.id.btn_8);
        btn[8]=(RelativeLayout)findViewById(R.id.btn_9);
        btn[9]=(RelativeLayout)findViewById(R.id.btn_10);
        btn[10]=(RelativeLayout)findViewById(R.id.btn_11);
        btn[11]=(RelativeLayout)findViewById(R.id.btn_12);
        btn[12]=(RelativeLayout)findViewById(R.id.btn_13);
        btn[13]=(RelativeLayout)findViewById(R.id.btn_14);
        btn[14]=(RelativeLayout)findViewById(R.id.btn_15);
        btn[15]=(RelativeLayout)findViewById(R.id.btn_16);
        btn[16]=(RelativeLayout)findViewById(R.id.btn_17);
        btn[17]=(RelativeLayout)findViewById(R.id.btn_18);
        btn[18]=(RelativeLayout)findViewById(R.id.btn_19);
        btn[19]=(RelativeLayout)findViewById(R.id.btn_20);
        btn[20]=(RelativeLayout)findViewById(R.id.btn_21);
        btn[21]=(RelativeLayout)findViewById(R.id.btn_22);
        btn[22]=(RelativeLayout)findViewById(R.id.btn_23);
        btn[23]=(RelativeLayout)findViewById(R.id.btn_24);
        btn[24]=(RelativeLayout)findViewById(R.id.btn_25);
        btn_text[0]=(TextView)findViewById(R.id.btn_text_1);
        btn_text[1]=(TextView)findViewById(R.id.btn_text_2);
        btn_text[2]=(TextView)findViewById(R.id.btn_text_3);
        btn_text[3]=(TextView)findViewById(R.id.btn_text_4);
        btn_text[4]=(TextView)findViewById(R.id.btn_text_5);
        btn_text[5]=(TextView)findViewById(R.id.btn_text_6);
        btn_text[6]=(TextView)findViewById(R.id.btn_text_7);
        btn_text[7]=(TextView)findViewById(R.id.btn_text_8);
        btn_text[8]=(TextView)findViewById(R.id.btn_text_9);
        btn_text[9]=(TextView)findViewById(R.id.btn_text_10);
        btn_text[10]=(TextView)findViewById(R.id.btn_text_11);
        btn_text[11]=(TextView)findViewById(R.id.btn_text_12);
        btn_text[12]=(TextView)findViewById(R.id.btn_text_13);
        btn_text[13]=(TextView)findViewById(R.id.btn_text_14);
        btn_text[14]=(TextView)findViewById(R.id.btn_text_15);
        btn_text[15]=(TextView)findViewById(R.id.btn_text_16);
        btn_text[16]=(TextView)findViewById(R.id.btn_text_17);
        btn_text[17]=(TextView)findViewById(R.id.btn_text_18);
        btn_text[18]=(TextView)findViewById(R.id.btn_text_19);
        btn_text[19]=(TextView)findViewById(R.id.btn_text_20);
        btn_text[20]=(TextView)findViewById(R.id.btn_text_21);
        btn_text[21]=(TextView)findViewById(R.id.btn_text_22);
        btn_text[22]=(TextView)findViewById(R.id.btn_text_23);
        btn_text[23]=(TextView)findViewById(R.id.btn_text_24);
        btn_text[24]=(TextView)findViewById(R.id.btn_text_25);
        successTime=(TextView)findViewById(R.id.successTime);
        ok_btn=(RelativeLayout)findViewById(R.id.ok_btn);
    }
}