const sql = require("./db.js");

// constructor
const Account = function(account) {
	this.id = account.id;
	this.passwd = account.passwd;
}

Account.create = (newAccount, result) => {
	sql.query("INSERT INTO SET ?", newAccount, (err, res) => {
		console.log(`newAccount: ${newAccount}`);
		if (err) {
			console.log("error: ", err);
			result(err, null);
			return;
		}

		console.log("account added: ", { id: res.insertId, ...newAccount });
		result(null, {id: res.insertId, ...newAccount });
	});
};

Account.findById = (accountId, result) => {
	sql.query("SELECT * FROM accounts WHERE id = ${accountId}", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(err, null);
			return;
		}

		// account found
		if (res.length) {
			console.log("account: ", res[0]);
			result(null, res[0]);
			return;
		}

		// account not found
		result({ kind: "not found" }, null);
	});
};

Account.getAll = result => {
	sql.query("SELECT * FROM accounts", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		console.log("accounts: ", res);
		result(null, res);
	});
};

Account.updateById = (id, account, result) => {
	sql.query(
		"UPDATE accounts SET id = ?, passwd = ? WHERE id = ?",
		[account.id, account.passwd, id],
		(err, res) => {
			if (err) {
				console.log("error: ", err);
				result(null, err);
				return;
			}

			if (res.affectedRows == 0) {
				// not found account with the id
				result({ kind: "not_found" }, null);
				return;
			}

			console.log("updated account: ", { id: id, ...customer });
			result(null, { id: id, ...customer });
		}
	);
};

Account.remove = (id, result) => {
	sql.query("DELETE FROM accounts WHERE id = ?", id, (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		if (res.affectedRows == 0) {
			// not found Account with the id
			result({ kind: "not_found" }, null);
			return;
		}

		console.log("deleted account with id: ", id);
		result(null, res);
	});
};

Account.removeAll = result => {
	sql.query("DELETE FROM accounts", (err, res) => {
		if (err) {
			console.log("error: ", err);
			result(null, err);
			return;
		}

		console.log("deleted ${res.affectedRows} accounts");
		result(null, res);
	});
};

module.exports = Account;