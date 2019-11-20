package com.siit.fc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siit.fc.service.IFileConvertService;

import common.form.BizResult;

/**
 * 文件转换相关请求
 * 
 * @author siit
 *
 */
@RestController
@RequestMapping("/fileConvert")
public class FileConvertController {

	private static Logger logger = LoggerFactory.getLogger(FileConvertController.class);

	@Autowired
	private IFileConvertService fileConvertService;

	@RequestMapping("/test")
	public BizResult test() {
		// http://localhost:8080/imgfc/fileConvert/test
		return fileConvertService.addFcTimerTasks();
	}

	@RequestMapping("/log")
	public BizResult log() {
		// http://localhost:8080/imgfc/fileConvert/log
		
		return BizResult.success();
	}

}
