const db = require('../dbUtils/db');

const Owner = {
    create: (name, email, phone, password, callback) => {
        const sql = 'INSERT INTO shop_owner(name, email, phone, password) VALUES (?, ?, ?, ?)';
        db.query(sql, [name, email, phone, password], callback);
    },

    getByEmail: (email, callback) => {
        const sql = 'SELECT * FROM shop_owner WHERE email = ?';
        db.query(sql, [email], callback);
    }
};

module.exports = Owner;

