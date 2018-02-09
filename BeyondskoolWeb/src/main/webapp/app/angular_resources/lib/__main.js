// Javascript String constants for translation
var THEMEREX_MESSAGE_BOOKMARK_ADD   = "Add the bookmark";
var THEMEREX_MESSAGE_BOOKMARK_ADDED = "Current page has been successfully added to the bookmarks. You can see it in the right panel on the tab \'Bookmarks\'";
var THEMEREX_MESSAGE_BOOKMARK_TITLE = "Enter bookmark title";
var THEMEREX_MESSAGE_BOOKMARK_EXISTS= "Current page already exists in the bookmarks list";
var THEMEREX_MESSAGE_SEARCH_ERROR = "Error occurs in AJAX search! Please, type your query and press search icon for the traditional search way.";
var THEMEREX_MESSAGE_EMAIL_CONFIRM= "On the e-mail address <b>%s</b> we sent a confirmation email.<br>Please, open it and click on the link.";
var THEMEREX_MESSAGE_EMAIL_ADDED  = "Your address <b>%s</b> has been successfully added to the subscription list";
var THEMEREX_REVIEWS_VOTE		  = "Thanks for your vote! New average rating is:";
var THEMEREX_REVIEWS_ERROR		  = "Error saving your vote! Please, try again later.";
var THEMEREX_MAGNIFIC_LOADING     = "Loading image #%curr% ...";
var THEMEREX_MAGNIFIC_ERROR       = "<a href=\"%url%\">The image #%curr%</a> could not be loaded.";
var THEMEREX_MESSAGE_ERROR_LIKE   = "Error saving your like! Please, try again later.";
var THEMEREX_GLOBAL_ERROR_TEXT	  = "Global error text";
var THEMEREX_NAME_EMPTY			  = "The name can\'t be empty";
var THEMEREX_NAME_LONG 			  = "Too long name";
var THEMEREX_EMAIL_EMPTY 		  = "Too short (or empty) email address";
var THEMEREX_EMAIL_LONG			  = "Too long email address";
var THEMEREX_EMAIL_NOT_VALID 	  = "Invalid email address";
var THEMEREX_SUBJECT_EMPTY		  = "The subject can\'t be empty";
var THEMEREX_SUBJECT_LONG 		  = "Too long subject";
var THEMEREX_MESSAGE_EMPTY 		  = "The message text can\'t be empty";
var THEMEREX_MESSAGE_LONG 		  = "Too long message text";
var THEMEREX_SEND_COMPLETE 		  = "Send message complete!";
var THEMEREX_SEND_ERROR 		  = "Transmit failed!";
var THEMEREX_LOGIN_EMPTY		  = "The Login field can\'t be empty";
var THEMEREX_LOGIN_LONG			  = "Too long login field";
var THEMEREX_PASSWORD_EMPTY		  = "The password can\'t be empty and shorter then 5 characters";
var THEMEREX_PASSWORD_LONG		  = "Too long password";
var THEMEREX_PASSWORD_NOT_EQUAL   = "The passwords in both fields are not equal";
var THEMEREX_REGISTRATION_SUCCESS = "Registration success! Please log in!";
var THEMEREX_REGISTRATION_FAILED  = "Registration failed!";
var THEMEREX_REGISTRATION_AUTHOR  = "Your account is waiting for the site admin moderation!";
var THEMEREX_GEOCODE_ERROR 		  = "Geocode was not successful for the following reason:";
var THEMEREX_GOOGLE_MAP_NOT_AVAIL = "Google map API not available!";


// AJAX parameters
var THEMEREX_ajax_url = "#";
var THEMEREX_ajax_nonce = "10a39e6899";

// Site base url
var THEMEREX_site_url = "#";

// Theme base font
var THEMEREX_theme_font = "";

// Theme skin
var THEMEREX_theme_skin = "kidscare";
var THEMEREX_theme_skin_bg = "#ffffff";

// Slider height
var THEMEREX_slider_height = 670;

// Sound Manager
var THEMEREX_sound_enable    = false;
var THEMEREX_sound_folder    = '';
var THEMEREX_sound_mainmenu  = '';
var THEMEREX_sound_othermenu = '';
var THEMEREX_sound_buttons   = '';
var THEMEREX_sound_links     = '';
var THEMEREX_sound_state     = { 
	all: THEMEREX_sound_enable ? 1 : 0, 
	mainmenu:	0,
	othermenu:	0,
	buttons:	1,
	links: 		0};

