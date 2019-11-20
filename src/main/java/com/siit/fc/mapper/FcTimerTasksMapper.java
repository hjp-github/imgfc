package com.siit.fc.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.siit.fc.model.FcTimerTasks;

public interface FcTimerTasksMapper {
	int deleteByPrimaryKey(String id);

	int insert(FcTimerTasks record);

	int insertSelective(FcTimerTasks record);

	FcTimerTasks selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(FcTimerTasks record);

	int updateByPrimaryKey(FcTimerTasks record);

	int updateLockNewTask(@Param("handleCode") String handleCode, @Param("lockRownum") int lockRownum, @Param("nowDate") Date nowDate);
	
	int updateLockOldTask(@Param("handleCode") String handleCode, @Param("lockRownum") int lockRownum, @Param("nowDate") Date nowDate);

	List<FcTimerTasks> findLockTask(@Param("handleCode") String handleCode, @Param("handleState") String handleState);

}