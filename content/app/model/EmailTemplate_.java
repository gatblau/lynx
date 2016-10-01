package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmailTemplate.class)
public class EmailTemplate_ {
    public static volatile SingularAttribute<EmailTemplate, Integer> id;
    public static volatile SingularAttribute<EmailTemplate, String> name;
    public static volatile SingularAttribute<EmailTemplate, String> description;
    public static volatile SingularAttribute<EmailTemplate, String> subject;
    public static volatile SingularAttribute<EmailTemplate, String> body;
}