// System message
var THEMEREX_systemMessage = {message:"", status: "", header: ""};

// User logged in
var THEMEREX_userLoggedIn = false;

// Show table of content for the current page
var THEMEREX_menu_toc = 'no';
var THEMEREX_menu_toc_home = THEMEREX_menu_toc!='no' && true;
var THEMEREX_menu_toc_top = THEMEREX_menu_toc!='no' && true;

// Fix main menu
var THEMEREX_menuFixed = true;

// Use responsive version for main menu
var THEMEREX_menuResponsive = 1024;
var THEMEREX_responsive_menu_click = true;

// Right panel demo timer
var THEMEREX_demo_time = 4000;

// Video and Audio tag wrapper
var THEMEREX_useMediaElement = true;

// Use AJAX search
var THEMEREX_useAJAXSearch = true;
var THEMEREX_AJAXSearch_min_length = 3;
var THEMEREX_AJAXSearch_delay = 200;

// Popup windows engine
var THEMEREX_popupEngine  = 'pretty';
var THEMEREX_popupGallery = true;

// E-mail mask
THEMEREX_EMAIL_MASK = '^([a-zA-Z0-9_\\-]+\\.)*[a-zA-Z0-9_\\-]+@[a-z0-9_\\-]+(\\.[a-z0-9_\\-]+)*\\.[a-z]{2,6}$';

// Messages max length
var THEMEREX_msg_maxlength_contacts = 1000;
var THEMEREX_msg_maxlength_comments = 1000;

// Remember visitors settings
var THEMEREX_remember_visitors_settings = true;

if (THEMEREX_theme_font=='') THEMEREX_theme_font = 'Sintony';

var tribe_js_config = {"permalink_settings":"\/%postname%\/","events_post_type":"tribe_events","events_base":"#"};


jQuery(window).load(function() {
    "use strict";
    preloader();
});

jQuery(document).ready(function() {
    "use strict";
	main_slider_init();
	reviews_stars();
	arc_skills_legend_color();
	price_filter();
	woo_review_star();
	hover_mobile();
});


/* preloader */
function preloader(){
    "use strict";
    //preloader
    jQuery(".preloaderimg").fadeOut();
    jQuery(".preloader").delay(200).fadeOut("slow").delay(200, function(){
        jQuery(this).remove();
    });
}

