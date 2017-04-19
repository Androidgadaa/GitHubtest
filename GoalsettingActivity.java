package com.xiaoyu.newwhellchairui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoyu.newwhellchairui.view.widget.NestedScrollView;
import com.xiaoyu.newwhellchairui.view.widget.WheelView;

import java.util.ArrayList;

/**
 * Created by xiaoyu on 2017/4/11.
 */
public class GoalsettingActivity extends BaseActivity {
    private LinearLayout mLl_turns;
    private TextView mTv_gola_numberofturns;
    private LinearLayout mLl_speed;
    private TextView mTv_gola_speed;
    private TextView mTv_gola_distanced;
    private TextView mTv_gola_calorie;
    private Button mBtn_gola_confirm;
    private Button mBtn_gola_cancel;
    private WheelView myWheelView;
    private TextView mTv_d;
    private TextView mTv_km;
    private ImageView mIv_leftarrow;
    private NestedScrollView scroll;
    private ImageView mImg_tuensbelow;
    private ImageView mImg_speedbelow;
    private TextView mTv_turns;
    private String turns;
    private String speednumber;
    private boolean turnhas;
    private boolean speedhas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("目标设定");
        setsetButton(View.GONE);
        setbattery(View.GONE);
        bindViews();
        setMyWhellview();
        img_base_chair.setImageResource(R.drawable.back);



    }
    private void bindViews() {

        mLl_turns = (LinearLayout) findViewById(R.id.ll_turns);
        mTv_gola_numberofturns = (TextView) findViewById(R.id.tv_gola_numberofturns);
        mLl_speed = (LinearLayout) findViewById(R.id.ll_speed);
        mTv_gola_speed = (TextView) findViewById(R.id.tv_gola_speed);
        mTv_gola_distanced = (TextView) findViewById(R.id.tv_gola_distanced);
        mTv_gola_calorie = (TextView) findViewById(R.id.tv_gola_calorie);
        myWheelView = (WheelView) findViewById(R.id.my_wheelview);
        mBtn_gola_confirm = (Button) findViewById(R.id.btn_gola_confirm);
        mBtn_gola_cancel = (Button) findViewById(R.id.btn_gola_cancel);
        mTv_d = (TextView) findViewById(R.id.tv_d);
        mTv_km = (TextView) findViewById(R.id.tv_km);
        mIv_leftarrow = (ImageView) findViewById(R.id.iv_leftarrow);
        scroll = (NestedScrollView) findViewById(R.id.scroll);
        mImg_tuensbelow = (ImageView) findViewById(R.id.img_tuensbelow);
        mImg_speedbelow = (ImageView) findViewById(R.id.img_speedbelow);
        mTv_turns = (TextView) findViewById(R.id.tv_turns);
        mLl_turns.setOnClickListener(this);
        mLl_speed.setOnClickListener(this);
        mBtn_gola_confirm.setOnClickListener(this);
        mBtn_gola_cancel.setOnClickListener(this);
    }
    boolean isclickturns ;
    boolean isclickspeed ;
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_turns :
                myWheelView.setWheelData(tuensArrays());
                setspeedicon(View.GONE);
                setturnsicon(View.VISIBLE);
                if(isclickturns) {
                    isclickturns = false ;
                    isclickspeed = false ;
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    scroll.setVisibility(View.INVISIBLE);
                }
                else {
                    isclickturns = true ;
                    isclickspeed = true ;
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    mImg_tuensbelow.setVisibility(View.VISIBLE);
                    scroll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_speed:
                myWheelView.setWheelData(createArrays());
                setspeedicon(View.VISIBLE);
                setturnsicon(View.GONE);
                if(isclickspeed) {
                    isclickturns = false ;
                    isclickspeed = false;
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    scroll.setVisibility(View.INVISIBLE);
                }
                else {
                    isclickturns = true ;
                    isclickspeed = true ;
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    mImg_speedbelow.setVisibility(View.VISIBLE);
                    scroll.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_gola_confirm:
                if(View.VISIBLE == mImg_tuensbelow.getVisibility() ) {
                    ArrayList<String> strings = tuensArrays();
                     turns  = strings.get(myWheelView.getCurrentPosition());
                    mTv_gola_numberofturns.setText(turns);
                    mTv_gola_numberofturns.setVisibility(View.VISIBLE);
                    turnhas = true ;
                    isclickturns = false ;
                    isclickspeed = false ;
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    scroll.setVisibility(View.INVISIBLE);
                    if(true == speedhas) {
                        setdisandcal(turns,speednumber);
                    }
                }
                else if(View.VISIBLE == mImg_speedbelow.getVisibility()) {
                    ArrayList<String> arrays = createArrays();
                    String speed = arrays.get(myWheelView.getCurrentPosition());
                    int i = speed.lastIndexOf(" ");
                    speednumber  = speed.substring(i+1 , speed.length());
                    mTv_gola_speed.setText(speednumber);
                    mTv_gola_speed.setVisibility(View.VISIBLE);
                    speedhas = true ;
                    isclickturns = false ;
                    isclickspeed = false;
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    scroll.setVisibility(View.INVISIBLE);
                    if(true == turnhas) {
                        setdisandcal(turns,speednumber);
                    }
                }
                //都设置好后点击确定的效果  可执行传数据等操作
                else if( View.VISIBLE == mTv_gola_numberofturns.getVisibility() && View.VISIBLE == mTv_gola_speed.getVisibility()) {
                    Toast.makeText(GoalsettingActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                        finish();
                }
                break;
                case R.id.img_base_chair:
                    finish();
                break;
                case R.id.btn_gola_cancel:
                    isclickturns = false ;
                    isclickspeed = false ;
                    scroll.setVisibility(View.INVISIBLE);
                    mImg_speedbelow.setVisibility(View.INVISIBLE);
                    mImg_tuensbelow.setVisibility(View.INVISIBLE);
                    break;

        }

    }
    //设置距离和卡路里的方法 测试随便写的算数方法   自己更改就好
    private  void setdisandcal(String turns ,String speed){
        int parseInttuens = Integer.parseInt(turns);
        int parseIntspeed = Integer.parseInt(speed);
        mTv_gola_distanced.setText(parseInttuens + parseIntspeed + "");
        mTv_gola_calorie.setText(parseInttuens * parseIntspeed + "");
        mTv_gola_calorie.setVisibility(View.VISIBLE);
        mTv_gola_distanced.setVisibility(View.VISIBLE);
    }

    private void  setspeedicon ( int visibility){
        mTv_d.setVisibility(visibility);
        mTv_km.setVisibility(visibility);
        mIv_leftarrow.setVisibility(visibility);
    }
    private void setturnsicon( int visbility){
        mTv_turns.setVisibility(visbility);
    }

    private void setMyWhellview() {
        myWheelView.setWheelAdapter(new MyWheelAdapter(this));
        myWheelView.setWheelSize(3);
        myWheelView.setSkin(WheelView.Skin.Holo);
        myWheelView.setWheelData(createArrays());
        myWheelView.setSelection(2);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.backgroundColor = Color.WHITE;
        style.textColor = Color.DKGRAY;
        style.selectedTextColor = Color.RED;
        style.selectedTextZoom = (float) 1.2;
        myWheelView.setStyle(style);
    }
    //速度滚轮添加数据
    private ArrayList<String> createArrays() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i < 21; i++) {
            list.add(i + "                                                         " + 5 * i);
        }
        return list;
    }
    //圈数滚轮添加数据
    private ArrayList<String> tuensArrays() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i < 21; i++) {
            list.add(100 * i + "");
        }
        return list;
    }

    @Override
    public View getContentView() {
        return View.inflate(GoalsettingActivity.this,R.layout.activity_golasetting,null);
    }
}
