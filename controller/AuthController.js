const User = require("../models/User") ;

var jwt = require("jsonwebtoken");
var bcrypt = require("bcryptjs");
var generator = require('generate-password');
var nodemailer = require('nodemailer');
require('dotenv').config();

function generateAccessToken(user) {
    return jwt.sign({ id: user.id }, process.env.ACCESS_TOKEN_SECRET, { expiresIn: 86400});
}

 
const signup = (req, res) => {
    // Save User to Database
    console.log(req.body);
    User.findOneAndUpdate(
        { mail: req.body.mail}, 
        {   username: req.body.username,
            mail:req.body.mail,
            mdp: bcrypt.hashSync(req.body.mdp, 8),
            validation:0,
        }, 
            { upsert: true, new: true }).then(function(item){
        envoyecode(req,res);   
        })
            .catch(function (err) {
                res.send({message:err.message,error:true});
            });


};

/**const signin = (req, res) => {
    console.log(req.body.mail,req.body.mdp);
    User.findOne({mail:req.body.mail})
        .then((user) => {
            console.log(user);
            if (!user) {
               return res.send({message:"utilisateur introuvable"});
            }

            if (!user.isactive) {
                return res.send({message:"le compte n'est pas encore activé"});
             }
         
            var passwordIsValid = bcrypt.compareSync(
                req.body.mdp,
                user.mdp
            );
            if (!passwordIsValid) {
                return res.send({
                    accessToken: null,
                    message: "Mot de passe eronné!"
                });
            }
           token=generateAccessToken(user);
           console.log(token);

           
            res.send({
                id: user.id,
                username: user.username,
                mail: user.mail,
                profile:user.profile,
                accessToken: token,
                error:false
            });

        })
        .catch(err => {
            res.send({ message: err.message });
        });
};**/

const signin = (req, res) => {
    console.log(req.body.mail, req.body.mdp);
    User.findOne({
        $and: [
            { mail: req.body.mail },
            { mdp: req.body.mdp }
        ]
    })
        .then((user) => {
            console.log(user);
            if (!user) {
                // Si l'utilisateur n'est pas trouvé, on change l'activation du compte en 2
                return res.send({ message: "utilisateur introuvable", activation_compte: 2 });
            }

            if (!user.isactive) {
                return res.send({ message: "le compte n'est pas encore activé", activation_compte: 1 });
            }

            var passwordIsValid = bcrypt.compareSync(
                req.body.mdp,
                user.mdp
            );
            if (!passwordIsValid) {
                return res.send({
                    accessToken: null,
                    message: "Mot de passe erroné!",
                    activation_compte: 2
                });
            }

            token = generateAccessToken(user);
            console.log(token);

            res.send({
                id: user.id,
                username: user.username,
                mail: user.mail,
                profile: user.profile,
                accessToken: token,
                error: false,
                activation_compte: 0
            });

        })
        .catch(err => {
            res.send({ message: err.message, activation_compte: -1 });
        });
};




const envoyecode = (req, res) => {
    var email=process.env.EMAIL;
    var mdp=process.env.MDP;
    var transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: { user:email , pass:mdp }
    });
    let code = generator.generate({ length: 5, numbers: true });

    var mailOptions = {
        from: '"Tourisme"<tourismeMada@gmail.com>',
        to: req.body.mail,
        subject: 'Verification email',
        text: '',
        html: `<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Email de test</title>
  <style>
    body {
      background-color: lightblue;
    }
    h1 {
      color: black;
      text-align: center;
    }
    p {
      color: #2C2889;
      font-size: 16px;
      text-align: center;
    }
    h3 {
        color: #2EEBD7;
        font-size: 16px;
        text-align: center;
      }
  </style>
</head>
<body>
  <h1>Bonjour,</h1>
  <p>Votre code de validation est:<h3>${code}</h3></p>
  <p>Merci d'avoir utilisé notre service !</p>
  <p>Cordialement,</p>
  <p>L'équipe TourismeMada13401260</p>
</body>
</html>
                
                `
    };

    transporter.sendMail(mailOptions)
    .then(info => {
        User.updateOne({ mail: req.body.mail },{$set:{validation:code}})
            .then(rep => {
                console.log('success send mail:' + info);
                res.send({message:'activation reussis',error:false});
            })
            .catch(err => {
                res.send({message:err.message,error:true});
            })
    })
    .catch(err => {
        res.send({message:err.message,error:true});
        console.log('Erreur mail Sender: ' + err);
    })

}

const activation = (req, res) => {
        User.findOne({validation:req.body.code })
            .then(user => {
                User.updateOne({ mail: user.mail },{$set:{isactive:true}}).then(resultat =>{

                    var token = jwt.sign({ id: user.id },'secret', {
                        expiresIn: 86400 // 24 hours
                    });

                    res.send({
                        id: user.id,
                        username: user.username,
                        mail: user.mail,
                        profile:user.profile,
                        accessToken: token,
                        error:false
                    });
               }).catch(err => {
                return  res.send({message:err.message,error:true });
            })
               
            })
            .catch(err => {
                return  res.send({message:'code invalide',error:true });
            })

}



module.exports = {
   signup,
   signin,
   envoyecode,
   activation
}
