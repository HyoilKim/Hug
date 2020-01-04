const Account = require("../models/account_model.js");

// Create and Save a new Account
exports.create = (req, res) => {
	// Validate request
	if (!req.body) {
		res.status(400).send({
			message: "empty content"
		});
	}

	// Create an Account
	const account = new Account({
		id: req.body.id,
		passwd: req.body.passwd
	});

	// Save Account in the database
	Account.create(account, (err, data) => {
		if (err)
			res.status(500).send({
				message:
					err.message || "Error occurred while creating the Account."
			});
		else res.send(data);
	});
};

// Retrieve all Accounts from the database.
exports.findAll = (req, res) => {
	Account.getAll((err, data) => {
		if (err)
			res.status(500).send({
				message:
					err.message || "Some error occurred while retrieving customers."
			});
		else res.send(data);
	});
};

// Find a single Account with an accountId
exports.findOne = (req, res) => {
	Account.findById(req.params.accountId, (err, data) => {
		if (err) {
			if (err.kind === "not found") {
				res.status(404).send({
					message: `Not found Account with id ${req.params.accountId}`
				});
			}
		}
		else res.send(data);
	});
};

// Update an Account identified by the accountId in the request
exports.update = (req, res) => {
	// Validate Request
	if (!req.body) {
		res.status(400).send({
			message: "empty content"
		});
	}

	Account.updateById(
		req.params.accoundId,
		new Account(req.body),
		(err, data) => {
			if (err) {
				if (err.kind === "not found") {
					res.status(404).send({
						message: `Not found Account with id ${req.params.accountId}`
					});
				}
				else {
					res.status(500).send({
						message: "Error updating Account with id " + req.params.accountId
					});
				}
			}
			else res.send(data);
		});
};

// Delete an Account with the specified accountId in the request
exports.delete = (req, res) => {
	Account.remove(req.params.accountId, (err, data) => {
			if (err) {
				if (err.kind === "not found") {
					res.status(404).send({
						message: `Not found Account with id ${req.params.accountId}`
					});
				}
				else {
					res.status(500).send({
						message: "Error deleting Account with id " + req.params.accountId
					});
				}
			}
			else res.send({message: `Account was deleted`});
	});	
};

// Delete all Accounts from the database.
exports.deleteAll = (req, res) => {
	Account.removeAll((err, data) => {
		if (err) {
			res.status(500).send({
				message: err.message || "Error while removing all accounts"
			});
		}
		else res.send({message: `All accounts deleted`});
	});
};