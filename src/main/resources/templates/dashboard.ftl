<!DOCTYPE html>
<!--[if IE 8 ]>
<html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]>
<html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>CMS</title>
    <meta name="description" content="">
    <meta name="author" content="">

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
    <div id="createBtn">
        <a href="/articles/create"><i class="fas fa-plus"></i>Article</a>
        <a href="/admin/users"><i class="fas fa-exchange"></i>Manage users</a>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>


        <#list list as item>
            <tr style="text-align: center">
            <td># ${item.id}</td>
            <td>
        <a href="/articles/${item.id}">${item.title}</a>
            </td>
            <td>
        <a href="/articles/${item.id}?action=delete" ><i class="fas fa-trash"></i></a>
            </td>
            </tr>

        </#list>
        </tbody>
    </table>
    <!-- end article -->
    </div> <!-- end brick-wrapper -->

</section> <!-- end bricks -->


<div id="preloader">
    <div id="loader"></div>
</div>

</body>

</html>
