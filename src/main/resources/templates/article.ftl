<!DOCTYPE html>
<head>


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
                <#if ctx??>
                    <#if ctx.admin>
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


<!-- content
================================================== -->

<section id="content-wrap" class="blog-single">
    <div class="row">
        <div class="col-twelve">

            <article class="format-standard">
                <a href="/articles">Back to articles</a>

                <div class="primary-content">

                    <h1 class="page-title">${article.title}</h1>

                    <p>${article.text}</p>

                    <div class="author-profile">
                        <img src="images/avatars/user-05.jpg" alt="">

                    </div> <!-- end author-profile -->

                </div> <!-- end entry-primary -->
            </article>


        </div> <!-- end col-twelve -->
    </div> <!-- end row -->

    <div class="comments-wrap">
        <div id="comments" class="row">
            <div class="col-full">

                <!-- respond -->
                <div class="respond">

                    <h3>Leave a Comment</h3>

                    <form name="contactForm" id="contactForm" method="post" action="/comments">
                        <fieldset>

                            <div class="form-field">
                                <#if ctx?? >
                                    <h1>${ctx.name}</h1>
                                    <input name="username" type="hidden" id="cName" class="full-width" placeholder="${ctx.name}"
                                           value="${ctx.name}">
                                <#else>
                                    <input name="username" type="text" id="cName" class="full-width" placeholder="Your Name"
                                           value="">
                                </#if>


                            </div>

                            <div class="message form-field">
                                <textarea name="text" id="cMessage" class="full-width"
                                          placeholder="Your Message"></textarea>
                            </div>

                            <input id="current" name="current" type="hidden" value="${article.id}">

                            <button type="submit" class="submit button-primary">Submit</button>

                        </fieldset>
                    </form> <!-- Form End -->

                </div> <!-- Respond End -->


                <h3>${comments?size} Comments</h3>

                <!-- commentlist -->
                <ol class="commentlist">

                    <#list comments as comment>

                        <li class="depth-1">

                        <div class="avatar">
                            <img width="50" height="50" class="avatar" src="images/avatars/user-01.jpg" alt="">
                        </div>

                        <div class="comment-content">

                        <div class="comment-info">
                            <cite>${comment.username}</cite>

                            <div class="comment-meta">
                                <time class="comment-time" datetime="2014-07-12T23:05">${comment.datecreation}
                                </time>
                                <#if ctx?? && ctx.admin>
                                    <span class="sep">/</span>
                                    <a href="/comments/${comment.id}?action=delete" class="red"><i class="fas fa-trash"></i></a>
                                </#if>
                            </div>
                        </div>

                        <div class="comment-text">
                        <p>${comment.text}</p>
                        </div>

                        </div>

                        </li>
                    </#list>


                </ol> <!-- Commentlist End -->

            </div> <!-- end col-full -->
        </div> <!-- end row comments -->
    </div> <!-- end comments-wrap -->

</section> <!-- end content -->


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
