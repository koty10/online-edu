/**
 * Listener to trigger modal close, when clicked on dialog mask.
 */
$(document).ready(function(){
    $("body").on("click",'.ui-dialog-mask',function () {
        idModal = this.id;
        idModal = idModal.replace("_modal","");
        getWidgetVarById(idModal).hide();
    })
});

/**
 * Returns the PrimefacesWidget from ID
 * @param id
 * @returns {*}
 */
function getWidgetVarById(id) {
    for (let propertyName in PrimeFaces.widgets) {
        let widget = PrimeFaces.widgets[propertyName];
        if (widget && widget.id === id) {
            return widget;
        }
    }
}