package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO - napisac query

        return entityManager.createQuery("select visit.patient from VisitEntity visit " +
                        "where visit.doctor.firstName = :firstName and visit.doctor.lastName = :lastName", PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();

    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO - napisac query
        return entityManager.createQuery("select distinct patient from PatientEntity patient " +
                        "join patient.visits visit " +
                        "join visit.medicalTreatments medicalTreatment " +
                        "where medicalTreatment.type = :treatmentType", PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO - napisac query

         return entityManager.createQuery("select distinct patient from PatientEntity patient " +
                            "join patient.addresses patientAddress " +
                            "join DoctorEntity doctor on doctor.firstName = :firstName and doctor.lastName = :lastName " +
                            "join doctor.addresses doctorAddress " +
                            "where patientAddress = doctorAddress", PatientEntity.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getResultList();

    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO - napisac query

        return entityManager.createQuery("select patient from PatientEntity patient " +
                        "left join patient.addresses address " +
                        "where address is null", PatientEntity.class)
                .getResultList();
    }
}