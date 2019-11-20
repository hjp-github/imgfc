package com.siit.fc.service;

import java.util.List;

import com.siit.fc.model.FcTimerTasks;

import common.form.BizResult;

public interface IFileConvertService {

	/**
	 * 新增转换任务
	 * 
	 * @return
	 */
	BizResult addFcTimerTasks();

	/**
	 * 锁定任务
	 */
	int updateLockNewTask();

	/**
	 * 锁定其他服务长时间占据的任务
	 * 
	 * @return
	 */
	int updateLockOldTask();

	/**
	 * 查询新锁定的任务
	 * 
	 * @param handleState
	 * 
	 * @return
	 */
	List<FcTimerTasks> findLockTask(String handleState);

	/**
	 * 任务处理
	 * 
	 * @param fcTimerTasks
	 * @return
	 */
	BizResult alterFcTimerTasks(FcTimerTasks fcTimerTasks);

}
