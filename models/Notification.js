const mongoose = require('mongoose') ;
const Schema = mongoose.Schema ;
const ObjectId = require("mongodb").ObjectId ;
const NotificationSchema = new Schema({
    idUser: { type: ObjectId, ref: "User", required: true } ,
    idSite:{ type: ObjectId, ref: "Sitetouristique", required: true } 
}) ;

module.exports = mongoose.model("Notification", NotificationSchema);