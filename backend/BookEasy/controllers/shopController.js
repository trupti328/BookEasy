const Shop = require('../models/shopModel');

exports.getAllShops = (req, res) => {
    Shop.getAllShops((err, result) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else {
            res.json(result);
        }
    });
};

exports.getShopsByOwner = (req, res) => {
    const { owner_id } = req.params;

    Shop.getShopByOwnerId(owner_id, (err, result) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else if (result.length === 0) {
            res.status(404).json({ error: 'No shops found for this owner' });
        } else {
            res.json(result);
        }
    });
};

exports.addShop = (req, res) => {
    const { owner_id, name, description, contact_number, category_id } = req.body;

    if (!owner_id || !name || !contact_number) {
        return res.status(400).json({ error: "Missing required fields" });
    }

    Shop.createShop(owner_id, name, description, contact_number, category_id, (err, shopId) => {
        if (err) {
            console.error("Shop Creation Error:", err);
            res.status(500).json({ error: 'Database error', details: err.message });
        } else {
            res.status(201).json({
                success: true,
                message: 'Shop added successfully!',
                shop_id: shopId
            });
        }
    });
};

exports.getShopsWithImages = (req, res) => {
    Shop.getShopsWithImages((err, result) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else {
            res.json(result);
        }
    });
};


exports.getShopsByCategory = (req, res) => {
    const { category_id } = req.params;

    Shop.getShopByCategoyId(category_id, (err, result) => {
        if (err) {
            res.status(500).json({ error: 'Database error' });
        } else if (result.length === 0) {
            res.status(404).json({ error: 'No shops found for this owner' });
        } else {
            res.json(result);
        }
    });
};