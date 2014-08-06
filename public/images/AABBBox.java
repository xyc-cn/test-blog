package com.itfarm.myandroid;

import java.util.ArrayList;

public class AABBBox 
{
	public float minX;
	public float maxX;
	public float minY;
	public float maxY;
	public float minZ;
	public float maxZ;
	
	boolean isStatic;//是否静止
	final float V_UNIT=0.01f;//判断是否相交的值
	
	public AABBBox(float minX,float maxX,float minY,float maxY,float minZ,float maxZ)
	{
		this.minX=minX;
		this.maxX=maxX;
		this.minY=minY;
		this.maxY=maxY;
		this.minZ=minZ;
		this.maxZ=maxZ;
	}
	
	public Thing goToCheck(ArrayList<Thing> allThings)
	{
		if(this.isStatic)
			return null;
		for(Thing thing:allThings)
		{			
			AABBBox rb=thing.aabbBox;
		if(rb!=null&&rb!=this)
			{			
				if(check(this,rb))
				{
					return thing;
				}
			}
		}
		return null;
	}
	
	public boolean check(AABBBox ra,AABBBox rb)
	{		
		float[] over=calOverTotal(ra,rb);
		return over[0]>V_UNIT&&over[1]>V_UNIT;
	}
	
	public float[] calOverTotal(AABBBox a,AABBBox b)
	{
		float xOver=calOverOne(a.maxX,a.minX,b.maxX,b.minX);
		float yOver=calOverOne(a.maxY,a.minY,b.maxY,b.minY);
		return new float[]{xOver,yOver};
	}
	
	public float calOverOne(float amax,float amin,float bmax,float bmin)
	{
		float minMax=0;
		float maxMin=0;
		if(amax<bmax)
		{
			minMax=amax;
			maxMin=bmin;
		}
		else 
		{
			minMax=bmax;
			maxMin=amin;
		}
		
		if(minMax>maxMin)
		{
			return minMax-maxMin;
		}
		else
		{
			return 0;
		}
	}
	
}
