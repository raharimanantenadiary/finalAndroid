var express = require('express');
var router = express.Router();



const {findSiteByCategorie,findAllCategorie,ajoutCommentaire,findAllNotification} = require('../controller/SiteTouristiqueController') ;

// /* GET users listing. */

// router.post('/save',save); //ajout reparation
router.get('/siteByCategorie',findSiteByCategorie);
router.post('/saveComs',ajoutCommentaire);
router.get('/categorie',findAllCategorie);
router.get('/notification',findAllNotification);

              
            


module.exports = router;
