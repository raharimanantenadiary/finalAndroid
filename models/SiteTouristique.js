const mongoose = require('mongoose') ;
const Schema = mongoose.Schema ;
const ObjectId = require("mongodb").ObjectId ;

const SitetouristiqueSchema = new Schema({
    idCategorie: { type: ObjectId, ref: "Categorie", required: true } ,
    titre:{ type: String } ,
    localisation:{ type: String } ,
    historique:{ type: String } ,
    contact:{ type: String } ,
    email:{ type: String } ,
    video:{ type: String } ,

    galerie:[{
        media:{type:String,required: true }
    }],
    commentaire:[{
        idUser:{ type: ObjectId, ref: "User", required: true } ,
        contenu:{ type: String } ,
        date:{ type: String,default: Date.now}  ,
    }],


}) ;

module.exports = mongoose.model("Sitetouristique",SitetouristiqueSchema) ;