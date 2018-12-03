package com.dfci.scheduler.db;

import com.dfci.scheduler.model.Patient;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PatientDAO extends AbstractDAO<Patient> {

    public PatientDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Patient create(Patient patient) {
        return persist(patient);
    }

    public void delete(Patient patient) {
        currentSession().delete(patient);
    }

    @SuppressWarnings("unchecked")
    public List<Patient> findAll() {
        return list((Query<Patient>) namedQuery("com.dfci.scheduler.model.Patient.findAll"));
    }

    @SuppressWarnings("unchecked")
    public List<Patient> find(String firstName, String lastName, LocalDate dateOfBirth) {
        Query query = namedQuery("com.dfci.scheduler.model.Patient.find")
                .setParameter("fName", firstName)
                .setParameter("lName", lastName)
                .setParameter("dob", dateOfBirth);


        return list((Query<Patient>)query);
    }
}