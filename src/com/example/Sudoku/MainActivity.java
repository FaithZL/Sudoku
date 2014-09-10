/*
 * 
 * by zhuling
 * 
 */

package com.example.Sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.suduku.R;

public class MainActivity extends Activity implements OnClickListener
{
	private GridView		gridView;
	private MyAdapter		myAdapter;
	private Button			btn1;
	private Button			btn2;
	private Button			btn3;
	private Button			btn4;
	private Button			btn5;
	private Button			btn6;
	private Button			btn7;
	private Button			btn8;
	private Button			btn9;
	private Button			btnSubmit;
	private Button			btnC;
	private Button			btnClear;

	private Solver			solver;

	private ItemListener	itemListener	= new ItemListener();

	public void findView()
	{
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnC = (Button) findViewById(R.id.btnC);
		btnClear = (Button) findViewById(R.id.btnClear);

		gridView = (GridView) findViewById(R.id.gridView);
	}

	public void setListener()
	{
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		btnC.setOnClickListener(this);
		btnClear.setOnClickListener(this);

		for (int i = 0; i < 81; i++)
		{
			myAdapter.getItem(i).setOnClickListener(itemListener);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myAdapter = MyAdapter.getInstance(this);
		solver = Solver.getInstance(MainActivity.this);

		findView();

		setListener();

		gridView.setAdapter(myAdapter);

	}

	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		MyAdapter.setMyPointer(null);
		Solver.setmPointer(null);
		PositionStack.setPointer(null);
		super.onBackPressed();
	}

	public void setNumber(int num, MyTextView myTextView)
	{
		myTextView.setText(num == 0 ? "" : "" + num);

		myTextView.setNumber(num);

		MyTextView[][] arrayTV = myAdapter.getArrayTV();

		solver.dynamicCheck(arrayTV, num);

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				arrayTV[i][j].setNumColor();
			}
		}

	}

	public void funForSubmit()
	{
		MyTextView[][] arrayTV = myAdapter.getArrayTV();
		boolean tag = true;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				tag = arrayTV[i][j].getIsRight();
				if (!tag)
				{
					Toast.makeText(MainActivity.this, "输入有误，请重新输入", 0).show();
					return;
				}
			}
		}

		new Thread(new Runnable() // 运算量大，新开启一个线程执行
				{

					@Override
					public void run()
					{
						// TODO Auto-generated method stub
						solver.startSolve(myAdapter.getArrayTV());
					}
				}).start();

	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		MyTextView MTV = itemListener.getLasTextView();

		if (MTV == null)
		{
			return;
		}
		switch (v.getId())
		{
		case R.id.btnClear:
			myAdapter.clear();
			break;
		case R.id.btnSubmit:
			this.funForSubmit();
			break;
		case R.id.btn1:
			this.setNumber(1, MTV);
			break;
		case R.id.btn2:
			this.setNumber(2, MTV);
			break;
		case R.id.btn3:
			this.setNumber(3, MTV);
			break;
		case R.id.btn4:
			this.setNumber(4, MTV);
			break;
		case R.id.btn5:
			this.setNumber(5, MTV);
			break;
		case R.id.btn6:
			this.setNumber(6, MTV);
			break;
		case R.id.btn7:
			this.setNumber(7, MTV);
			break;
		case R.id.btn8:
			this.setNumber(8, MTV);
			break;
		case R.id.btn9:
			this.setNumber(9, MTV);
			break;
		case R.id.btnC:
			this.setNumber(0, MTV);
			break;
		default:
			break;
		}
	}

}
