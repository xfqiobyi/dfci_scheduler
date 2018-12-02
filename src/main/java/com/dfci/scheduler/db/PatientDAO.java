package com.dfci.scheduler.db;

import com.dfci.scheduler.core.Patient;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PatientDAO extends AbstractDAO<Patient> {

    public PatientDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Patient create(Patient person) {
        return persist(person);
    }

    @SuppressWarnings("unchecked")
    public List<Patient> findAll() {
        return list((Query<Patient>) namedQuery("com.dfci.scheduler.core.Patient.findAll"));
    }
}