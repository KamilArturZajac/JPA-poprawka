package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) { // TODO - napisac query

        return entityManager.createQuery("select doctor from DoctorEntity doctor " +
                        "where doctor.specialization = :specialization", DoctorEntity.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) { // TODO - napisac query
        return entityManager.createQuery("select count(v) from VisitEntity v " +
                        "join v.doctor d " +
                        "join v.patient p " +
                        "where d.firstName = :docFirstName " +
                        "and d.lastName = :docLastName " +
                        "and p.firstName = :patientFirstName " +
                        "and p.lastName = :patientLastName", Long.class)
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName)
                .getSingleResult();
    }
}
