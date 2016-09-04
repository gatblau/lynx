package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SurveyDefLang.class)
public class SurveyDefLang_ {
	public static volatile SingularAttribute<SurveyDefLang, Integer> id;
	public static volatile SingularAttribute<SurveyDefLang, String> name;
    public static volatile SingularAttribute<SurveyDefLang, String> description;
    public static volatile SingularAttribute<SurveyDefLang, Language> languageId;
    public static volatile SingularAttribute<SurveyDefLang, Integer> languageId_;
    public static volatile SingularAttribute<SurveyDefLang, SurveyDef> surveyDefId;
    public static volatile SingularAttribute<SurveyDefLang, Integer> surveyDefId_;
}
