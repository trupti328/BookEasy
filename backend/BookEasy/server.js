const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
require('dotenv').config();


const customerRoutes = require('./routes/customerRoutes');
const ownerRoutes = require('./routes/ownerRoutes');
const shopRoutes = require('./routes/shopRoutes');
const categoryRoutes = require('./routes/categoryRoute');

const app = express();
const PORT = process.env.PORT;


app.use(cors());
app.use(bodyParser.json());


app.use('/customer', customerRoutes);
app.use('/owner', ownerRoutes);
app.use('/shop', shopRoutes);
app.use('/category', categoryRoutes);

app.get('/shop/category/:category_id', (req, res) => {
    const categoryId = req.params.category_id;
    console.log(`Received category_id: ${categoryId}`); // ✅ Check logs
    res.json({ message: "Success" });
});


app.listen(PORT, () => {
    console.log(`server is running on port ${PORT}`);
});