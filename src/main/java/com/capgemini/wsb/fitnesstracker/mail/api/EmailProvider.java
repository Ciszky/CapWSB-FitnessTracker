package com.capgemini.wsb.fitnesstracker.mail.api;

import java.util.List;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

/**
 * Interface for providing email services.
 */
public interface EmailProvider {

    /**
     * Sends an email with a list of trainings.
     *
     * @param to the recipient's email address
     * @param subject the subject of the email
     * @param trainingList the list of trainings to include in the email
     * @return an EmailDto object containing the details of the sent email
     */
    EmailDto sendMail(String to, String subject, List<Training> trainingList);
}
