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
	
	/**
	 * 
	 */
	var search = function(qry) {

	}

	/**
	 * Mapa de acentos.
	 */
	var accentMap = {
		"Á" : "A",
		"á" : "a",
		"Ã" : "A",
		"ã" : "a",
		"É" : "E",
		"é" : "e",
		"Ê" : "E",
		"ê" : "e",
		"Í" : "I",
		"í" : "i"
		"Ó" : "O",
		"ó" : "o",
		"ö" : "o",
		"Ô" : "O"
		"ô" : "o",
		"Ç" : "C"
		"ç" : "c"
	};

	/**
	 * Remove os acentos de utilizando a array accentMap para realizar a
	 * substituição pelo caractere não acentuado.
	 */
	function _normalizeSearch(term) {
		var ret = "";
		for (var i = 0; i < term.length; i++) {
			ret += accentMap[term.charAt(i)] || term.charAt(i);
		}
		return ret;
	}

	return {
		highlight : highlight
	}

}();