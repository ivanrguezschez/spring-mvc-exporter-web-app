jQuery(document).ready(function ($) {
    $.ajaxSetup({
        // Disable caching of AJAX responses     
        cache: false
    });
});
function extractId(id) {
    return id.substring(id.indexOf('_') + 1);
}