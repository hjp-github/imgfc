-- Create table
create table FC_TIMER_TASKS
(
  id               VARCHAR2(50) not null,
  image_token      VARCHAR2(50) not null,
  source_file_type VARCHAR2(10) not null,
  source_file_md5  VARCHAR2(50) not null,
  source_file_path VARCHAR2(500) not null,
  target_file_type VARCHAR2(10) not null,
  target_file_md5  VARCHAR2(50),
  target_file_path VARCHAR2(500),
  handle_code      VARCHAR2(50),
  handle_time      DATE,
  handle_state     VARCHAR2(10),
  handle_msg       VARCHAR2(500),
  create_time      DATE,
  last_update_time DATE,
  handle_count     NUMBER default 0
);
-- Add comments to the table 
comment on table FC_TIMER_TASKS
  is '文件转换定时任务';
-- Add comments to the columns 
comment on column FC_TIMER_TASKS.id
  is 'ID uuid';
comment on column FC_TIMER_TASKS.image_token
  is '业务文件数据唯一标识';
comment on column FC_TIMER_TASKS.source_file_type
  is '被转换的文件类型';
comment on column FC_TIMER_TASKS.source_file_md5
  is '被转换的文件MD5';
comment on column FC_TIMER_TASKS.source_file_path
  is '被转换的文件路径';
comment on column FC_TIMER_TASKS.target_file_type
  is '转换后的文件类型';
comment on column FC_TIMER_TASKS.target_file_md5
  is '转换后的文件MD5';
comment on column FC_TIMER_TASKS.target_file_path
  is '转换后的文件路径';
comment on column FC_TIMER_TASKS.handle_code
  is '任务锁定标识';
comment on column FC_TIMER_TASKS.handle_time
  is '任务锁定时间';
comment on column FC_TIMER_TASKS.handle_state
  is '任务处理状态（0/待处理，1/处理中，2/处理完成，3/处理异常）';
comment on column FC_TIMER_TASKS.handle_msg
  is '任务处理异常描述';
comment on column FC_TIMER_TASKS.create_time
  is '任务创建时间';
comment on column FC_TIMER_TASKS.last_update_time
  is '任务最后修改时间';
comment on column FC_TIMER_TASKS.handle_count
  is '任务处理次数';
-- Create/Recreate primary, unique and foreign key constraints 
alter table FC_TIMER_TASKS
  add constraint FC_TIMER_TASKS_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
