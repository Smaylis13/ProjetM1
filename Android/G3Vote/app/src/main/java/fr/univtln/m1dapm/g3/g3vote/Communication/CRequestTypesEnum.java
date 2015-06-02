package fr.univtln.m1dapm.g3.g3vote.Communication;

import java.io.Serializable;

/**
 * Created by Ookami on 18/05/2015.
 */
public enum CRequestTypesEnum implements Serializable {
    generate_keys,
    auth_user,
    log_user,
    get_user,
    add_new_user,
    delete_user,
    add_new_vote,
    get_vote,
    get_votes,
    get_candidates,
    add_contact,
    get_contacts
}
