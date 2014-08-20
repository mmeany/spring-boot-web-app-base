Overview
========
This is a starter application based on Spring Boot, including the following technologies already baked in:

* Spring Web MVC 4
* Spring Security with a custom UserDetailsService that authenticates against the HSQL Database
* Apache Tiles 3
* Spring Data JPA
* HSQL Database, embedded with persistent store on disk
* Twitter Bootstrap
* JQuery and JQuery Validation

First Time Database Configuation
================================
File mentioned here can be found in: src/main/resources

To create the database on first run of the application change line 6 of application.properties from:

    spring.jpa.hibernate.ddl-auto: 

to:

    spring.jpa.hibernate.ddl-auto: create

This will cause the database to be dropped and recreated, deleting it if it exists. To get the test data loaded as the same time,
make a copy of data-hidden.sql in the same directory and name it data.sql.

Don't forget to change application.properies back to its initial state and delete data.sql once you have recreated the database.

Making user details available through Tiles
===========================================
See the Tiles configuration for customised View Preparer that makes the currently signed in user object available as an attribute that cascades. This is defined on the basic.jsp template making it available to all pages and in particular is used in the header.jsp to display the signed in message.

Custom Tags
===========
In the src/main/webapp/WEB-INF/tags folder are some custom tags. Of interest here is the pagination tag that can be used in conjunction with Spring Data Page and Pageable implementations to provide a quick and easy means of paginating through a data set. The List controller and view provide an example.

Pagination
The pagination tag is a bit of a monster, so much easier to do this in code, but there you go :)

To use the tag, put it on a page which has a Spring Data 'Page' object in the model as attribute 'page'.

JavaScript Enhancements
=======================
There is a customisation that is run on page load that adds an on-click handler to any <a> tags decorated with a data-ajax-load attribute. Such links will load data into the current page using JQuery Ajax. The value of the data-ajax-load attribute should be the id of the load target (a DIV for example). JQuery goes further than this and will look at the loaded data to see if it contains an element with the same id and if so will extract the content of that element and inject it in the element with corresponding name in the current document. This gives smooth page transitions, but there are caveats - the browser location is not updated to reflect the loaded data.

The site javascript also adds an on-load event that see if there is a function called pageInit() defined on the current page. If so, that function is invoked, with no parameters. This provides a easy means of defining a block of Javascript that has to run when a page is loaded without having to worry where that code is located, it can be in a JS file or in the body tile.

JQuery Form Validation is included and can be seen on the login page. see http://jqueryvalidation.org/documentation/#list-of-built-in-validation-methods

Likewise, automatic Spring validation is included and can be seen on the login page.

Issues
======
Known issues - try to resolve, but not show stoppers

. When paging through a list of results the location bar does not change. Unable to bookmark a
  specific page of results.

Todo
====
This project will not be considered finished until the following items have been completed.

. Allow UserAuthorities to be selected from list of all available
. Implement a custom validator for cross field checks

Done
====
This sections contains entries from the Todo section copied into it once they have been completed.

. UserAthorities - remove the numeric primary key as it is not needed
. Implement JQuery form validation
. Change logging implementation to slf4j using log4j.

Nice to have
============

. JQuery validation to use same messages as server side validator.
. Internationalise
. Separate UserAuthorities and Roles. A Role would comprise of a collection of authorities.
. Code generation, Velocity based, for adding new forms and controllers

Documentation
=============
. How to add a new view to tiles
. The custom Form tags - what and why
. How to add a new form
  . Model object and jpa annotations
  . Form object and validation annotations
  . JSP form and JQuery validation
  . Controller
    . Injection of domain objects
    . Javax validation
. About Spring Data repositories
