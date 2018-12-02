package com.dfci.scheduler.resources;

import com.codahale.metrics.annotation.Timed;
import com.dfci.scheduler.core.Patient;
import com.dfci.scheduler.db.PatientDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientResource {

    private static final Logger logger = LoggerFactory.getLogger(PatientResource.class);

    private final PatientDAO patientDAO;

    public PatientResource(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Patient> fetchAllPatients() {

        logger.debug("fetch all patients called");

        return patientDAO.findAll();
    }
}
