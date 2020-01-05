const express = require('express');
const bodyParser = require('body-parser');

const app = express();

const port = 80;

// parse requests in the form of JSON
app.use(bodyParser.json());
// parse requests in the form of urlencoded
app.use(bodyParser.urlencoded({extended: true}));

/*
app.get("/", (req, res) => {
	res.json({message: "router executed\n"});
});
*/

require("./routes/main.js")(app);

app.listen(port, () => {
	console.log(`Server running on port 1380\n`);
});