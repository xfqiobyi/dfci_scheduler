package com.dfci.scheduler;

import com.dfci.scheduler.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ChemoSchedulerApplication extends Application<ChemoSchedulerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ChemoSchedulerApplication().run(args);
    }

    @Override
    public String getName() {
        return "ChemoScheduler";
    }

    @Override
    public void initialize(final Bootstrap<ChemoSchedulerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(final ChemoSchedulerConfiguration configuration,
                    final Environment environment) {

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getAppendix()
        );

        environment.jersey().register(resource);
    }

}
