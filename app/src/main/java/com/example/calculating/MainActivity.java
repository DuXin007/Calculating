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
	//С����
	Button dian=null;
	//����
	Button equal=null;
	//�Ӽ��˳�
	Button add=null;
	Button sub=null;
	Button mul=null;
	Button div=null;
	//ɾ��
	Button delete=null;
	//�˸�
	Button back=null;
	//��������
	Button left=null;
	Button right=null;
	
	TextView tv=null;
	/*
	 * ϸ��������Ĺ涨��
	 * 1.�������+��-��*��/����С���������Ų��ܳ��ִ��ڻ�������Σ�
	 * 2.С���㲻������������֮����ִ��ڻ�������Σ�
	 * 3.��Ϊ�ӻ��ʱ��ǰΪ�˻���������ţ�
	 * 4.��β���������һ�ν��к��Կ��ǣ�����洢
	 * 5.��=��ֻ������һ�Σ�
	 * 6.������󣬰��Ӽ��˳����������ϴε�ֵ�ٴν�������...�����ּ���С����������Զ����ϴ�ֵ����������������㣡
	 * 7.�˸�ʱ�������ںź�,�˸񲻻����ԭʽ�ӣ�
	 * 8.��֤0�ڿ�ʼʱ�������ֻ��ʾһ�Σ��洢һ�Σ�������ֵ�0000,0123���Ƶ����⣡
	 * 9.�Ȱ�����ʱ��Ĭ��Ϊ���Ŵ���
	 */
	double result=0,num1=0,num2=0,num3=0;
	//�жϲ���������1������+��2������-,3:����*,4:����/��
	char operator='+';
	//b1��b2��b3��b4�ֱ�����ϴγ��ֵ������Ϊ�ӡ������ˡ����� b5��b6�����Ϊ�ӻ��ʱ��ǰΪ�˻����
	boolean b1=false,b2=false,b3=false,b4=false,b5=false,b6=false;
	//flag1���������Ϊ0�������¼���
	//flag2�ж��������+��-��*��/����С���������Ų��ܳ��ִ��ڻ�������Σ�
	//flag3�ж�С���㲻������������֮����ִ��ڻ�������Σ�
	//flag4�жϰ�=��ֻ������һ�Σ�
	boolean flag1=false,flag2=false,flag3=false,flag4=false;
	//���ڱ���result��String����
	String res;
	//���������Ƿ���С���㣬���˸�ʱ�����ж�
	boolean Ispoint=false;
	//������ģ��ջ�Ĵ洢
	char []a=new char[100];   //���ڱ���������Ϣ��
	//��̬����Ǿ�̬������,temp���ڱ�����ʱ��i
	static int i=0,count=0,temp=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//�Ӳ����л��ID
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
		
		
		//���ü�����
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
	
	//�������ĳ�ʼ��
	public void init(){
		tv.setText("0");
		i=0;
		b1=b2=b3=b4=b5=b6=false;
		count=0;
		num1=num2=num3=0;
		flag1=flag2=flag3=flag4=false;
	}
	
	//�������ʾ���ı����У������ı����е��ַ����洢��a��ʼ�������ַ�������,
	public void stringToArray(){
		init();
		res=String.valueOf(result);
		tv.setText(res);
		i=res.length();
		for(int t=0;t<i;t++)
			a[t]=res.charAt(t);
	}
	
	//��̨���д����ڴ˺����н���
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
		
	//��1-9֮�����ֵĲ�����
	public void pressNum(String ch){
		flag2=false;
		if(flag4)  init();
		a[i++]=ch.charAt(0);
		if( a[0]=='0' && a[1]=='0' && i==2)  {  //�������ظ�����0���¼� ��00000
			i--; 	tv.setText("0");
	    } 
		else  if( a[0]=='0' && a[1]!='0' && i==2) { //����״γ���0���¼� ��023
			i--;  a[0]=a[1]; tv.setText(ch);
		}
		else if(i==1) 
			tv.setText(ch);
		else 
			tv.setText(tv.getText().toString()+ch);
	}
	
	//��С����Ĳ���
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
	
	//����������Ĳ���
	public void pressOperator(String ch){
		if(!flag2)
		{
			if(flag4)   stringToArray();//���Ӽ��˳����������ϴε�ֵ�ٴν�������
			a[i++]=ch.charAt(0);
			if(i==1) {         
				if(a[0]!='-'){        //���������⣬�Ȱ�����ʱ��Ĭ��Ϊ���Ŵ���
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
	
	//��BackSpace����Ĳ���
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
					    pressNum(String.valueOf(a[e]));  //�����ڲ����漰��i�ı仯
			}
		}
		else
			init();
	}
	
	//��=��Ĳ���
	public void pressEqual(){
		//��=��ֻ������һ�Σ�
		if(!flag4){
		//ģ���ջ�ķ����������㣡
		for(int j=i-1;j>=0;j--){
			//��β���������һ�ν��к��Կ���
			if(j==i-1 && ( a[j]=='+' || a[j]=='-' || a[j]=='*' || a[j]=='/' || a[j]=='.'))   continue;
			//��ȡ���ݣ�
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
			   if(b5) {  //��Ϊ�ӻ����ǰΪ�ˣ����ȼ�
				   num1=num1*num3;
				   b5=false;
			   }
			   else if(b6)  {//��Ϊ�ӻ����ǰΪ�������ȼ�
				   if(num3!=0){
					   num1=num1/num3;
					   b6=false;
				   }
				   else{
					   flag1=true;
					   break;			//��һ��
				   }
			   }
			   if(j==0){
				   if(a[j]=='-')  {//�����Ĵ���
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
						   break;			//�ڶ���
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
						break;				//������
					}
				}
				b1=true;
				num1=0;
			}
			else if(a[j]=='-' && j!=0) { //ȷ�ϲ��Ǹ��ţ�
				count=0;
				if(!b1 && !b2 && !b3 && !b4){
					num2=num1;
				}
				else if(b1){
					num2=num1-num2;   //ע�⣡��8-8+8=8-(8-8)
					b1=false;
				}	
				else if(b2){
					num2=num1+num2;   //ע�⣡��8-8-8=8-��8+8��
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
						break;				//���Ĵ�
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
						break;				//�����
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
						num2=num1/num2;   //ע�⣡��8/8*8=8/��8/8��
						b3=false;
					}
					else{
						flag1=true;
						break;				//������
					}
				}
				else if(b4){
						num2=num1*num2;  //ע�⣡��8/8/8=8/��8*8��
				}
				b4=true;
				num1=0;
			}	
		}
		if(!flag1){   //�������磬����12λ��Ч����
			BigDecimal b=new  BigDecimal(num1);
			result=b.setScale(12,BigDecimal.ROUND_HALF_UP).doubleValue();
			stringToArray();   ////�������ʾ���ı����У������ı����е��ַ����洢��a��ʼ�������ַ�������,
		}
		else{
			tv.setText("��������Ϊ0��");
			flag1=false;
		}
		flag4=true;
	  }//��=��ֻ������һ�ε�flag4���жϽ�����
	}
}
