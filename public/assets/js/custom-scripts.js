/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";
    var mainApp = {

        initFunction: function () {
            /*MENU 
            ------------------------------------*/
            $('#main-menu').metisMenu();
			
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });
     
        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.initFunction();
    });

}(jQuery));


function addZero(i) 
{
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function obtenerHora()
{
    var d = new Date();
    var h = addZero(d.getHours());
    var m = addZero(d.getMinutes());
    var s = addZero(d.getSeconds());
    
    return (h + ":" + m + ":" + s);
}

function obtenerFecha()
{
    var currentdate = new Date(); 
    var datetime = currentdate.getFullYear()+"-"+
                   addZero((currentdate.getMonth()+1))+"-"+
                   addZero(currentdate.getDate());
    return datetime;
}