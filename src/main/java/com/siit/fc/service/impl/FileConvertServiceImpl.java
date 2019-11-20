package com.siit.fc.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siit.fc.mapper.FcTimerTasksMapper;
import com.siit.fc.model.FcTimerTasks;
import com.siit.fc.service.IFileConvertService;
import com.siit.fc.web.listener.AppListener;

import common.finals.FcHandleStateFinal;
import common.form.BizResult;
import common.utils.Convert;
import common.utils.DateSiitUtil;
import common.utils.LibreOfficeUtil;
import common.utils.MD5Util;

@Service
public class FileConvertServiceImpl implements IFileConvertService {

	private static final Logger logger = LoggerFactory.getLogger(FileConvertServiceImpl.class);

	@Autowired
	private FcTimerTasksMapper fcTimerTasksMapper;

	public BizResult addFcTimerTasks() {

		FcTimerTasks record = null;

		for (int i = 0; i < 10000; i++) {
			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("1");
			record.setSourceFileType("docx");
			record.setSourceFileMd5("aeb86606b78163ed65fbec02b2963b57");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/1.doc");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);

			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("2");
			record.setSourceFileType("doc");
			record.setSourceFileMd5("1be70767d0e36dce18cdcb19ff5c4056");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/2.doc");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);

			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("3");
			record.setSourceFileType("doc");
			record.setSourceFileMd5("a86020f157e934502878a3fb3708dab6");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/3.docx");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);

			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("4");
			record.setSourceFileType("doc");
			record.setSourceFileMd5("b33ce0502e63ff80afdd59e0061190f4");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/4.doc");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);

			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("5");
			record.setSourceFileType("doc");
			record.setSourceFileMd5("15056b8483334c5ff7b9879eb2a5eaff");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/5.doc");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);

			record = new FcTimerTasks();
			record.setId(Convert.getUUID());
			record.setImageToken("6");
			record.setSourceFileType("doc");
			record.setSourceFileMd5("3bd722410b723116299726ad6134b328");
			record.setSourceFilePath("C:/Users/siit/Desktop/pdf/6.doc");
			record.setTargetFileType("pdf");
			record.setTargetFileMd5(null);
			record.setTargetFilePath(null);
			record.setHandleCode(null);
			record.setHandleTime(null);
			record.setHandleState("0");
			record.setHandleMsg(null);
			record.setCreateTime(new Date());
			record.setLastUpdateTime(new Date());
			record.setHandleCount(0);
			fcTimerTasksMapper.insert(record);
		}

		return BizResult.error("影响行数为0");
	}

	public int updateLockNewTask() {
		return fcTimerTasksMapper.updateLockNewTask(AppListener.getAppSign(), Integer.valueOf(AppListener.getLockRownum()), new Date());
	}

	public int updateLockOldTask() {

		return fcTimerTasksMapper.updateLockOldTask(AppListener.getAppSign(), Integer.valueOf(AppListener.getLockRownum()), new Date());
	}

	public List<FcTimerTasks> findLockTask(String handleState) {
		return fcTimerTasksMapper.findLockTask(AppListener.getAppSign(), handleState);
	}

	public BizResult alterFcTimerTasks(FcTimerTasks fcTimerTasks) {
		BizResult result = BizResult.success();

		if (FcHandleStateFinal.HANDLE_WAITING.equals(fcTimerTasks.getHandleState())) {
			// 新锁定的任务
			result = alterNewFcTimerTasks(fcTimerTasks);
			if (!result.isSuccessful()) {
				// 修改处理次数
				if (Convert.IsNullOrEmpty(fcTimerTasks.getHandleCount())) {
					fcTimerTasks.setHandleCount(1);
				} else {
					fcTimerTasks.setHandleCount(fcTimerTasks.getHandleCount() + 1);
				}
				fcTimerTasks.setHandleState(FcHandleStateFinal.HANDLE_ERROR);
				fcTimerTasks.setHandleMsg(result.getResultHint());
				fcTimerTasks.setLastUpdateTime(new Date());
				fcTimerTasksMapper.updateByPrimaryKey(fcTimerTasks);
			}
		} else if (FcHandleStateFinal.HANDLE_ING.equals(fcTimerTasks.getHandleState())) {
			// 正在转换中的任务
			result = alterHandlingFcTimerTasks(fcTimerTasks);
			if (!result.isSuccessful()) {
				// 修改处理次数
				if (Convert.IsNullOrEmpty(fcTimerTasks.getHandleCount())) {
					fcTimerTasks.setHandleCount(1);
				} else {
					fcTimerTasks.setHandleCount(fcTimerTasks.getHandleCount() + 1);
				}
				fcTimerTasks.setLastUpdateTime(new Date());
				fcTimerTasksMapper.updateByPrimaryKey(fcTimerTasks);
			}
		} else {
			return BizResult.error("业务处理暂时不支持HANDLE_STATE[" + fcTimerTasks.getHandleState() + "]状态");
		}
		return result;
	}

