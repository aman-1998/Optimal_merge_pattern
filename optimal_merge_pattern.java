import java.util.*;
import java.lang.*;
import java.io.*;

class optimal
{
	int heap_size,sum=0;
	node min1,min2;
	optimal(int i)
	{
		this.heap_size=i;
	}
	node optimal_merge(ArrayList<node> A)
	{
		node temp;
		A=build_heap(A);
		while(heap_size>1)
		{
			A=delete_min(A,1);
			A=delete_min(A,2);
			temp=new node(min1,min1.record+min2.record,min2);
			A=insert(A,temp);
		}
		return A.get(1);
	}
	ArrayList<node> build_heap(ArrayList<node> A)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			A=min_heapify(A,i);
		return A;
	}
	ArrayList<node> min_heapify(ArrayList<node> A,int i)
	{
		int l,r,smallest;
		node temp;
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && A.get(l).record<A.get(i).record)
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && A.get(r).record<A.get(smallest).record)
			smallest=r;
		if(smallest!=i)
		{
			temp=A.get(i);
			A.set(i,A.get(smallest));
			A.set(smallest,temp);
			A=min_heapify(A,smallest);
		}
		return A;
	}
	ArrayList<node> delete_min(ArrayList<node> A,int flag)
	{
		if(flag==1)
		{
			min1=A.get(1);
			A.set(1,A.get(heap_size));
			heap_size--;
			A=min_heapify(A,1);
		}
		else
			min2=A.get(1);
		return A;
	}
	ArrayList<node> insert(ArrayList<node> A,node temp)
	{
		A.set(1,temp);
		A=min_heapify(A,1);
		return A;
	}
	void movements(node root,int len)
	{
		if(root.left!=null)
			movements(root.left,len+1);
		if(root.right!=null)
			movements(root.right,len+1);
		if(root.left==null && root.right==null)
			sum=sum+len*(root.record);
	}
	public static void main(String[] args)
	{
		int i=1,r,v;
		Scanner in=new Scanner(System.in);
		ArrayList<node> A=new ArrayList<node>();
		A.add(null);
		do
		{
			System.out.print("Enter no. of records for file"+(i++)+" : ");
			r=in.nextInt();
			A.add(new node(null,r,null));
			System.out.print("Enter \'1\' to continue and any other key to quit : ");
			v=in.nextInt();
		}
		while(v==1);
		optimal x=new optimal(A.size()-1);
		node root=x.optimal_merge(A);
		x.movements(root,0);
		System.out.print("\nMin movements = "+x.sum+"\n");
	}
}
class node
{
	int record;
	node left,right;
	node(node l,int record,node r)
	{
		left=l;
		right=r;
		this.record=record;
	}
}