
public class DataModel
{
	char[][] arr = new char[128][128];
	out D = new out("DataModel");
	static DataModel ServerModel;
	
	public DataModel()
	{
		if (ServerModel == null)
			ServerModel = this;
	}
	
	public boolean update(char a, int r, int c)
	{
		//Just update the array. The server thread will take care of asking it to write to file.
		if (r > arr.length || c > arr[0].length)
		{
			D.warn("Attempting to update outside of bounds. Req: [" + r + "][" + c + "]");
			return false;
		}
		
		arr[r][c] = a;
		
		return true;
	}
	
	public char get(int r, int c)
	{
		
		return arr[r][c];
	}
	
	public DataModel getDiff(DataModel d)
	{
		DataModel temp = new DataModel();
		
		for (int r = 0; r < 128; r++)
		{
			for (int c = 0; c < 128; c++)
			{
				if (get(r,c) != d.get(r, c))
				{
					temp.update(d.get(r,c), r, c);
				}
			}
		}
		
		return temp;
	}
	
	public DataModel getDiff(String j)
	{
		DataModel temp = new DataModel();
		int offset = 0;
		for (int r = 0; r < 128; r++)
		{
			for (int c = 0; c < 128; c++)
			{
				temp.update(j.charAt(offset), r, c);
				offset++;
			}
			offset++; //Gotta skip the \n character
		}
		return getDiff(temp);
		
	}
	
	public String toString()
	{
		String sb = "";
		for (int r = 0; r < arr.length; r++)
			for (int c = 0; c < arr[0].length; c++)
			{
				if (arr[r][c] != (char)0)
				{
					sb = sb + (arr[r][c]) + (char)207;
					sb = sb + (r) + (char)207;
					sb = sb + (c) + (char)207;
				}
			}
		
		return sb;
		
	}
}
