const db = require('../dbUtils/db');

const Category = {
    getAll: (callback) => {
        const sql = 'SELECT * FROM category';
        db.query(sql, callback);
    }
};

module.exports = Category;
