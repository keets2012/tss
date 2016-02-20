package com.njit.util.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.njit.domain.Department;

public class DeptGsonAdapter implements JsonSerializer<Department>{

	@Override
	public JsonElement serialize(Department dept, Type type,
			JsonSerializationContext context) {
		JsonObject o = new JsonObject() ;
		o.addProperty("id", dept.getId()) ;
		o.addProperty("name", dept.getName()) ;
		return o;
	}
}
