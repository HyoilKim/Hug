const sql = require("./db.js");

// constructor
const Media = function(media) {
	this.thumb = media.thumb;
	this.title = media.title;
	this.subtitle = media.subtitle;
	this.images = media.images;
	this.video = media.video;
}

Media.create = (newMedia, result) => {
	sql.query("INSERT INTO SET ?", newMedia, (err, res) => {
		console.log(`newMedia: ${newMedia}`);
		if (err) {
			console.log("error: ", err);
			result(err, null);
			return;
		}

		console.log("media added: ", { id: res.insertId, ...newMedia });
		result(null, {id: res.insertId, ...newMedia });
	});
};

Media.findById = (mediaId, result) => {
	sql.query("SELECT * FROM medias WHERE mid = ${mediaId}", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(err, null);
			return;
		}

		// media found
		if (res.length) {
			console.log("media: ", res[0]);
			result(null, res[0]);
			return;
		}

		// media not found
		result({ kind: "not found" }, null);
	});
};

Media.getAll = result => {
	sql.query("SELECT * FROM medias", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		console.log("medias: ", res);
		result(null, res);
	});
};

Media.updateById = (id, media, result) => {
	sql.query(
		"UPDATE medias SET thumb = ?, title = ?, subtitle=?, images=? video=? WHERE mid = ?",
		[media.thumb, media.title, media.subtitle, media.images, media.video, id],
		(err, res) => {
			if (err) {
				console.log("error: ", err);
				result(null, err);
				return;
			}

			if (res.affectedRows == 0) {
				// not found media with the id
				result({ kind: "not_found" }, null);
				return;
			}

			console.log("updated media: ", { id: id, ...customer });
			result(null, { id: id, ...customer });
		}
	);
};

Media.remove = (id, result) => {
	sql.query("DELETE FROM medias WHERE mid = ?", id, (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		if (res.affectedRows == 0) {
			// not found Media with the id
			result({ kind: "not_found" }, null);
			return;
		}

		console.log("deleted media with id: ", id);
		result(null, res);
	});
};

Media.removeAll = result => {
	sql.query("DELETE FROM medias", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		console.log("deleted ${res.affectedRows} medias");
		result(null, res);
	});
};

module.exports = Media;