package com.dfci.scheduler.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    private final AtomicLong counter;
    private final String appendix;

    public HelloWorldResource(String appendix) {
        this.appendix = appendix;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> nameMaybe) {

        logger.debug("sayHello called with " + nameMaybe);

        final String value = "Hello " + nameMaybe.orElse("no name") + "; " + appendix;
        return new Saying(counter.incrementAndGet(), value);
    }
}

class Saying {
    private long id;

    @JsonProperty
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}