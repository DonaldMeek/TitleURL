function newURL() {
	console.log("Here in function");
	
	$("input[name='url_name']:first").clone().insertAfter("div.added_url_textbox");
}

