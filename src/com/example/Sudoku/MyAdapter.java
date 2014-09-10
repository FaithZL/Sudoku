/*
 * 
 * by zhuling
 * 
 */

package com.example.Sudoku;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter
{
	private Context				context		= null;

	private MyTextView[][]		arrayTV		= new MyTextView[9][9];

	private Solver				solver;

	private static MyAdapter	myPointer	= null;

	public static MyAdapter getMyPointer()
	{
		return myPointer;
	}

	public static void setMyPointer(MyAdapter myPointer)
	{
		MyAdapter.myPointer = myPointer;
	}

	private int	position	= 0;

	public MyTextView[][] getArrayTV()
	{
		return arrayTV;
	}

	public void clear()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				arrayTV[i][j].setText("");
				arrayTV[i][j].setNumber(0);
				arrayTV[i][j].setIsRight(true);
			}
		}
	}

	public int getPosition()
	{
		return position;
	}

	public static MyAdapter getInstance(Context context)
	{
		if (myPointer == null)
		{
			myPointer = new MyAdapter(context);
		}

		return myPointer;
	}

	private MyAdapter(Context context)
	{
		// TODO Auto-generated constructor stub
		this.context = context;

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				arrayTV[i][j] = new MyTextView(context, null);
				arrayTV[i][j].setNumber(0);
				arrayTV[i][j].setId(i * 9 + j);
				arrayTV[i][j].setRow(i);
				arrayTV[i][j].setCol(j);
			}
		}
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return 81;
	}

	@Override
	public MyTextView getItem(int position)
	{
		// TODO Auto-generated method stub
		int row = position / 9;
		int col = position % 9;
		return arrayTV[row][col];
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public MyTextView getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		int row = position / 9;
		int col = position % 9;

		MyTextView tv = arrayTV[row][col];

		tv.setGravity(Gravity.CENTER);

		tv.setTextSize(29);

		tv.setNormalColor();

		tv.setText(tv.getNumber() == 0 ? "" : "" + tv.getNumber());

		return tv;
	}

}
