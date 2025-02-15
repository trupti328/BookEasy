const db = require("../dbUtils/db");

const createShop = (owner_id, name, description, contact_number, category_id, callback) => {
    db.query(
        "INSERT INTO shop (owner_id, name, description, contact_number, category_id, status) VALUES (?, ?, ?, ?, ?, 'Active')",
        [owner_id, name, description, contact_number, category_id],
        (err, result) => {
            if (err) {
                console.error("Database Insert Error:", err); // Log the error
                callback(err, null);
            } else {
                callback(null, result.insertId);
            }
        }
    );

};

const getAllShops = (callback) => {
    db.query("SELECT * FROM shop", (err, result) => {
        if (err) {
            callback(err, null);
        } else {
            callback(null, result);
        }
    });
};

const getShopByOwnerId = (owner_id, callback) => {
    db.query("SELECT * FROM shop WHERE owner_id = ?", [owner_id], (err, result) => {
        if (err) {
            callback(err, null);
        } else {
            callback(null, result);
        }
    });
};

const getShopsWithImages = (callback) => {
    const sql = `
        SELECT s.shop_id, s.name, s.description, s.contact_number, si.image_url
        FROM shop s
        LEFT JOIN shop_images si ON s.shop_id = si.shop_id
    `;
    db.query(sql, (err, result) => {
        if (err) {
            callback(err, null);
        } else {
            callback(null, result);
        }
    });
};


const getShopByCategoyId = (category_id, callback) => {
    db.query("SELECT * FROM shop WHERE category_id = ?", [category_id], (err, result) => {
        if (err) {
            callback(err, null);
        } else {
            callback(null, result);
        }
    });
};


module.exports = { createShop, getAllShops, getShopByOwnerId, getShopsWithImages, getShopByCategoyId };
