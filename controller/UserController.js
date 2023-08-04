const User = require("../models/User") ;


const findAll = async (req, res) => {
    User.find({}).then((result) => sendResult(res, result)) ;
} ;


const getUserInfo = async (req, res) => {
    User.findOne({_id: req.body.iduser}).then((result) => sendResult(res, result)) ;
} ;





/****************
 * SEND GENERAL *
 ***************/
function sendResult(res, result) {
    res.status(200).json(result) ;
}

/***************
 * EXPORTATION *
 **************/
module.exports = {
    findAll ,
    getUserInfo
}