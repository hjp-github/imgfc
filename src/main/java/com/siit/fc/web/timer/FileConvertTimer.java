package com.siit.fc.web.timer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.siit.fc.model.FcTimerTasks;
import com.siit.fc.service.IFileConvertService;

import common.finals.FcHandleStateFinal;

/**
 * 文件转换定时器
 * 
 * @author siit
 *
 */
@Component
public class FileConvertTimer {

	private static final Logger logger = LoggerFactory.getLogger(FileConvertTimer.class);

	@Autowired
	private IFileConvertService fileConvertService;

	@Scheduled(cron = "0 0/1 * * * ? ")
	public void execute() {
		executeFc();
	}

	/**
	 * 定时器线程锁
	 */
	private static boolean TIMER_LOCK = false;

	/**
	 * word转PDF定时器
	 */
	public void executeFc() {

		if (TIMER_LOCK) {
			logger.debug("--当前任务正在执行--");
			return;
		}

		// 定时器线程占锁
		TIMER_LOCK = true;

		try {

			// 转换中的任务
			List<FcTimerTasks> handlingTasksList = fileConvertService.findLockTask(FcHandleStateFinal.HANDLE_ING);
			if (handlingTasksList != null && handlingTasksList.size() > 0) {
				for (FcTimerTasks fcTimerTasks : handlingTasksList) {
					fileConvertService.alterFcTimerTasks(fcTimerTasks);
				}
			}

			// 锁定新任务
			int rows = fileConvertService.updateLockNewTask();
			if (rows == 0) {
				// 锁定其他服务长时间占据的任务
				rows = fileConvertService.updateLockOldTask();
			}
			if (rows > 0) {
				List<FcTimerTasks> newTasksList = fileConvertService.findLockTask(FcHandleStateFinal.HANDLE_WAITING);
				for (FcTimerTasks fcTimerTasks : newTasksList) {
					fileConvertService.alterFcTimerTasks(fcTimerTasks);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--任务处理异常--", e);
		} finally {
			// 释放定时器线程锁
			TIMER_LOCK = false;
		}
	}

}
