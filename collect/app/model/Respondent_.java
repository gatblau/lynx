package model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Respondent.class)
public class Respondent_ {
	public static volatile SingularAttribute<Respondent, Integer> id;
    public static volatile SingularAttribute<Respondent, String> firstname;
    public static volatile SingularAttribute<Respondent, String> lastname;
    public static volatile SingularAttribute<Respondent, String> email;
    public static volatile SingularAttribute<Respondent, String> telephone;
    public static volatile SingularAttribute<Respondent, String> pwdHash;
    public static volatile SingularAttribute<Respondent, Boolean> enabled;
    public static volatile SingularAttribute<Respondent, String> activationCode;
    public static volatile SingularAttribute<Respondent, Country> countryId;
    public static volatile SingularAttribute<Respondent, Integer> countryId_;
    public static volatile SingularAttribute<Respondent, Group> groupId;
    public static volatile SingularAttribute<Respondent, Integer> groupId_;
    public static volatile SingularAttribute<Respondent, Language> preferredLanguageId;
    public static volatile SingularAttribute<Respondent, Integer> preferredLanguageId_;
    public static volatile SingularAttribute<Respondent, Role> roleId;
    public static volatile SingularAttribute<Respondent, Integer> roleId_;
    public static volatile SetAttribute<Respondent, Survey> surveyRespondentViaLockedBy;
    public static volatile SetAttribute<Respondent, SurveyAdmin> surveyAdminRespondentViaRespondentId;
    public static volatile SetAttribute<Respondent, SurveyRespondent> surveyRespondentRespondentViaRespondentId;
}
