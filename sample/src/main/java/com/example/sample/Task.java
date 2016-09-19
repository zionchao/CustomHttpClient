package com.example.sample;

/**
 * 任务表
 * 
 * @author zhangchao_a
 *
 */
public class Task {
	private long taskId;// bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',  //, unique = true
	private String taskcode;// varchar(20) NOT NULL COMMENT '任务编号: 日期+编码+序号',
	private String taskname;// varchar(50) NOT NULL COMMENT '任务名称',
	private String startTime;// timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
								// COMMENT '计划开始时间',
	private String endTime;// timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT '计划终止时间',
	private String priority;// varchar(16) NOT NULL DEFAULT 'MEDIUM' COMMENT
							// '优先级：LOW-低/MEDIUM-中/HIGH-高)',
	private String scenes;// varchar(2) NOT NULL COMMENT '场景,枚举:1-电视，2-电影',
	private String exeBegintime;// varchar(6) NOT NULL COMMENT '区段开始执行时间',
	private String exeEndtime;// varchar(6) NOT NULL COMMENT '区段结束执行时间',
	private int iterationNum;// int(5) NOT NULL COMMENT '循环次数',
	private String intervalType;// varchar(1) NOT NULL DEFAULT '0' COMMENT '是否间隔// 0：不设置间隔/1：有间隔时间',
	private String interval;// varchar(4) NOT NULL DEFAULT '0' COMMENT
							// '间隔时间，单位分钟，当IntervalType为1时必填',
	private String iterationType;// varchar(2) NOT NULL COMMENT
									// '重复类型:0：不限/1：每天/2：每周/3：每月',
	//脚本内容
	private String scriptContent;
	
	private String taskstatus;//为0则为无效
	//场景名称
	private String scenesName;
	//场景标题
	private String scenesTitle;
	
	//执行总次数
	private int totalNum;

	public Task() {
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getScenes() {
		return scenes;
	}

	public void setScenes(String scenes) {
		this.scenes = scenes;
	}

	public String getExeBegintime() {
		return exeBegintime;
	}

	public void setExeBegintime(String exeBegintime) {
		this.exeBegintime = exeBegintime;
	}

	public String getExeEndtime() {
		return exeEndtime;
	}

	public void setExeEndtime(String exeEndtime) {
		this.exeEndtime = exeEndtime;
	}

	public int getIterationNum() {
		return iterationNum;
	}

	public void setIterationNum(int iterationNum) {
		this.iterationNum = iterationNum;
	}

	public String getIntervalType() {
		return intervalType;
	}

	public void setIntervalType(String intervalType) {
		this.intervalType = intervalType;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getIterationType() {
		return iterationType;
	}

	public void setIterationType(String iterationType) {
		this.iterationType = iterationType;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}

	public String getScenesName() {
		return scenesName;
	}

	public void setScenesName(String scenesName) {
		this.scenesName = scenesName;
	}

	public String getScenesTitle() {
		return scenesTitle;
	}

	public void setScenesTitle(String scenesTitle) {
		this.scenesTitle = scenesTitle;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
}
