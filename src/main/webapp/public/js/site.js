/*
 * Do nothing
 */
function mvmNOP() {}

/*
 * Force a page reload when a modal with class mvm is hidden. Used in confirmation dialogs
 */
$('body').on('hidden.bs.modal', '.mvm', function () {
    window.location.reload();
  });

/*
 * Enable HTML markup for auto loading content using Ajax into a container.
 *
 * Add an onClick event handler to all A link tags marked with data-ajax-load
 * attribute. The value of the data-ajax-load attribute is the selector used
 * to identify where to inject the data into the page. This is also used to
 * pull the data out of the response.
 *
 * The URL of the A tag is used to fetch the page.
 */     
$(document).on('click', 'a[data-ajax-load]', function(event) {
    var tUrl = $(event.target).attr('href');
    var tTarget = $(event.target).data('ajax-load');
    $(tTarget).load(tUrl + ' ' + tTarget, 
        function(response, status, xhr) {
          if (status == "error") {
            console.log(xhr.status + " " + xhr.statusText);
          }
        }
    );
    return false;
});
