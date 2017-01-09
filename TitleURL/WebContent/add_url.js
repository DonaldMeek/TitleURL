function newURL() {
	$("input[name='url_name']:first").clone().insertAfter("input[name='url_name']:first");
	$("input[name='url_name']:first").after("<br />");
}