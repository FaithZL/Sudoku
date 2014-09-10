/*
 * 
 * by zhuling
 * 
 */

package com.example.Sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView
{
	private int	number	= 0;

	private int	myId;

	private int	row		= 0;
	private int	col		= 0;

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCol()
	{
		return col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}

	private Paint		myPaint			= new Paint();

	private int			normalColor;
	private final int	selectedColor	= android.graphics.Color.YELLOW;
	private int			currentColor;
	private final int	numErrorColor	= android.graphics.Color.RED;
	private final int	numNormalColor	= android.graphics.Color.BLACK;

	private boolean		isRight			= true;

	public void setTextWithNum()
	{
		this.setText(number == 0 ? "" : "" + number);
	}

	public boolean getIsRight()
	{
		return isRight;
	}

	public void setIsRight(boolean isRight)
	{
		this.isRight = isRight;
	}

	public void setNormalColor()
	{
		if (col >= 3 && col <= 5 && row >= 0 && row <= 2)
		{
			normalColor = android.graphics.Color.LTGRAY;
			this.setBackgroundColor(normalColor);

		} else if (row >= 3 && row <= 5 && col >= 0 && col <= 2)
		{
			normalColor = android.graphics.Color.LTGRAY;
			this.setBackgroundColor(normalColor);

		} else if (row >= 3 && row <= 5 && col >= 6 && col <= 8)
		{
			normalColor = android.graphics.Color.LTGRAY;
			this.setBackgroundColor(normalColor);

		} else if (row >= 6 && row <= 8 && col >= 3 && col <= 5)
		{
			normalColor = android.graphics.Color.LTGRAY;
			this.setBackgroundColor(normalColor);

		} else
		{
			normalColor = android.graphics.Color.WHITE;
			this.setBackgroundColor(normalColor);
		}
	}

	// 未选中 ，选中的颜色切换
	public void changeColor()
	{
		if (currentColor == normalColor)
		{
			setBackgroundColor(selectedColor);

		} else if (currentColor == selectedColor)
		{
			setBackgroundColor(normalColor);
		}
	}

	public void setNumColor()
	{
		if (isRight)
		{
			setNumNormalColor();
		} else
		{
			setNumErrorColor();
		}
	}

	public void setNumNormalColor()
	{
		this.setTextColor(numNormalColor);
	}

	public void setNumErrorColor()
	{
		this.setTextColor(numErrorColor);
	}

	@Override
	public void setBackgroundColor(int color)
	{
		// TODO Auto-generated method stub
		super.setBackgroundColor(color);
		currentColor = color;
	}

	@Override
	public void setId(int id)
	{
		// TODO Auto-generated method stub
		super.setId(id);
		myId = id;
	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public MyTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		myPaint.setStrokeWidth(3);
		myPaint.setColor(android.graphics.Color.BLACK);

		canvas.drawLine(0, 0, 0, this.getHeight() - 1, myPaint);

		canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
				this.getHeight() - 1, myPaint);
	}

}
