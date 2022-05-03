create database gateway;

use gateway;

create table route
(
    id    int auto_increment comment '主键'
        primary key,
    ukey  varchar(64)      not null comment 'route配置的id',
    uri   varchar(128)     not null comment '对应route配置的uri',
    ord   int              null comment 'route配置的order',
    state bit default b'0' not null comment '使用状态 0禁用 1启用',
    constraint `key`
        unique (ukey)
) comment '路由信息表';

create table predicate
(
    id       int auto_increment comment '主键'
        primary key,
    route_id int           not null comment 'route表主键',
    ukey     varchar(64)   not null comment 'predicate的key',
    value    varchar(1024) not null comment 'predicate的value'
) comment '规则表';

create table filter
(
    id       int auto_increment comment '主键'
        primary key,
    route_id int           not null comment 'route表主键',
    name     varchar(64)   not null comment 'filter的name',
    value    varchar(1024) not null comment 'filter的value'
) comment '过滤器表';





