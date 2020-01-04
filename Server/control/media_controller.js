const Media = require("../models/media_model.js");

// Create and Save a new Media
exports.create = (req, res) => {
	// Validate request
	if (!req.body) {
		res.status(400).send({
			message: "empty content"
		});
	}

	// Create an Media
	const media = new Media({
		thumb: req.body.thumb,
		title: req.body.title,
		desc: req.body.desc,
		images: req.body.images,
		url: req.body.url
	});

	// Save Media in the database
	Media.create(media, (err, data) => {
		if (err)
			res.status(500).send({
				message:
					err.message || "Error occurred while creating the Media."
			});
		else res.send(data);
	});
};

// Retrieve all Medias from the database.
exports.findAll = (req, res) => {
	Media.getAll((err, data) => {
		if (err)
			res.status(500).send({
				message:
					err.message || "Some error occurred while retrieving customers."
			});
		else res.send(data);
	});
};

// Find a single Media with an mediaId
exports.findOne = (req, res) => {
	Media.findById(req.params.mediaId, (err, data) => {
		if (err) {
			if (err.kind === "not found") {
				res.status(404).send({
					message: `Not found Media with id ${req.params.mediaId}`
				});
			}
		}
		else res.send(data);
	});
};

// Update an Media identified by the mediaId in the request
exports.update = (req, res) => {
	// Validate Request
	if (!req.body) {
		res.status(400).send({
			message: "empty content"
		});
	}

	Media.updateById(
		req.params.accoundId,
		new Media(req.body),
		(err, data) => {
			if (err) {
				if (err.kind === "not found") {
					res.status(404).send({
						message: `Not found Media with id ${req.params.mediaId}`
					});
				}
				else {
					res.status(500).send({
						message: "Error updating Media with id " + req.params.mediaId
					});
				}
			}
			else res.send(data);
		});
};

// Delete an Media with the specified mediaId in the request
exports.delete = (req, res) => {
	Media.remove(req.params.mediaId, (err, data) => {
			if (err) {
				if (err.kind === "not found") {
					res.status(404).send({
						message: `Not found Media with id ${req.params.mediaId}`
					});
				}
				else {
					res.status(500).send({
						message: "Error deleting Media with id " + req.params.mediaId
					});
				}
			}
			else res.send({message: `Media was deleted`});
	});	
};

// Delete all Medias from the database.
exports.deleteAll = (req, res) => {
	Media.removeAll((err, data) => {
		if (err) {
			res.status(500).send({
				message: err.message || "Error while removing all medias"
			});
		}
		else res.send({message: `All medias deleted`});
	});
};