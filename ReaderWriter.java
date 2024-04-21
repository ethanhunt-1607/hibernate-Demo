import java.io.*;
import java.util.*;

class Resource
{
	int size=10;
	int i=0,j=-1;

	int arr[] = new int[size];

	public void write() throws Exception
	{
		while(true)
		{
			synchronized(this)
			{
/*				while(i==j)
				{
					wait();
				}
*/
				if(i==size)
				{
					System.out.println("Memory is Full ! ");
					break;
				}

				arr[i] = i;
				System.out.println("Writer write's : "+i);
				i++;
				notify();
				Thread.sleep(1000);
			}
	
		}
	}

	public void read() throws Exception
	{
		while(true)
		{
			synchronized(this)
			{
				j++;

				if(j==size)
				{
					System.out.println("Memory is Full, nothing to read");
					break;
				}
				while(j==i)
				{
					wait();
				}
				System.out.println("Reader Read's : "+arr[j]);
				notify();
				Thread.sleep(1000);
			}
	
		}
	}
}

class ReadThread extends Thread
{
	Resource res;

	public ReadThread(Resource res)
	{
		this.res = res;
	}

	public void run()
	{
		try
		{
			res.read();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

class WriteThread extends Thread
{
	Resource res;

	public WriteThread(Resource res)
	{
		this.res = res;
	}

	public void run()
	{
		try
		{
			res.write();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



class ReaderWriter
{
	public static void main(String[] args) throws Exception
	{
		Resource res = new Resource();

		WriteThread t1 = new WriteThread(res);
		ReadThread t2 = new ReadThread(res);

		t1.start();
		t2.start();
	}
}