	/**
	 * 新锁定转换任务处理
	 * 
	 * @param fcTimerTasks
	 * @return
	 */
	private BizResult alterNewFcTimerTasks(FcTimerTasks fcTimerTasks) {
		// 检查待转换文件是否存在
		File srcFile = new File(fcTimerTasks.getSourceFilePath());
		if (!srcFile.exists()) {
			return BizResult.error("转换任务[" + fcTimerTasks.getId() + "]处理失败，源文件不存在[" + fcTimerTasks.getSourceFilePath() + "]");
		}

		// 文件MD5校验
		String md5 = MD5Util.getFileMD5String(srcFile);
		if (!fcTimerTasks.getSourceFileMd5().equalsIgnoreCase(md5)) {
			return BizResult.error("转换任务[" + fcTimerTasks.getId() + "]处理失败，源文件MD5值不匹配[" + fcTimerTasks.getSourceFilePath() + "]");
		}

		// 转换后文件存放路径
		String targetFilePath = fcTimerTasks.getSourceFilePath().substring(0, fcTimerTasks.getSourceFilePath().lastIndexOf("/") + 1) + fcTimerTasks.getTargetFileType().toLowerCase() + "/"
				+ DateSiitUtil.formatDate(new Date(), "yyyy/MM/dd/HH/mm/ss/SSS");
		File targetFileFolder = new File(targetFilePath);
		if (!targetFileFolder.exists()) {
			targetFileFolder.mkdirs();
		}

		// 文件转换
		LibreOfficeUtil.wordToPdf(fcTimerTasks.getSourceFilePath(), targetFilePath);

		// 修改成转换中状态
		fcTimerTasks.setHandleState(FcHandleStateFinal.HANDLE_ING);
		fcTimerTasks.setTargetFilePath(targetFilePath + "/" + srcFile.getName().substring(0, srcFile.getName().lastIndexOf(".") + 1) + fcTimerTasks.getTargetFileType().toLowerCase());
		fcTimerTasks.setLastUpdateTime(new Date());
		fcTimerTasksMapper.updateByPrimaryKey(fcTimerTasks);

		return BizResult.success();
	}

	/**
	 * 转换中任务处理
	 * 
	 * @param fcTimerTasks
	 * @return
	 */
	private BizResult alterHandlingFcTimerTasks(FcTimerTasks fcTimerTasks) {
		// 判断转换后的文件是否存在
		File targetFile = new File(fcTimerTasks.getTargetFilePath());
		if (!targetFile.exists()) {
			return BizResult.error("转换任务[" + fcTimerTasks.getId() + "]处理中，转换后文件还未生成[" + fcTimerTasks.getTargetFilePath() + "]");
		}

		// 计算文件MD5值
		String md5 = MD5Util.getFileMD5String(targetFile);
		if (Convert.IsNullOrEmpty(md5)) {
			return BizResult.error("转换任务[" + fcTimerTasks.getId() + "]处理中，转换后文件获取文件MD5值为空[" + fcTimerTasks.getTargetFilePath() + "]");
		}

		// 修改转化成功状态
		// 修改处理次数
		if (Convert.IsNullOrEmpty(fcTimerTasks.getHandleCount())) {
			fcTimerTasks.setHandleCount(1);
		} else {
			fcTimerTasks.setHandleCount(fcTimerTasks.getHandleCount() + 1);
		}
		fcTimerTasks.setTargetFileMd5(md5);
		fcTimerTasks.setHandleState(FcHandleStateFinal.HANDLE_SUCCESS);
		fcTimerTasks.setHandleMsg("");
		fcTimerTasks.setLastUpdateTime(new Date());
		fcTimerTasksMapper.updateByPrimaryKey(fcTimerTasks);

		return BizResult.success();
	}

}
