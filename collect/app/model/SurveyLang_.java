package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SurveyLang.class)
public class SurveyLang_ {
	public static volatile SingularAttribute<SurveyLang, Integer> id;
	public static volatile SingularAttribute<SurveyLang, String> name;
    public static volatile SingularAttribute<SurveyLang, String> description;
    public static volatile SingularAttribute<SurveyLang, Language> languageId;
    public static volatile SingularAttribute<SurveyLang, Integer> languageId_;
    public static volatile SingularAttribute<SurveyLang, Survey> surveyId;
    public static volatile SingularAttribute<SurveyLang, Integer> surveyId_;
}
