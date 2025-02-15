const express = require('express');
const router = express.Router();
const shopController = require('../controllers/shopController');

// Add a new shop
router.post('/add', shopController.addShop);

// Get all shops by owner ID
router.get('/:owner_id', shopController.getShopsByOwner);

router.get('/allshops', shopController.getAllShops)

//get sho-p by category
router.get('/category/:category_id', shopController.getShopsByCategory)

module.exports = router;
