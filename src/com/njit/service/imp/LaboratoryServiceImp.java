package com.njit.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Experiment;
import com.njit.domain.Laboratory;
import com.njit.service.LaboratoryService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class LaboratoryServiceImp extends DaoSupportImp<Laboratory> implements
		LaboratoryService {

	@Override
	public List<Laboratory> findAvailLab(List<Laboratory> allLabList,List<Experiment> experimentList,String expTime) {
//		System.out.println("------------------------"+expTime);
//		System.out.println("-------------"+allLabList.size());
//		System.out.println("``````````````````````"+experimentList.size());
//		for(Laboratory lab1:allLabList){
//			for(Experiment exp:experimentList)
//			{
//				if((exp.getLab().getId()==lab1.getId())&&(expTime.equals(exp.getExpTime())))
//				{
//					System.out.println("============111111p"+exp.getExpTime()+"  "+exp.getProject().getName()+"  "+exp.getLab().getName());
//					allLabList.remove(lab1);
//					System.out.println(allLabList.size());
//					break;
//				}
//			}
//			System.out.println("111111");
//		}
//		System.out.println("---------+++++++---------------"+allLabList.size());
		Laboratory lab=null;
		Experiment exp=null;
		List<Laboratory> temp=new ArrayList<Laboratory>();
		for(int i=0;i<allLabList.size();i++)
		{
			lab=allLabList.get(i);
			for(int j=0;j<experimentList.size();j++)
			{
				exp=experimentList.get(j);
				if((exp.getLab().getId()==lab.getId())&&(expTime.equals(exp.getExpTime())))
					{
						System.out.println("============111111p"+exp.getExpTime()+"  "+exp.getProject().getName()+"  "+exp.getLab().getName());
						temp.add(exp.getLab());
						//allLabList.remove(lab);
						System.out.println(temp.size());
						break;
					}
			}
			
		}
//		System.out.println("==="+temp.size());
		allLabList.removeAll(temp);
	//	System.out.println("000000000000000000");
		return allLabList;
	}		
		//	List<Laboratory> availLabList=null;
//		for(int i=0;i<allLabList.size();i++)
//		{
//			Laboratory lab1=allLabList.get(i);
//			for(int j=0;j<allocateLabList.size();j++)
//			{
//				
//				Laboratory lab2=allocateLabList.get(j);
//				if(lab1.getId()!=lab2.getId())
//				{
//					availLabList.add(lab1);
//				}
//			}
//		}




}
