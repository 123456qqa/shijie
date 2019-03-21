drop database if exists cloudCorporation ;

create database cloudCorporation ;

use cloudCorporation ;


drop table if exists UserInfo;

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo
(
   userInfoId           int not null auto_increment,
   mobilePhone          varchar(50),
   password             varchar(255),
   userName             varchar(255),
   email                varchar(255),
   trueName             varchar(255),
   gender               varchar(20),
   headImage            text,
   brief                text,
   primary key (userInfoId)
);
select * from userInfo

select mobilePhone,headImage from userInfo

/*�����Ѿ���Ϊ���ѻ������������Ϊ���ѡ��û����������û������Գ�Ϊ����*/
select * from userInfo 
where userInfoId<>2 /*�����û�����*/
and userInfoId not in (
/*������ȷ�Ϻ����б���*/
select userInfoBId from friend where userInfoAId = 2 and status='approved'
) and userInfoId not in (
select userInfoAId from friend where userInfoBId = 2 and status='approved'
)
and  userInfoId not in(
/*��������������б���*/
select userInfoBId from friend where userInfoAId = 2 and status='applied'
) and userInfoId not in ( 
select userInfoAId from friend where userInfoBId = 2 and status='applied'
)

select * from friend 

insert into friend(userInfoAId,userInfoBId,status,startTime)
values(2,1,'approved',now()) ;

delete from friend ;

insert into friend(userInfoAId,userInfoBId,status,startTime)
values(1,2,'approved',now()) ;

delete from friend ;
insert into friend(userInfoAId,userInfoBId,status,startTime)
values(2,1,'applied',now()) ;

delete from friend ;

insert into friend(userInfoAId,userInfoBId,status,startTime)
values(1,2,'applied',now()) ;

insert into userInfo( mobilePhone ,password)values('13855669988','password');
select * from userInfo;
show databases;