const db = require('../dbUtils/db')

const Customer = {
    create: (name, email, phone, password, callback) => {
        const sql = 'INSERT INTO customer(name,email,phone,password) VALUES(?,?,?,?)';
        db.query(sql, [name, email, phone, password], callback);
    },

    getByEmail: (email, callback) => {
        const sql = 'SELECT * FROM customer WHERE email = ?';
        db.query(sql, [email], callback);
    }
};

module.exports = Customer;