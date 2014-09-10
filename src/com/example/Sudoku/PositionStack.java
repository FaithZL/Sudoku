/*
 * 
 * ·ÏÆú
 * 
 * 
 */

package com.example.Sudoku;

class PositionValue
{
	public int	row	= 9;
	public int	col	= 9;

	public PositionValue(int row, int col)
	{
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
	}
}

public class PositionStack extends Object
{
	private PositionValue				arrayPosition[]	= new PositionValue[82];

	private int						stackPointer	= 0;

	private static PositionStack	Pointer			= null;

	public boolean push(PositionValue value)
	{
		if (stackPointer < 81)
		{
			arrayPosition[stackPointer++] = value;
			return true;
		}
		return false;
	}

	public PositionValue pop()
	{
		if (stackPointer > 0)
		{
			return arrayPosition[--stackPointer];
		}
		return null;
	}

	public static void setPointer(PositionStack Pointer)
	{
		PositionStack.Pointer = Pointer;
	}

	public static PositionStack getInstance()
	{
		if (Pointer == null)
		{
			Pointer = new PositionStack();
		}
		return Pointer;
	}

	private PositionStack()
	{

	}

}