/*Main slider*/
function main_slider_init() {
    "use strict";
    if (jQuery("#mainslider_1").length > 0) {

		var setREVStartSize = function() {
			var	tpopt = new Object();
				tpopt.startwidth = 1150;
				tpopt.startheight = 633;
				tpopt.container = jQuery('#rev_slider_1');
				tpopt.fullScreen = "off";
				tpopt.forceFullWidth="off";

			tpopt.container.closest(".rev_slider_wrapper").css({height:tpopt.container.height()});tpopt.width=parseInt(tpopt.container.width(),0);tpopt.height=parseInt(tpopt.container.height(),0);tpopt.bw=tpopt.width/tpopt.startwidth;tpopt.bh=tpopt.height/tpopt.startheight;if(tpopt.bh>tpopt.bw)tpopt.bh=tpopt.bw;if(tpopt.bh<tpopt.bw)tpopt.bw=tpopt.bh;if(tpopt.bw<tpopt.bh)tpopt.bh=tpopt.bw;if(tpopt.bh>1){tpopt.bw=1;tpopt.bh=1}if(tpopt.bw>1){tpopt.bw=1;tpopt.bh=1}tpopt.height=Math.round(tpopt.startheight*(tpopt.width/tpopt.startwidth));if(tpopt.height>tpopt.startheight&&tpopt.autoHeight!="on")tpopt.height=tpopt.startheight;if(tpopt.fullScreen=="on"){tpopt.height=tpopt.bw*tpopt.startheight;var cow=tpopt.container.parent().width();var coh=jQuery(window).height();if(tpopt.fullScreenOffsetContainer!=undefined){try{var offcontainers=tpopt.fullScreenOffsetContainer.split(",");jQuery.each(offcontainers,function(e,t){coh=coh-jQuery(t).outerHeight(true);if(coh<tpopt.minFullScreenHeight)coh=tpopt.minFullScreenHeight})}catch(e){}}tpopt.container.parent().height(coh);tpopt.container.height(coh);tpopt.container.closest(".rev_slider_wrapper").height(coh);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(coh);tpopt.container.css({height:"100%"});tpopt.height=coh;}else{tpopt.container.height(tpopt.height);tpopt.container.closest(".rev_slider_wrapper").height(tpopt.height);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(tpopt.height);}
		};

		/* CALL PLACEHOLDER */
		setREVStartSize();


		var tpj=jQuery;
		tpj.noConflict();
		var revapi6;

		tpj(document).ready(function() {

		if(tpj('#rev_slider_1').revolution == undefined)
			revslider_showDoubleJqueryError('#rev_slider_1');
		else
		   revapi6 = tpj('#rev_slider_1').show().revolution(
			{
				dottedOverlay:"none",
				delay:14000,
				startwidth:1150,
				startheight:633,
				hideThumbs:200,
				thumbWidth:100,
				thumbHeight:50,
				thumbAmount:3,
				simplifyAll:"off",
				navigationType:"bullet",
				navigationArrows:"solo",
				navigationStyle:"round",
				touchenabled:"on",
				onHoverStop:"on",
				nextSlideOnWindowFocus:"off",
				swipe_threshold: 75,
				swipe_min_touches: 1,
				drag_block_vertical: false,
				parallax:"mouse",
				parallaxBgFreeze:"off",
				parallaxLevels:[2,4,6,8,10,12,14,16,18,20],
				parallaxDisableOnMobile:"on",
				keyboardNavigation:"off",
				navigationHAlign:"center",
				navigationVAlign:"bottom",
				navigationHOffset:0,
				navigationVOffset:20,
				soloArrowLeftHalign:"left",
				soloArrowLeftValign:"center",
				soloArrowLeftHOffset:20,
				soloArrowLeftVOffset:0,
				soloArrowRightHalign:"right",
				soloArrowRightValign:"center",
				soloArrowRightHOffset:20,
				soloArrowRightVOffset:0,
				shadow:0,
				fullWidth:"on",
				fullScreen:"off",
				spinner:"spinner0",
				stopLoop:"off",
				stopAfterLoops:-1,
				stopAtSlide:-1,
				shuffle:"off",
				autoHeight:"off",
				forceFullWidth:"off",
				hideThumbsOnMobile:"off",
				hideNavDelayOnMobile:1500,
				hideBulletsOnMobile:"off",
				hideArrowsOnMobile:"off",
				hideThumbsUnderResolution:0,
				hideSliderAtLimit:0,
				hideCaptionAtLimit:0,
				hideAllCaptionAtLilmit:0,
				startWithSlide:0					

			});
			
		});
	}

	if (jQuery("#mainslider_2").length > 0) {
		var setREVStartSize = function() {
			var	tpopt = new Object();
				tpopt.startwidth = 1150;
				tpopt.startheight = 633;
				tpopt.container = jQuery('#rev_slider_2');
				tpopt.fullScreen = "off";
				tpopt.forceFullWidth="off";

			tpopt.container.closest(".rev_slider_wrapper").css({height:tpopt.container.height()});tpopt.width=parseInt(tpopt.container.width(),0);tpopt.height=parseInt(tpopt.container.height(),0);tpopt.bw=tpopt.width/tpopt.startwidth;tpopt.bh=tpopt.height/tpopt.startheight;if(tpopt.bh>tpopt.bw)tpopt.bh=tpopt.bw;if(tpopt.bh<tpopt.bw)tpopt.bw=tpopt.bh;if(tpopt.bw<tpopt.bh)tpopt.bh=tpopt.bw;if(tpopt.bh>1){tpopt.bw=1;tpopt.bh=1}if(tpopt.bw>1){tpopt.bw=1;tpopt.bh=1}tpopt.height=Math.round(tpopt.startheight*(tpopt.width/tpopt.startwidth));if(tpopt.height>tpopt.startheight&&tpopt.autoHeight!="on")tpopt.height=tpopt.startheight;if(tpopt.fullScreen=="on"){tpopt.height=tpopt.bw*tpopt.startheight;var cow=tpopt.container.parent().width();var coh=jQuery(window).height();if(tpopt.fullScreenOffsetContainer!=undefined){try{var offcontainers=tpopt.fullScreenOffsetContainer.split(",");jQuery.each(offcontainers,function(e,t){coh=coh-jQuery(t).outerHeight(true);if(coh<tpopt.minFullScreenHeight)coh=tpopt.minFullScreenHeight})}catch(e){}}tpopt.container.parent().height(coh);tpopt.container.height(coh);tpopt.container.closest(".rev_slider_wrapper").height(coh);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(coh);tpopt.container.css({height:"100%"});tpopt.height=coh;}else{tpopt.container.height(tpopt.height);tpopt.container.closest(".rev_slider_wrapper").height(tpopt.height);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(tpopt.height);}
		};

		/* CALL PLACEHOLDER */
		setREVStartSize();


		var tpj=jQuery;
		tpj.noConflict();
		var revapi7;

		tpj(document).ready(function() {

		if(tpj('#rev_slider_2').revolution == undefined)
			revslider_showDoubleJqueryError('#rev_slider_2');
		else
		   revapi7 = tpj('#rev_slider_2').show().revolution(
			{
				dottedOverlay:"none",
				delay:14000,
				startwidth:1150,
				startheight:633,
				hideThumbs:200,
				thumbWidth:100,
				thumbHeight:50,
				thumbAmount:3,
				simplifyAll:"off",
				navigationType:"bullet",
				navigationArrows:"solo",
				navigationStyle:"round",
				touchenabled:"on",
				onHoverStop:"on",
				nextSlideOnWindowFocus:"off",
				swipe_threshold: 75,
				swipe_min_touches: 1,
				drag_block_vertical: false,
				parallax:"mouse",
				parallaxBgFreeze:"off",
				parallaxLevels:[2,4,6,8,10,12,14,16,18,20],
				parallaxDisableOnMobile:"on",
				keyboardNavigation:"off",
				navigationHAlign:"center",
				navigationVAlign:"bottom",
				navigationHOffset:0,
				navigationVOffset:20,
				soloArrowLeftHalign:"left",
				soloArrowLeftValign:"center",
				soloArrowLeftHOffset:20,
				soloArrowLeftVOffset:0,
				soloArrowRightHalign:"right",
				soloArrowRightValign:"center",
				soloArrowRightHOffset:20,
				soloArrowRightVOffset:0,
				shadow:0,
				fullWidth:"on",
				fullScreen:"off",
				spinner:"spinner0",
				stopLoop:"off",
				stopAfterLoops:-1,
				stopAtSlide:-1,
				shuffle:"off",
				autoHeight:"off",
				forceFullWidth:"off",
				hideThumbsOnMobile:"off",
				hideNavDelayOnMobile:1500,
				hideBulletsOnMobile:"off",
				hideArrowsOnMobile:"off",
				hideThumbsUnderResolution:0,
				hideSliderAtLimit:0,
				hideCaptionAtLimit:0,
				hideAllCaptionAtLilmit:0,
				startWithSlide:0					
			});
			
		});
	}

	if (jQuery("#mainslider_3").length > 0) {
		var setREVStartSize = function() {
			var	tpopt = new Object();
				tpopt.startwidth = 1150;
				tpopt.startheight = 633;
				tpopt.container = jQuery('#rev_slider_3');
				tpopt.fullScreen = "off";
				tpopt.forceFullWidth="off";

			tpopt.container.closest(".rev_slider_wrapper").css({height:tpopt.container.height()});tpopt.width=parseInt(tpopt.container.width(),0);tpopt.height=parseInt(tpopt.container.height(),0);tpopt.bw=tpopt.width/tpopt.startwidth;tpopt.bh=tpopt.height/tpopt.startheight;if(tpopt.bh>tpopt.bw)tpopt.bh=tpopt.bw;if(tpopt.bh<tpopt.bw)tpopt.bw=tpopt.bh;if(tpopt.bw<tpopt.bh)tpopt.bh=tpopt.bw;if(tpopt.bh>1){tpopt.bw=1;tpopt.bh=1}if(tpopt.bw>1){tpopt.bw=1;tpopt.bh=1}tpopt.height=Math.round(tpopt.startheight*(tpopt.width/tpopt.startwidth));if(tpopt.height>tpopt.startheight&&tpopt.autoHeight!="on")tpopt.height=tpopt.startheight;if(tpopt.fullScreen=="on"){tpopt.height=tpopt.bw*tpopt.startheight;var cow=tpopt.container.parent().width();var coh=jQuery(window).height();if(tpopt.fullScreenOffsetContainer!=undefined){try{var offcontainers=tpopt.fullScreenOffsetContainer.split(",");jQuery.each(offcontainers,function(e,t){coh=coh-jQuery(t).outerHeight(true);if(coh<tpopt.minFullScreenHeight)coh=tpopt.minFullScreenHeight})}catch(e){}}tpopt.container.parent().height(coh);tpopt.container.height(coh);tpopt.container.closest(".rev_slider_wrapper").height(coh);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(coh);tpopt.container.css({height:"100%"});tpopt.height=coh;}else{tpopt.container.height(tpopt.height);tpopt.container.closest(".rev_slider_wrapper").height(tpopt.height);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(tpopt.height);}
		};

		/* CALL PLACEHOLDER */
		setREVStartSize();


		var tpj=jQuery;
		tpj.noConflict();
		var revapi8;

		tpj(document).ready(function() {

		if(tpj('#rev_slider_3').revolution == undefined)
			revslider_showDoubleJqueryError('#rev_slider_3');
		else
		   revapi8 = tpj('#rev_slider_3').show().revolution(
			{
				dottedOverlay:"none",
				delay:14000,
				startwidth:1150,
				startheight:633,
				hideThumbs:200,
				thumbWidth:100,
				thumbHeight:50,
				thumbAmount:3,
				simplifyAll:"off",
				navigationType:"bullet",
				navigationArrows:"solo",
				navigationStyle:"round",
				touchenabled:"on",
				onHoverStop:"on",
				nextSlideOnWindowFocus:"off",
				swipe_threshold: 75,
				swipe_min_touches: 1,
				drag_block_vertical: false,
				parallax:"mouse",
				parallaxBgFreeze:"off",
				parallaxLevels:[2,4,6,8,10,12,14,16,18,20],
				parallaxDisableOnMobile:"on",
				keyboardNavigation:"off",
				navigationHAlign:"center",
				navigationVAlign:"bottom",
				navigationHOffset:0,
				navigationVOffset:20,
				soloArrowLeftHalign:"left",
				soloArrowLeftValign:"center",
				soloArrowLeftHOffset:20,
				soloArrowLeftVOffset:0,
				soloArrowRightHalign:"right",
				soloArrowRightValign:"center",
				soloArrowRightHOffset:20,
				soloArrowRightVOffset:0,
				shadow:0,
				fullWidth:"on",
				fullScreen:"off",
				spinner:"spinner0",
				stopLoop:"off",
				stopAfterLoops:-1,
				stopAtSlide:-1,
				shuffle:"off",
				autoHeight:"off",
				forceFullWidth:"off",
				hideThumbsOnMobile:"off",
				hideNavDelayOnMobile:1500,
				hideBulletsOnMobile:"off",
				hideArrowsOnMobile:"off",
				hideThumbsUnderResolution:0,
				hideSliderAtLimit:0,
				hideCaptionAtLimit:0,
				hideAllCaptionAtLilmit:0,
				startWithSlide:0
			});
		});
	}
	
	if (jQuery("#mainslider_4").length > 0) {
		jQuery('.royalslider_4').royalSlider({
			template:'default',
			image_generation:{
				imageWidth:'',
				imageHeight:'',
				thumbImageWidth:'',
				thumbImageHeight:''
			},
			thumbs:{
				thumbWidth:96,
				thumbHeight:72
			},
			autoPlay:{
				enabled:!0,
				delay:7000,
				stopAtAction:!1
			},
			block:{
				moveOffset:20,
				speed:400,
				delay:200
			},
			width:'100%',
			height:'500px',
			autoScaleSlider:!0,
			autoScaleSliderWidth:'',
			autoScaleSliderHeight:'',
			slidesSpacing:0,
			imageScalePadding:0,
			arrowsNav:!1
		});
	}

	if (jQuery("#mainslider_5").length > 0) {
		jQuery('.new-royalslider-17').royalSlider({
			template:'default',
			image_generation:{
				imageWidth:'',
				imageHeight:'',
				thumbImageWidth:'',
				thumbImageHeight:''
			},
			thumbs:{
				thumbWidth:96,
				thumbHeight:72
			},
			autoPlay:{
				enabled:!0,
				delay:7000,
				stopAtAction:!1,
				pauseOnHover:!1
			},
			fullscreen:{
				enabled:!0
			},
			block:{
				moveOffset:900,
				speed:1500,
				delay:300
			},
			width:'100%',
			height:'',
			autoHeight:!0,
			slidesSpacing:0,
			imageScaleMode:'fill',
			arrowsNav:!1,
			loop:!0,
			numImagesToPreload:2
		});
	}

	if (jQuery("#mainslider_6").length > 0) {
		jQuery('.new-royalslider-12').royalSlider({
			template:'default',
			image_generation:{
				imageWidth:'',
				imageHeight:'',
				thumbImageWidth:'',
				thumbImageHeight:''
			},
			thumbs:{
				thumbWidth:96,
				thumbHeight:72
			},
			autoPlay:{
				enabled:!0,
				delay:7000,
				stopAtAction:!1,
				pauseOnHover:!1
			},
			fullscreen:{
				enabled:!0
			},
			block:{
				moveOffset:900,
				speed:1500,
				delay:300
			},
			width:'100%',
			height:'',
			autoHeight:!0,
			slidesSpacing:0,
			imageScaleMode:'fill',
			arrowsNav:!1,
			loop:!0,
			numImagesToPreload:2
		});
	}

	if (jQuery("#mainslider_7").length > 0) {
		var setREVStartSize = function() {
			var	tpopt = new Object();
				tpopt.startwidth = 1150;
				tpopt.startheight = 350;
				tpopt.container = jQuery('#rev_slider_4_1');
				tpopt.fullScreen = "off";
				tpopt.forceFullWidth="off";

			tpopt.container.closest(".rev_slider_wrapper").css({height:tpopt.container.height()});tpopt.width=parseInt(tpopt.container.width(),0);tpopt.height=parseInt(tpopt.container.height(),0);tpopt.bw=tpopt.width/tpopt.startwidth;tpopt.bh=tpopt.height/tpopt.startheight;if(tpopt.bh>tpopt.bw)tpopt.bh=tpopt.bw;if(tpopt.bh<tpopt.bw)tpopt.bw=tpopt.bh;if(tpopt.bw<tpopt.bh)tpopt.bh=tpopt.bw;if(tpopt.bh>1){tpopt.bw=1;tpopt.bh=1}if(tpopt.bw>1){tpopt.bw=1;tpopt.bh=1}tpopt.height=Math.round(tpopt.startheight*(tpopt.width/tpopt.startwidth));if(tpopt.height>tpopt.startheight&&tpopt.autoHeight!="on")tpopt.height=tpopt.startheight;if(tpopt.fullScreen=="on"){tpopt.height=tpopt.bw*tpopt.startheight;var cow=tpopt.container.parent().width();var coh=jQuery(window).height();if(tpopt.fullScreenOffsetContainer!=undefined){try{var offcontainers=tpopt.fullScreenOffsetContainer.split(",");jQuery.each(offcontainers,function(e,t){coh=coh-jQuery(t).outerHeight(true);if(coh<tpopt.minFullScreenHeight)coh=tpopt.minFullScreenHeight})}catch(e){}}tpopt.container.parent().height(coh);tpopt.container.height(coh);tpopt.container.closest(".rev_slider_wrapper").height(coh);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(coh);tpopt.container.css({height:"100%"});tpopt.height=coh;}else{tpopt.container.height(tpopt.height);tpopt.container.closest(".rev_slider_wrapper").height(tpopt.height);tpopt.container.closest(".forcefullwidth_wrapper_tp_banner").find(".tp-fullwidth-forcer").height(tpopt.height);}
		};

		/* CALL PLACEHOLDER */
		setREVStartSize();


		var tpj=jQuery;
		tpj.noConflict();
		var revapi4;

		tpj(document).ready(function() {

		if(tpj('#rev_slider_4_1').revolution == undefined)
			revslider_showDoubleJqueryError('#rev_slider_4_1');
		else
		   revapi4 = tpj('#rev_slider_4_1').show().revolution(
			{
				dottedOverlay:"none",
				delay:7000,
				startwidth:1150,
				startheight:350,
				hideThumbs:200,
				thumbWidth:100,
				thumbHeight:50,
				thumbAmount:5,
				minHeight:350,
				simplifyAll:"off",
				navigationType:"thumb",
				navigationArrows:"solo",
				navigationStyle:"round",
				touchenabled:"on",
				onHoverStop:"on",
				nextSlideOnWindowFocus:"off",
				swipe_threshold: 75,
				swipe_min_touches: 1,
				drag_block_vertical: false,
				parallax:"mouse",
				parallaxBgFreeze:"off",
				parallaxLevels:[5,10,15,20,25,30,35,40,45,50],
				parallaxDisableOnMobile:"on",
				keyboardNavigation:"off",
				navigationHAlign:"center",
				navigationVAlign:"bottom",
				navigationHOffset:0,
				navigationVOffset:20,
				soloArrowLeftHalign:"left",
				soloArrowLeftValign:"center",
				soloArrowLeftHOffset:20,
				soloArrowLeftVOffset:0,
				soloArrowRightHalign:"right",
				soloArrowRightValign:"center",
				soloArrowRightHOffset:20,
				soloArrowRightVOffset:0,
				shadow:0,
				fullWidth:"on",
				fullScreen:"off",
				spinner:"spinner0",
				stopLoop:"off",
				stopAfterLoops:-1,
				stopAtSlide:-1,
				shuffle:"off",
				autoHeight:"off",
				forceFullWidth:"off",
				hideThumbsOnMobile:"off",
				hideNavDelayOnMobile:1500,
				hideBulletsOnMobile:"off",
				hideArrowsOnMobile:"off",
				hideThumbsUnderResolution:0,
				hideSliderAtLimit:0,
				hideCaptionAtLimit:0,
				hideAllCaptionAtLilmit:0,
				startWithSlide:0					
			});
		});
	}

}

