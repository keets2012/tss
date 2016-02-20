package com.njit.util.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.njit.domain.Project;

public class ProjectGsonAdapter implements JsonSerializer<Project>{

	@Override
	public JsonElement serialize(Project project, Type type,
			JsonSerializationContext context) {
		JsonObject o = new JsonObject() ;
		o.addProperty("id", project.getId()) ;
		o.addProperty("name", project.getName()) ;
		return o;
	}

}
