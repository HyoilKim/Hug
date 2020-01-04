module.exports = app => {
	const accounts = require("../control/account_controller.js");
	// Create a new Account
	app.post("/accounts", accounts.create);
	// Retrieve all Accounts
	app.get("/accounts", accounts.findAll);
	// Retrieve a single Account with accountId
	app.get("/accounts/:accountId", accounts.findOne);
	// Update a Account with accountId
	app.put("/accounts/:accountId", accounts.update);
	// Delete a Account with accountId
	app.delete("/accounts/:accountId", accounts.delete);
	// Create a new Account
	app.delete("/accounts", accounts.deleteAll);

	const medias = require("../control/media_controller.js")
	// Create a new Media
	app.post("/medias", medias.create);
	// Retrieve all Medias
	app.get("/medias", medias.findAll);
	// Retrieve a single Media with mediaId
	app.get("/medias/:mediaId", medias.findOne);
	// Update a Media with mediaId
	app.put("/medias/:mediaId", medias.update);
	// Delete a Media with mediaId
	app.delete("/medias/:mediaId", medias.delete);
	// Create a new Media
	app.delete("/medias", medias.deleteAll);
};