/*reviews stars*/
function reviews_stars() {
    "use strict";
    if (jQuery(".ratingItem").length > 0) {
        jQuery(".ratingItem").each(function() {
            var percent = jQuery(this).attr("data-mark");
            jQuery(this).find('.starHover').css({'width': percent + '%'});
        });
    }
}

/* arc skills legend color */
function arc_skills_legend_color() {
    "use strict";

    if (jQuery(".sc_skills_arc").length > 0) {
        jQuery(".sc_skills_arc").find("li:nth-child(1)", '.sc_skills_legend').css({'background-color': jQuery(".arc:nth-child(1) input.color", ".sc_skills_arc").attr("value")});
        jQuery(".sc_skills_arc").find("li:nth-child(2)", '.sc_skills_legend').css({'background-color': jQuery(".arc:nth-child(2) input.color", ".sc_skills_arc").attr("value")});
        jQuery(".sc_skills_arc").find("li:nth-child(3)", '.sc_skills_legend').css({'background-color': jQuery(".arc:nth-child(3) input.color", ".sc_skills_arc").attr("value")});
        jQuery(".sc_skills_arc").find("li:nth-child(4)", '.sc_skills_legend').css({'background-color': jQuery(".arc:nth-child(4) input.color", ".sc_skills_arc").attr("value")});
        jQuery(".sc_skills_arc").find("li:nth-child(5)", '.sc_skills_legend').css({'background-color': jQuery(".arc:nth-child(5) input.color", ".sc_skills_arc").attr("value")});
    }
}

