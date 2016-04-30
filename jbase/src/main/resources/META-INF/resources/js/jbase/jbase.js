/**
 * Bibilioteca javascript jbase.js
 */
jbase = function() {

	/**
	 * Effeito de highlight no component com o ID especificado.
	 */
	var highlight = function(id) {
		var eId = PrimeFaces.escapeClientId(id);
		for (var i = 0; i < 2; i++) {
			$(eId).effect("highlight", {}, 500);
		}
	}

	return {
		highlight : highlight
	}

}();