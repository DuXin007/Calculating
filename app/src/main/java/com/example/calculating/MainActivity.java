package com.example.calculating;

import java.math.BigDecimal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		init();
	}

	//0-9
	Button btn0=null;
	Button btn1=null;
	Button btn2=null;
	Button btn3=null;
	Button btn4=null;
	Button btn5=null;
	Button btn6=null;
	Button btn7=null;
	Button btn8=null;
	Button btn9=null;
	//小数点
	Button dian=null;
	//等于
	Button equal=null;
	//加减乘除
	Button add=null;
	Button sub=null;
	Button mul=null;
	Button div=null;
	//删除
	Button delete=null;
	//退格
	Button back=null;
	//左右括号
	Button left=null;
	Button right=null;
	
	TextView tv=null;
	/*
	 * 细节性问题的规定：
	 * 1.运算符（+，-，*，/）和小数点连续着不能出现大于或等于两次！
	 * 2.小数点不能在连续数字之间出现大于或等于两次！
	 * 3.后为加或减时，前为乘或除，加括号！
	 * 4.结尾出现运算符一次进行忽略考虑，不予存储
	 * 5.按=后只允许结果一次！
	 * 6.出结果后，按加减乘除键可以以上次的值再次进行运算...按数字键和小数点键则是自动将上次值清除，从新输入运算！
	 * 7.退格时，按等于号后,退格不会出现原式子！
	 * 8.保证0在开始时被按多次只显示一次，存储一次，解决出现的0000,0123类似的问题！
	 * 9.先按减号时，默认为负号处理！
	 */
	double result=0,num1=0,num2=0,num3=0;
	//判断操作数！（1：代表+，2：代表-,3:代表*,4:代表/）
	char operator='+';
	//b1、b2、b3、b4分别代表上次出现的运算符为加、减、乘、除， b5、b6代表后为加或减时，前为乘或除！
	boolean b1=false,b2=false,b3=false,b4=false,b5=false,b6=false;
	//flag1代表除数不为0的所有事件！
	//flag2判断运算符（+，-，*，/）和小数点连续着不能出现大于或等于两次！
	//flag3判断小数点不能在连续数字之间出现大于或等于两次！
	//flag4判断按=后只允许结果一次！
	boolean flag1=false,flag2=false,flag3=false,flag4=false;
	//用于保存result的String类型
	String res;
	//用来保存是否有小数点，在退格时进行判断
	boolean Ispoint=false;
	//用数组模拟栈的存储
	char []a=new char[100];   //用于保存输入信息！
	//静态、与非静态的区别,temp用于保存临时的i
	static int i=0,count=0,temp=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//从布局中获得ID
		btn0=(Button) findViewById(R.id.btn0);
		btn1=(Button) findViewById(R.id.btn1);
		btn2=(Button) findViewById(R.id.btn2);
		btn3=(Button) findViewById(R.id.btn3);
		btn4=(Button) findViewById(R.id.btn4);
		btn5=(Button) findViewById(R.id.btn5);
		btn6=(Button) findViewById(R.id.btn6);
		btn7=(Button) findViewById(R.id.btn7);
		btn8=(Button) findViewById(R.id.btn8);
		btn9=(Button) findViewById(R.id.btn9);
		
		dian=(Button) findViewById(R.id.dian);
		add=(Button) findViewById(R.id.btn_add);
		sub=(Button) findViewById(R.id.btn_sub);
		mul=(Button) findViewById(R.id.btn_mul);
		div=(Button) findViewById(R.id.btn_div);
		
		equal=(Button) findViewById(R.id.dengyu);
		delete=(Button) findViewById(R.id.btn_delete);
		back=(Button) findViewById(R.id.btn_backspace);
		
		tv= (TextView) findViewById(R.id.result);
		
		
		//设置监听器
		btn0.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		
		dian.setOnClickListener(this);
		add.setOnClickListener(this);
		sub.setOnClickListener(this);
		mul.setOnClickListener(this);
		div.setOnClickListener(this);
		
		equal.setOnClickListener(this);
		delete.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	//计数器的初始化
	public void init(){
		tv.setText("0");
		i=0;
		b1=b2=b3=b4=b5=b6=false;
		count=0;
		num1=num2=num3=0;
		flag1=flag2=flag3=flag4=false;
	}
	
	//将结果显示在文本框中，并将文本框中的字符串存储到a初始化过的字符数组中,
	public void stringToArray(){
		init();
		res=String.valueOf(result);
		tv.setText(res);
		i=res.length();
		for(int t=0;t<i;t++)
			a[t]=res.charAt(t);
	}
	
	//后台运行处理在此函数中进行
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
				case R.id.btn0:
					pressNum("0");
					break;
				case R.id.btn1:
					pressNum("1");
					break;
				case R.id.btn2:
					pressNum("2");
					break;
				case R.id.btn3:
					pressNum("3");
					break;
				case R.id.btn4:
					pressNum("4");
					break;
				case R.id.btn5:
					pressNum("5");
					break;
				case R.id.btn6:
					pressNum("6");
					break;
				case R.id.btn7:
					pressNum("7");
					break;
				case R.id.btn8:
					pressNum("8");
				    break;
				case R.id.btn9:
					pressNum("9");
					break;
				case R.id.dian:
					pressPoint();
					break;
				case R.id.btn_add:
					pressOperator("+");
					break;
				case R.id.btn_sub:
					pressOperator("-");
					break;
				case R.id.btn_mul:
					pressOperator("*");
					break;
				case R.id.btn_div:
					pressOperator("/");
					break;
				case R.id.dengyu:
					pressEqual();
					break;
				case R.id.btn_delete:
					init();   
					break;
				case R.id.btn_backspace:
					pressBackSpace();
					break;
				default:
					break;
			}
		}
		
	//按1-9之间数字的操作！
	public void pressNum(String ch){
		flag2=false;
		if(flag4)  init();
		a[i++]=ch.charAt(0);
		if( a[0]=='0' && a[1]=='0' && i==2)  {  //解决多次重复出现0的事件 如00000
			i--; 	tv.setText("0");
	    } 
		else  if( a[0]=='0' && a[1]!='0' && i==2) { //解决首次出现0的事件 如023
			i--;  a[0]=a[1]; tv.setText(ch);
		}
		else if(i==1) 
			tv.setText(ch);
		else 
			tv.setText(tv.getText().toString()+ch);
	}
	
	//按小数点的操作
	public void pressPoint(){
		if(!flag2 && !flag3)
		{
			if(flag4)  init();
			a[i++]='.';
			if(i==1) { a[0]='0'; a[1]='.'; i=2; tv.setText("0."); }
			else tv.setText(tv.getText().toString()+".");
			flag2=true;
			flag3=true;
		}
	}
	
	//按运算符键的操作
	public void pressOperator(String ch){
		if(!flag2)
		{
			if(flag4)   stringToArray();//按加减乘除键可以以上次的值再次进行连算
			a[i++]=ch.charAt(0);
			if(i==1) {         
				if(a[0]!='-'){        //处理负号问题，先按减号时，默认为负号处理！
				a[0]='0'; a[1]=ch.charAt(0); i=2;
				tv.setText("0"+ch);
				}
				else  
					tv.setText(ch);      
			}
			else  {
				tv.setText(tv.getText().toString()+ch);
			}
			flag2=true;
			flag3=false;
		}
	}
	
	//按BackSpace键后的操作
	public void pressBackSpace(){
		if(i>1){  
		    i--;
		    temp=i;
		    init();
			for(int e=0;e<temp;e++){
				if(a[e]=='+' || a[e]=='-' || a[e]=='*' || a[e]=='/')
						pressOperator(String.valueOf(a[e]));  //
				else if(a[e]=='.')
					    pressPoint();
				else
					    pressNum(String.valueOf(a[e]));  //函数内部都涉及到i的变化
			}
		}
		else
			init();
	}
	
	//按=后的操作
	public void pressEqual(){
		//按=后只允许结果一次！
		if(!flag4){
		//模拟出栈的方法进行运算！
		for(int j=i-1;j>=0;j--){
			//结尾出现运算符一次进行忽略考虑
			if(j==i-1 && ( a[j]=='+' || a[j]=='-' || a[j]=='*' || a[j]=='/' || a[j]=='.'))   continue;
			//存取数据！
			if(a[j]!='+' && a[j]!='-' && a[j]!='*' && a[j]!='/'){
				if(a[j]=='.'){
					num1*=Math.pow(10, -count);
					count=0;
				}
				else{
					num1+=((int)(a[j])-48)*(Math.pow(10,count));
					count++;
				}
			}
			
			if( a[j]=='+' || a[j]=='-' || a[j]=='*' || a[j]=='/' || j==0){
			   if(b5) {  //后为加或减，前为乘，优先级
				   num1=num1*num3;
				   b5=false;
			   }
			   else if(b6)  {//后为加或减，前为除，优先级
				   if(num3!=0){
					   num1=num1/num3;
					   b6=false;
				   }
				   else{
					   flag1=true;
					   break;			//第一次
				   }
			   }
			   if(j==0){
				   if(a[j]=='-')  {//负数的处理！
					   num1*=-1;
				   }
				   if(b1)
					   num1+=num2;
				   else if(b2)
					   num1-=num2;
				   else if(b3)
					   num1*=num2;
				   else if(b4){
					   if(num2!=0)
						   	num1/=num2;
					   else{
						   flag1=true;
						   break;			//第二次
					   }
				   }
			   }
			}
			 
		    if(a[j]=='+'){
				count=0;
				if(!b1 && !b2 && !b3 && !b4){
					num2=num1;
				}
				else if(b1){
					num2=num1+num2;
				}	
				else if(b2){
					num2=num1-num2;
					b2=false;
				}
				else if(b3){
					num2=num1*num2;
					b3=false;
				}
				else if(b4){
					if(num2!=0){
						num2=num1/num2;  
						b4=false;
					}
					else{
						flag1=true;
						break;				//第三次
					}
				}
				b1=true;
				num1=0;
			}
			else if(a[j]=='-' && j!=0) { //确认不是负号！
				count=0;
				if(!b1 && !b2 && !b3 && !b4){
					num2=num1;
				}
				else if(b1){
					num2=num1-num2;   //注意！！8-8+8=8-(8-8)
					b1=false;
				}	
				else if(b2){
					num2=num1+num2;   //注意！！8-8-8=8-（8+8）
				}
				else if(b3){
					num2=num1*num2;
					b3=false;
				}
				else if(b4){
					if(num2!=0){
						num2=num1/num2;
						b4=false;
					}
					else{
						flag1=true;
						break;				//第四次
					}
				}
				b2=true;
				num1=0;
			}
			else if(a[j]=='*'){
				count=0;
				if(!b1 && !b2 && !b3 && !b4){
					num2=num1;
				}
				else if(b1){
					num3=num1;
					b5=true;
				}	
				else if(b2){
					num3=num1;
					b5=true;
			    }
				else if(b3){
					num2=num1*num2;
				}
				else if(b4){
					if(num2!=0){
						num2=num1/num2;
						b4=false;
					}
					else{
						flag1=true;
						break;				//第五次
					}
				}
				b3=true;
				num1=0;
			}
			else if(a[j]=='/'){
				count=0;
				if(!b1 && !b2 && !b3 && !b4){
					num2=num1;
				}
				else if(b1){
					num3=num1;
					b6=true;
				}	
				else if(b2){
					num3=num1;
					b6=true;
				}
				else if(b3){
					if(num2!=0){
						num2=num1/num2;   //注意！！8/8*8=8/（8/8）
						b3=false;
					}
					else{
						flag1=true;
						break;				//第六次
					}
				}
				else if(b4){
						num2=num1*num2;  //注意！！8/8/8=8/（8*8）
				}
				b4=true;
				num1=0;
			}	
		}
		if(!flag1){   //四舍五如，保留12位有效数字
			BigDecimal b=new  BigDecimal(num1);
			result=b.setScale(12,BigDecimal.ROUND_HALF_UP).doubleValue();
			stringToArray();   ////将结果显示在文本框中，并将文本框中的字符串存储到a初始化过的字符数组中,
		}
		else{
			tv.setText("除数不能为0！");
			flag1=false;
		}
		flag4=true;
	  }//按=后只允许结果一次的flag4的判断结束！
	}
}
