
var express = require('express');
var logger = require('./logger');
var app = express();

app.get('/', (req, res)=>{
        console.log(req.ip);
        logger.info(req.ip + " : " + "info log");
});

app.listen(12345, ()=>{
        console.log('start...');
});