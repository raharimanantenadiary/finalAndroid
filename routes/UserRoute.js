var express = require("express");
var router = express.Router();
const {authenticateToken } = require('../Middleware/token') ;

const { findAll,getUserInfo } = require('../controller/UserController') ;
// /* GET users listing. */

router.get('/',findAll );
router.get('/userInfo/:iduser',getUserInfo );

module.exports = router;
