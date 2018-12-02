package com.dfci.scheduler;

import com.dfci.scheduler.core.Patient;
import com.dfci.scheduler.db.PatientDAO;
import com.dfci.scheduler.resources.PatientResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ChemoSchedulerApplication extends Application<ChemoSchedulerConfiguration> {

    private final HibernateBundle<ChemoSchedulerConfiguration> hibernateBundle =
            new HibernateBundle<ChemoSchedulerConfiguration>(Patient.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ChemoSchedulerConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new ChemoSchedulerApplication().run(args);
    }

    @Override
    public String getName() {
        return "ChemoScheduler";
    }

    @Override
    public void initialize(final Bootstrap<ChemoSchedulerConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<ChemoSchedulerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ChemoSchedulerConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final ChemoSchedulerConfiguration configuration,
                    final Environment environment) {

        final PatientDAO patientDAO = new PatientDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new PatientResource(patientDAO));
    }

}
