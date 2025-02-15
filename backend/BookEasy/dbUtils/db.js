const mysql = require('mysql2');

//mysql connection pool
const pool = mysql.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    connectionLimit: 10,
});

//test connection
pool.getConnection((err, connection) => {
    if (err) {
        console.error("Database connection error: ", err);
        return;
    }
    console.log('Connected to the mysql database.');
    connection.release();
});

module.exports = pool;