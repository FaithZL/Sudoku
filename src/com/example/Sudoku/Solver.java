/*
 * 
 * by zhuling
 * 
 */

package com.example.Sudoku;

import android.app.Activity;
import android.content.Context;

public class Solver extends Object
{
	private static Solver	mPointer	= null;

	private Context			context;

	public boolean isPass(MyTextView[][] arrayTV, int row, int col) // 检查行列
	{
		int temp = arrayTV[row][col].getNumber();
		boolean right = true;

		for (int i = 0; i < 9; i++)
		{
			if (i != col && arrayTV[row][i].getNumber() == temp && temp != 0)
			{
				right = false;
				arrayTV[row][col].setIsRight(right);
				return right;
			}
			if (i != row && arrayTV[i][col].getNumber() == temp && temp != 0)
			{
				right = false;
				arrayTV[row][col].setIsRight(right);
				return right;
			}
		}

		int startRow = row / 3 * 3;

		int startCol = col / 3 * 3;

		for (int i = startRow; i < startRow + 3; i++)
		{
			for (int j = startCol; j < startCol + 3; j++)
			{
				if (temp == arrayTV[i][j].getNumber() && (i != row || j != col)
						&& temp != 0)
				{
					right = false;
					arrayTV[row][col].setIsRight(right);
					return right;
				}
			}
		}

		arrayTV[row][col].setIsRight(right);
		return right;
	}

	public boolean dynamicCheck(MyTextView[][] arrayTV, int value)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				isPass(arrayTV, i, j);
			}
		}
		return true;
	}

	public void startSolve(MyTextView[][] arrayTV)
	{
		fillIn(0, arrayTV);
	}

	class MyRunnable implements Runnable
	{
		private MyTextView[][]	arrayTV	= null;

		public MyRunnable(MyTextView[][] arrayTV)
		{
			// TODO Auto-generated constructor stub
			this.arrayTV = arrayTV;
		}

		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			for (int i = 0; i < 81; i++)
			{
				int r = i / 9;
				int c = i % 9;
				arrayTV[r][c].setTextWithNum();
			}
		}

	}

	public void setTextWithNumAll(MyTextView[][] arrayTV)
	{
		MyRunnable myRunnable = new MyRunnable(arrayTV); // 这个函数涉及到UI的操作，所以必须放在UI线程（主线程）里执行

		((Activity) context).runOnUiThread(myRunnable);
	}

	public void fillIn(int position, MyTextView[][] arrayTV)
	{
		int row = position / 9;
		int col = position % 9;

		if (arrayTV[row][col].getNumber() != 0)
		{
			if (position == 80)
			{
				this.setTextWithNumAll(arrayTV); // 将数字填入81个框内
				return;
			}
			fillIn(position + 1, arrayTV);
			return;
		}

		for (int i = 1; i <= 9; i++)
		{
			arrayTV[row][col].setNumber(i);
			if (isPass(arrayTV, row, col))
			{
				if (position == 80)
				{
					this.setTextWithNumAll(arrayTV); // 将数字填入81个框内
					arrayTV[row][col].setNumber(0);
					return;
				}
				fillIn(position + 1, arrayTV);
			} else
			{
				arrayTV[row][col].setNumber(0);
			}
		}
		arrayTV[row][col].setNumber(0);
	}

	public static Solver getInstance(Context context)
	{
		if (mPointer == null)
		{
			mPointer = new Solver(context);
		}
		return mPointer;
	}

	public static void setmPointer(Solver mPointer)
	{
		Solver.mPointer = mPointer;
	}

	private Solver(Context context)
	{
		// TODO Auto-generated constructor stub
		this.context = context;
	}
}
