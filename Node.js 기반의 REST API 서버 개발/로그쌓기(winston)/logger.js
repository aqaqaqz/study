const winston = require('winston');
require('winston-daily-rotate-file');

const logger = winston.createLogger({
    level: 'info',
    // 파일저장
    transports: [
        new winston.transports.DailyRotateFile({
            filename : './log/list', //파일명. 지정해준 경로+이름에 날짜붙여서 매일 로그생성.
            zippedArchive: true, // 압축여부
            format: winston.format.printf(
                info => `[${info.level.toUpperCase()}] - ${info.message}`
            )
        })
    ]
});

module.exports = logger;
