const site = require("../models/SiteTouristique") ;
const categorie = require("../models/Categorie") ;
const Notification = require("../models/Notification") ;
const mongoose=require("mongoose");



const findSiteByCategorie = async (req, res) => {
    await site.find({idCategorie: req.params.idCategorie})
    .exec(function (err, site) {
        if (err) {
            sendResult(res, err);
        } else {
        sendResult(res, site);
    }
});
}

const findSiteById = async (req, res) => {
    await site.findOne({_id: req.params.idSite})
    .exec(function (err, site) {
        if (err) {
            sendResult(res, err);
        } else {
        sendResult(res, site);
    }
});
}




const findAllCategorie = async (req, res) => {
    await categorie.find()
    .exec(function (err, categorie) {
        if (err) {
            sendResult(res, err);
        } else {
        sendResult(res, categorie);
    }
});
}

const findSiteByTitre = async (req, res) => {
const searchTerm = req.params.titre;

// Utilisation d'une expression régulière pour simuler LIKE%
const searchTermRegex = new RegExp(`^${searchTerm}`, 'i');
    await site.find({titre : searchTermRegex})
    .exec(function (err, site) {
        if (err) {
            sendResult(res, err);
        } else {
        sendResult(res, site);
    }
});
}

const findAllNotification = async (req, res) => {
    await Notification.find({}).populate('idUser','username').populate('idSite','titre')
    .exec(function (err, notification) {
    if (err) {
        sendResult(res, err);
    } else {
        sendResult(res, notification);
    }
    });
}

const ajoutCommentaire = async (req, res) => { 
    site.findOneAndUpdate(
        { _id: req.body.id}, 
        { $push: { commentaire: {
            idUser:req.body.idUser,
            contenu:req.body.contenu,
            date:req.body.date,
        } }},
        { new: true }, 
        async (err, site) => {
            console.log(site);
            if (err) return sendResult(res,err);
            await new Notification({idUser:req.body.idUser,idSite:site._id}).save(
            function(error, notification) {
            if (error)  sendResult(res, error);
            sendResult(res,notification);
        }); 
    });
};

const ajoutGallerie = async (req, res) => { 
    site.findOneAndUpdate(
        { _id: req.body.id}, 
        { $push: { galerie: {
            media:req.body.media,
        } }},
        { new: true }, 
        async (err, site) => {
            if (err) return sendResult(res,err);
            sendResult(res,site);
    });
};






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
    findSiteByCategorie,
    findAllCategorie,
    findAllNotification,
    ajoutCommentaire,
    findSiteById,
    findSiteByTitre,
    ajoutGallerie

}
