package com.njit.util.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.njit.domain.Task;

public class TaskGsonAdapter implements JsonSerializer<Task>{

	@Override
	public JsonElement serialize(Task task, Type type,
			JsonSerializationContext context) {
		JsonObject o = new JsonObject() ;
		o.addProperty("id", task.getId()) ;
		o.addProperty("name", task.getName()) ;
		return o;
	}

}
