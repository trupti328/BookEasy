const Category = require('../models/categoryModel');

exports.getCategory = (req, res) => {
    Category.getAll((err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database error' });
        }
        if (results.length === 0) {
            return res.status(404).json({ error: 'No categories found' });
        }
        res.json({ success: true, categories: results });
    });
};
