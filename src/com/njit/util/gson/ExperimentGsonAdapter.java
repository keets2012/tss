package com.njit.util.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.njit.domain.Experiment;
import com.njit.domain.Project;

public class ExperimentGsonAdapter implements JsonSerializer<Experiment>{

	@Override
	public JsonElement serialize(Experiment experiment, Type type,
			JsonSerializationContext context) {
		JsonObject o = new JsonObject() ;
		o.addProperty("id", experiment.getId()) ;
		System.out.println(experiment.getProject());
		o.addProperty("projectName", experiment.getProject().getName()) ;
		o.addProperty("courseName", experiment.getProject().getCurriculum().getName()) ;
		String weekTime = experiment.getExpTime().split("w")[0];
		String dayTime = experiment.getExpTime().split("w")[1];
		String turnTimeStr = experiment.getExpTime().split("w")[2];
		int turnTime = Integer.parseInt(turnTimeStr) ;
		String expDiaplayTime = "第"+weekTime+"周，周"+dayTime+"，"+(turnTime*2-1)+"、"+(turnTime*2)+"节" ;
		o.addProperty("expTime", expDiaplayTime) ;
		o.addProperty("teacherName", experiment.getUser().getName()) ;
		o.addProperty("labName", experiment.getLab().getName()) ;
		o.addProperty("deptName", experiment.getDept().getName()) ;
		o.addProperty("description", experiment.getDescription()) ;
		return o;
	}

}
