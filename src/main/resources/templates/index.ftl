<#-- @ftlvariable name="data" type="zerah.alexandre.cms.tpl.IndexContext" -->


<!DOCTYPE html>
<head>

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>CMS</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

</head>

<body id="top">

<!-- header
================================================== -->
<header class="short-header">

    <div class="gradient-block"></div>

    <div class="row header-content">

        <div class="logo">
            <a href="/">Author</a>
        </div>

        <nav id="main-nav-wrap">
            <ul class="main-navigation sf-menu">
                <li class="current"><a href="/" title="">Home</a></li>
                <#if session??>
                    <#if session.admin>
                        <li><a href="/admin" title="">Dashboard</a></li>
                        <li><a href="/logout" title="">Logout</a></li>
                    <#else >
                        <li><a href="/logout" title="">Logout</a></li>
                    </#if>
                <#else>
                    <li><a href="/login" title="">Login</a></li>
                    <li><a href="/register" title="">Register</a></li>
                </#if>
            </ul>
        </nav> <!-- end main-nav-wrap -->

        <div class="search-wrap">

            <form role="search" method="get" class="search-form" action="#">
                <label>
                    <span class="hide-content">Search for:</span>
                    <input type="search" class="search-field" placeholder="Type Your Keywords" value="" name="s"
                           title="Search for:" autocomplete="off">
                </label>
                <input type="submit" class="search-submit" value="Search">
            </form>

            <a href="#" id="close-search" class="close-btn">Close</a>

        </div> <!-- end search wrap -->

        <div class="triggers">
            <a class="search-trigger" href="#"><i class="fa fa-search"></i></a>
            <a class="menu-toggle" href="#"><span>Menu</span></a>
        </div> <!-- end triggers -->

    </div>

</header> <!-- end header -->


<!-- masonry
================================================== -->
<section id="bricks">

    <div class="row masonry">
        <!-- brick-wrapper -->
        <div class="bricks-wrapper">

            <div class="grid-sizer"></div>
            <#list list as item>
                <article class="brick entry format-standard animate-this">

                <div class="entry-thumb">
                        <img src="https://picsum.photos/700/800" alt="picture">

                </div>

                <div class="entry-text">
                <div class="entry-header">


                <h1 class="entry-title"><a href="/articles/${item.id}">${item.title}</a></h1>

                </div>
            <div id="text-${item.id}" class="entry-excerpt">

                <#if item.text?length &gt; 100>
                <script>
                    var t = document.createTextNode("${item.text?js_string}".slice(0, 250) + "... [see more]");
                    var p = document.createElement("p");
                    p.append(t);
                    document.getElementById("text-${item.id}").append(p)


                </script>
                <#else>
                    ${item.text}
                </#if>


                </div>
                </div>
                </article>
            </#list>

            <!-- end article -->
        </div> <!-- end brick-wrapper -->

    </div> <!-- end row -->

</section> <!-- end bricks -->


<!-- footer
================================================== -->
<footer>

    <div class="footer-main">

        <div class="row">

            <div class="col-four tab-full mob-full footer-info">

                <h4>About Our Site</h4>

                <p>
                    Lorem ipsum Ut velit dolor Ut labore id fugiat in ut fugiat nostrud qui in dolore commodo eu magna
                    Duis cillum dolor officia esse mollit proident Excepteur exercitation nulla. Lorem ipsum In
                    reprehenderit commodo aliqua irure labore.
                </p>

            </div> <!-- end footer-info -->

            <div class="col-two tab-1-3 mob-1-2 site-links">

                <h4>Site Links</h4>

                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">Terms</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                </ul>

            </div> <!-- end site-links -->

            <div class="col-two tab-1-3 mob-1-2 social-links">

                <h4>Social</h4>

                <ul>
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">Facebook</a></li>
                    <li><a href="#">Dribbble</a></li>
                    <li><a href="#">Google+</a></li>
                    <li><a href="#">Instagram</a></li>
                </ul>

            </div> <!-- end social links -->

            <div class="col-four tab-1-3 mob-full footer-subscribe">

                <h4>Subscribe</h4>

                <p>Keep yourself updated. Subscribe to our newsletter.</p>

                <div class="subscribe-form">

                    <form id="mc-form" class="group" novalidate="true">

                        <input type="email" value="" name="dEmail" class="email" id="mc-email"
                               placeholder="Type &amp; press enter" required="">

                        <input type="submit" name="subscribe">

                        <label for="mc-email" class="subscribe-message"></label>

                    </form>

                </div>

            </div> <!-- end subscribe -->

        </div> <!-- end row -->

    </div> <!-- end footer-main -->

    <div class="footer-bottom">
        <div class="row">

            <div class="col-twelve">
                <div class="copyright">
                    <span>© Copyright Abstract 2016</span>
                    <span>Design by <a href="http://www.styleshout.com/">styleshout</a></span>
                </div>

                <div id="go-top">
                    <a class="smoothscroll" title="Back to Top" href="#top"><i class="icon icon-arrow-up"></i></a>
                </div>
            </div>

        </div>
    </div> <!-- end footer-bottom -->

</footer>

<div id="preloader">
    <div id="loader"></div>
</div>

</body>

</html>
