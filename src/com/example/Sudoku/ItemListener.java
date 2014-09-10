/*
 * 
 * by zhuling
 * 
 */


package com.example.Sudoku;

import android.view.View;
import android.view.View.OnClickListener;

public class ItemListener implements OnClickListener
{
	private  MyTextView	lasTextView	= null;

	public  MyTextView getLasTextView()
	{
		return lasTextView;
	}

	public ItemListener()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View paramView)
	{
		// TODO Auto-generated method stub
		if (lasTextView != null)
		{
			lasTextView.changeColor();
		}

		MyTextView myTextView = (MyTextView) paramView;
		myTextView.changeColor();
		
		lasTextView = myTextView;
	}

}
