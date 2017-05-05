package com.example.awey.mycalculator;


import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener {
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
    Button btn_equal; //等号

    Button btn_clear; //清除
    Button btn_del; //删除
    EditText box_input; //显示框

    Boolean needclear;

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
        box_input = (EditText) findViewById(R.id.box_input);

        //实例化输入框
        box_input = (EditText) findViewById(R.id.box_input);

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
        btn_equal.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
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

                box_input.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                box_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_equal:
                getResult();
                break;
            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    box_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_clear:
                box_input.setText("");
                break;
        }
    }

    /**
     * 获取计算结果
     */
    private void getResult() {
        needclear = true;
        String exp = box_input.getText().toString();
        double r = 0;
        int space = exp.indexOf(' ');//用于搜索空格位置
        String s1 = exp.substring(0, space);//s1用于保存第一个运算数
        String op = exp.substring(space + 1, space + 2);//op用于保存运算符
        String s2 = exp.substring(space + 3);//s2用于保存第二个运算数
        double arg1 = Double.parseDouble(s1);//将运算数从string转换为Single
        double arg2 = Double.parseDouble(s2);
        if (op.equals("＋")) {
            r = arg1 + arg2;
        } else if (op.equals("－")) {
            r = arg1 - arg2;
        } else if (op.equals("×")) {
            r = arg1 * arg2;
        } else if (op.equals("÷")) {
            if (arg2 == 0) {
                r = 0;
            } else {
                r = arg1 / arg2;
            }
        }
        if (!s1.contains(".") && !s2.contains(".")) {
            int result = (int) r;
            box_input.setText(result + "");
        } else {
            box_input.setText(r + "");
        }
    }
}