// Price range slider
function price_filter() {
    if (jQuery("#slider-range").length > 0) {
        jQuery("#slider-range").slider({
            range: true,
            min: 0,
            max: 200,
            values: [0, 200],
            slide: function(event, ui) {
                jQuery("#amount").val("£" + ui.values[0] + " - £" + ui.values[1]);
            }
        });
        jQuery("#amount").val("£" + jQuery("#slider-range").slider("values", 0) +
            " - £" + jQuery("#slider-range").slider("values", 1));
    }
}

// Select review stars
function woo_review_star() {
    if (jQuery(".stars", "#review_form").length > 0) {
        $(".stars").find("a").on("click", function() {
            $("a.active").removeClass("active");
            $(this).addClass("active");
            return false;
        });
    }
}


// Hover effects on mobile
function hover_mobile() {
    if (jQuery("#mobile_tap_hover").length > 0) {
        $('a.taphover').on("touchstart", function(e) {
            var link = $(this); //preselect the link
            if (link.hasClass('hover')) {
                return true;
            } else {
                link.addClass("hover");
                //$(this).parent().css({'z-index': 1});
                $('a.taphover').not(this).removeClass("hover");
                e.preventDefault();
                return false; //extra, and to make sure the function has consistent return points
            }
        });

        $('.imgNav.taphover').on("touchstart", function(e) {
            var link = $(this); //preselect the link
            if (link.hasClass('hover')) {
                $('.itemNext.taphover').not(this).removeClass("hover");
                $('.itemPrev.taphover').not(this).removeClass("hover");
                return true;
            } else {
                link.addClass("hover");
                $('.imgNav.taphover').not(this).removeClass("hover");
                e.preventDefault();
                return false; //extra, and to make sure the function has consistent return points
            }
        });
    }
}