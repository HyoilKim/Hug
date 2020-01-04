const mysql = require("mysql");
const db_config = require("../config/db_config.js");

// create db connection
const connection = mysql.createConnection({
	host: db_config.HOST,
	user: db_config.USER,
	password: db_config.PASSWORD,
	database: db_config.DB
});

// open MySQL connection
connection.connect(error => {
	if (error) throw error;
	console.log("db connected\n");
});

module.exports = connection;