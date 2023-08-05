var express = require('express');
var router = express.Router();



const {findSiteByCategorie,findAllCategorie,ajoutCommentaire,findAllNotification,findSiteById,findSiteByTitre} = require('../controller/SiteTouristiqueController') ;

// /* GET users listing. */

// router.post('/save',save); //ajout reparation
router.get('/siteByCategorie/:idCategorie',findSiteByCategorie);
router.get('/siteById/:idSite',findSiteById);
router.get('/siteByTitre/:titre',findSiteByTitre)
router.post('/saveComs',ajoutCommentaire);
router.get('/categorie',findAllCategorie);
router.get('/notification',findAllNotification);

              
            


module.exports = router;
