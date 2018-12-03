package com.dfci.scheduler.resources;

import com.codahale.metrics.annotation.Timed;
import com.dfci.scheduler.model.Patient;
import com.dfci.scheduler.db.PatientDAO;
import com.dfci.scheduler.resources.error.ErrorEntity;
import com.dfci.scheduler.resources.error.HttpResponseCodes;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import static com.dfci.scheduler.resources.error.HttpResponseCodes.HTTP_BAD_REQUEST;
import static com.dfci.scheduler.resources.error.HttpResponseCodes.HTTP_NOT_FOUND;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static javax.ws.rs.core.Response.Status.CREATED;

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
    public Response findPatients(@QueryParam("first_name") Optional<String> firstNameMaybe,
                                 @QueryParam("last_name") Optional<String> lastNameMaybe,
                                 @QueryParam("date_of_birth") Optional<String> dateOfBirthMaybe) {

        logger.debug("findPatients called");

        if (!firstNameMaybe.isPresent() || isNullOrEmpty(firstNameMaybe.get()) ||
            !lastNameMaybe.isPresent() || isNullOrEmpty(lastNameMaybe.get()) ||
            !dateOfBirthMaybe.isPresent() || isNullOrEmpty(dateOfBirthMaybe.get())) {

            return Response.status(HTTP_BAD_REQUEST)
                    .entity(new ErrorEntity("INVALID_PARAMETER", "All of first_name, last_name and date_of_birth must be specified"))
                    .type("application/json")
                    .build();
        }

        try {
            LocalDate date = LocalDate.parse(dateOfBirthMaybe.get(), ISO_LOCAL_DATE);
            List<Patient> patients = patientDAO.find(firstNameMaybe.get(), lastNameMaybe.get(), date);

            return Response.status(CREATED)
                    .entity(patients)
                    .build();
        } catch (DateTimeParseException dtpe) {
            logger.info("Error parsing date " + dateOfBirthMaybe.get(), dtpe);

            return Response.status(HTTP_BAD_REQUEST)
                    .entity(new ErrorEntity("INVALID_PARAMETER", "Unable to parse '" + dateOfBirthMaybe.get() + "' as ISO Date."))
                    .type("application/json")
                    .build();
        }
    }

    @POST
    @Timed
    @UnitOfWork
    public Response addPatient(Patient patient) {

        Patient createdPatient = patientDAO.create(patient);


        return Response.status(CREATED)
                .entity(createdPatient)
                .build();
    }

    @DELETE
    @Path("/{patient_id}")
    @Timed
    @UnitOfWork
    public Response deletePatient(@PathParam("patient_id") Long patientId) {

        Optional<Patient> patientMaybe = patientDAO.findById(patientId);

        if (!patientMaybe.isPresent()) {
            return Response.status(HTTP_NOT_FOUND)
                    .entity(new ErrorEntity("NOT_FOUND", "Unable to find patient  '" + patientId + "'."))
                    .type("application/json")
                    .build();
        }

        patientDAO.delete(patientMaybe.get());

        return Response.status(Response.Status.OK).build();
    }
}
