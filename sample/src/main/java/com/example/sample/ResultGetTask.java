package com.example.sample;

import java.util.List;

/**
 * 任务结果
 * @author zhangchao_a

 */
public class ResultGetTask {

	private List<Task> data;

	public List<Task> getData() {
		return data;
	}

	public void setData(List<Task> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		if(data!=null&&data.size()>0)
		    return data.get(0).getTaskcode();
		else
			return "成功返回";
	}
}
