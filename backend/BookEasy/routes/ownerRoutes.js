const express = require('express');
const router = express.Router();
const ownerController = require('../controllers/ownerController');

router.post('/register', ownerController.registerOwner);
router.post('/login', ownerController.loginOwner);

module.exports = router;