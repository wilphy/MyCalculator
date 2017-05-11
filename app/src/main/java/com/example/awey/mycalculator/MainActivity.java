package com.example.awey.mycalculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MotionEvent;
import android.text.TextUtils;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_0; //数字0
    Button btn_1; //数字1
    Button btn_2; //数字2
    Button btn_3; //数字3
    Button btn_4; //数字4
    Button btn_5; //数字5
    Button btn_6; //数字6
    Button btn_7; //数字7
    Button btn_8; //数字8
    Button btn_9; //数字9
    Button btn_dot; //小数点

    Button btn_plus; //加号
    Button btn_minus; //减号
    Button btn_multiply; //乘号
    Button btn_divide; //除号

    Button btn_clear; //清除
    Button btn_del; //删除
    Button btn_equal; //等号

    EditText box_input; //显示框

    Boolean clear_flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //实例化按钮
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_dot = (Button) findViewById(R.id.btn_dot);// 小数点

        btn_divide = (Button) findViewById(R.id.btn_divide);// 除以
        btn_multiply = (Button) findViewById(R.id.btn_multiply);// 乘以
        btn_minus = (Button) findViewById(R.id.btn_minus);// 减
        btn_plus = (Button) findViewById(R.id.btn_plus);// 加
        btn_equal = (Button) findViewById(R.id.btn_equal);// 等于

        btn_clear = (Button) findViewById(R.id.btn_clear); //清除
        btn_del = (Button) findViewById(R.id.btn_del); //删除

        //实例化输入框
        box_input = (EditText) findViewById(R.id.box_input);


        //给按钮设置点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_dot.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);

        btn_equal.setOnClickListener(this);

        //为editText添加onTouchListener监听事件
        //result.setOnTouchListener(this);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        //取输入框的内容
        String str = box_input.getText().toString();

        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
                if(clear_flag){
                    clear_flag=false;
                    str="";    //进行下一轮计算前将输入框设置为空
                    box_input.setText("");
                }
                //点击数字按钮或小数点就将其添加到输入框里
                box_input.setText(str+((Button)v).getText());
                break;

            //加减乘除四则运算
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if (str.equals("")) {
                    Toast.makeText(MainActivity.this, "不具备运算",Toast.LENGTH_LONG).show();
                    box_input.setText("");
                }
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    box_input.setText("");
                }
                //将点击的运算符添加到输入框前后有“ ”用于区别
                box_input.setText(str + " " + ((Button) v).getText() + " ");
                break;

            //删除
            case R.id.btn_del:
                if (clear_flag) {
                    clear_flag = false;
                    str="";    //进行下一轮计算前将输入框设置为空
                    box_input.setText("");
                }
                else if (str!=null&& !str.equals("")) {
                    box_input.setText(str.substring(0,str.length()-1));
                }
                break;

            //清除
            case R.id.btn_clear:
                clear_flag=false;
                str="";    //进行下一轮计算前将输入框设置为空
                box_input.setText("");
                break;

            //运算结果
            case R.id.btn_equal:
                getResult();
                break;


        }
    }


    /**
     * 获取计算结果
     */

    private void getResult() {
        String exp = box_input.getText().toString();
        if(clear_flag){
            clear_flag=false;
            return;
        }
        if (exp == null || exp.equals("")) {
            return;
        }
        //没有输入运算符（运算符前后都手动加入了空格）
        if (!exp.contains(" ")) {//如果不包含空格（运算符前面有空格），直接返回（比如点了数字，没有运算符）
            return;
        }
        clear_flag = true;
        double result = 0;
        String s1 = exp.substring(0, exp.indexOf(" "));//运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//运算符
        String s2 = exp.substring(exp.indexOf(" ") + 3);//运算符后面的字符串
        //s1、s2非空
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("＋")) {
                result = d1 + d2;
            } else if (op.equals("－")) {
                result = d1 - d2;
            } else if (op.equals("×")) {
                result = d1 * d2;
            } else if (op.equals("÷")) {
                if (d2 == 0) {
                    Toast.makeText(MainActivity.this, "除数不能为0！！！", Toast.LENGTH_LONG).show();
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            }
            //判断是否有小数点或除号
            if (!s1.contains(".") && !s2.contains(".")&&!op.equals("÷")) {
                int r = (int)result;    //把result强制转化为int类型
                box_input.setText(r + "");
            } else {
                box_input.setText(result + "");
            }
        }else if(!s1.equals("") && s2.equals("")){
            Toast.makeText(MainActivity.this, "不具备运算",Toast.LENGTH_LONG).show();
            box_input.setText(exp);
        }
        //s1为空，s2非空
        else if(s1.equals("") && !s2.equals("")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("＋")) {
                result = 0 + d2;
            } else if (op.equals("－")) {
                result = 0 - d2;
            } else if (op.equals("×")) {
                result = 0 ;
            } else if (op.equals("÷")) {
                result = 0;
            }
            //判断是否有小数点或除号
            if (!s1.contains(".") && !s2.contains(".")&&!op.equals("÷")) {
                int r = (int)result;    //把result强制转化为int类型
                box_input.setText(r + "");
            } else {
                box_input.setText(result + "");
            }
        }
        //s1、s2都是空
        else{
            box_input.setText("");
        }
    }
}

