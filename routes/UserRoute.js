var express = require("express");
var router = express.Router();
const {authenticateToken } = require('../Middleware/token') ;

const { findAll, save,getUserInfo } = require('../controller/UserController') ;
// /* GET users listing. */

router.get('/',findAll );
router.get('/userInfo',getUserInfo );

module.exports = router;
