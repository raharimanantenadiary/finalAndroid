const mongoose = require('mongoose') ;
const Schema = mongoose.Schema ;

const CategorieSchema = new Schema({
    intitule: { type: String, required: true },
    icone:{type: String}
}) ;

module.exports = mongoose.model("Categorie", CategorieSchema);