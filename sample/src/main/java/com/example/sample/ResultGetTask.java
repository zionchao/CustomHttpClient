package com.example.sample;

import android.util.JsonReader;

import com.kevin.http.AppException;
import com.kevin.http.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务结果
 * @author zhangchao_a

 */
public class ResultGetTask implements Entity{

	private List<Task> data;

	public List<Task> getData() {
		return data;
	}

	public void setData(List<Task> data) {
		this.data = data;
	}

	public boolean success;

	@Override
	public String toString() {
		if(data!=null&&data.size()>0)
		    return data.get(0).getTaskcode();
		else
			return "成功返回";
	}

	@Override
	public void readFromJson(JsonReader reader) throws AppException {
		try {
			data=new ArrayList<>();
			reader.beginObject();
			while ( reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("data")) {
//					reader.beginObject();
//					while (reader.hasNext())
//					{
						reader.beginArray();
						while (reader.hasNext()) {
//							reader.beginObject();
							//                        while( reader.hasNext())
							{
								Task task = new Task();
								task.readFromJson(reader);
								data.add(task);
							}
//							reader.endObject();
						}
						reader.endArray();
//					}
//					reader.endObject();

				}else if (name.equalsIgnoreCase("success"))
				{
					success=reader.nextBoolean();
				}else
					reader.skipValue();
			}
			reader.endObject();
		} catch (Exception e) {
			throw new AppException(AppException.ErrorType.IO,e.getMessage());
		}
	}
}
