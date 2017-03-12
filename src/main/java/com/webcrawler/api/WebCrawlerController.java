package com.webcrawler.api;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webcrawler.crawler.PageCrawlerBuilder;
import com.webcrawler.domain.WebPage;

@RestController
public class WebCrawlerController {

    @GetMapping("/crawl")
    public WebPage getPage(@RequestParam String url) throws IOException {
        PageCrawlerBuilder builder = new PageCrawlerBuilder(url);
        WebPage webPage = builder.buildConnection()
                         .buildAnchors()
                         .buildDocumentVersion()
                         .buildHeaders()
                         .buildTitle()
                         .buildLoginForm()
                         .buildNumExternalLinks()
                         .buildNumInternalLinks()
                         .build();
        return webPage;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @GetMapping("/error")
    public String handleAppException(Exception ex) {
        return ex.getMessage();
    }

}
