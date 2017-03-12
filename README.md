## Synopsis

This project is an example of how we can use Jsoup, and the goal is to crawl a web page at basic level

## Code Example

**PageCrawlerBuilder** is probably the strongest class in this project, is a simple builder of WebPage which sets via functions to connect and set all 
the basics states of a web page. As a library You can use something like:
WebPage webPage = builder.buildConnection()
                         .buildAnchors()
                         .buildDocumentVersion()
                         .buildHeaders()
                         .buildTitle()
                         .buildLoginForm()
                         .buildNumExternalLinks()
                         .buildNumInternalLinks()
                         .build();

## Installation

You can install running the jar via command line like:
java -jar target/com.webcrawler-0.0.1-SNAPSHOT.jar.original
or
java -jar com.webcrawler-0.0.1-SNAPSHOT.jar.original

## API Reference

Once you run the server you can use the API:
http://localhost:8080/crawl?url=http://domain.com/
or go to the Web interface
http://localhost:8080/index.html

## Assumptions
The idea of create a factory of elements was interesting, but I decided to create only 2 factories
one for Heading factories, which basically only fetch the strings of the h1, h2, until h6, but for the case of the links, I decided created one custom
That Factory matches better the requirement to know if is an external or internal, or in case intentionally to know the text of the anchor, ´
despite that the name is not needed, the URL will be showed in the tab of Anchors. 
The Builder was an idea that came after thinking what is the best behavior for a tool to Crawl a page, is interesting to see how big can be a 
WebPage object, depending of the web site to crawl, but still with the Builder we can decide how and what are those objects most relevant to crawl.
build method its the most important function finally, because is the way how we build the object to return. 
I was thinking to create a service between a Controller and the Builder to have something like
return this.service( URL ); 
but I think that is not big deal to create a extra layer only because only looks nice, since the scope was for a specific task, but as always is possible discuss it.
The part of Login page was hard to decide how we can handle that part, since the way how to know if the web site has or not a login form. 
In that case, I made a set of conditions where according to a few common used words like sign up, login or join into the form to detect if is or not.
