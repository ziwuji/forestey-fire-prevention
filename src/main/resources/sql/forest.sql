
-- 图片表
CREATE TABLE ShotImage(
`imgID` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'image id',
`imgName` VARCHAR(50) NOT NULL COMMENT 'image name',
`imgUrl` BIGINT NOT NULL COMMENT 'image url',
`cutTime` INT NOT NULL COMMENT 'cut time',
`vidID` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'id',
PRIMARY KEY (`imgID`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='image table';

-- 视频表
CREATE TABLE MonitorVideo(
`mVidID` INT NOT NULL AUTO_INCREMENT COMMENT 'video id',
`mVidName` VARCHAR(50),
`mVidSize` INT,
`mVidUrl` VARCHAR(50),
`startTime` TIMESTAMP,
`skchImgUrl` VARCHAR(50),
`mptID` INT,
PRIMARY KEY (`mVidID`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='video table';


-- 报警表
CREATE TABLE AlarmVideo(
`rVidID` INT NOT NULL AUTO_INCREMENT COMMENT 'video id',
`rVidName` VARCHAR(50),
`rVidSize` INT,
`rVidUrl` VARCHAR(50),
`startTime` TIMESTAMP,
`mptID` INT,
`aRecID` INT,
PRIMARY KEY (`rVidID`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='video table';

-- 监控点
CREATE TABLE MonitorPoint(
`mptID` INT NOT NULL AUTO_INCREMENT,
`mptName` VARCHAR(50),
`latitude` DOUBLE ,
`longtitude` DOUBLE ,
`height` DOUBLE,
PRIMARY KEY (`mptID`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


-- 监控记录
CREATE TABLE AlarmRecord(
`aRecID` INT NOT NULL AUTO_INCREMENT,
`alarmTime` TIMESTAMP,
`optLocX` INT ,
`optLocY` INT ,
`isHandled` INT,
`hrzAngle` DOUBLE ,
`mptID` INT,
`crewID` INT,
PRIMARY KEY (`aRecID`)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
INSERT INTO MonitorVideo (`mVidID`,`mVidName`,`mVidSize`,`mVidUrl`,`startTime`,`skchImgUrl`,`mptID`) VALUES('1','视频1','25mb','http://www.baidu.com','2018-11-09 17:01:50','http://www.jiusnake.com','1')
