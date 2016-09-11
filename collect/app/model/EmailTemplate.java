package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="EmailTemplate")
@Table (name="\"email_template\"")
@NamedQueries ({
	 @NamedQuery(name="EmailTemplate.findAll", query="SELECT a FROM EmailTemplate a")
	,@NamedQuery(name="EmailTemplate.findByName", query="SELECT a FROM EmailTemplate a WHERE a.name = :name")
	,@NamedQuery(name="EmailTemplate.findByNameContaining", query="SELECT a FROM EmailTemplate a WHERE a.name like :name")

	,@NamedQuery(name="EmailTemplate.findByDescription", query="SELECT a FROM EmailTemplate a WHERE a.description = :description")
	,@NamedQuery(name="EmailTemplate.findByDescriptionContaining", query="SELECT a FROM EmailTemplate a WHERE a.description like :description")

	,@NamedQuery(name="EmailTemplate.findBySubject", query="SELECT a FROM EmailTemplate a WHERE a.subject = :subject")
	,@NamedQuery(name="EmailTemplate.findBySubjectContaining", query="SELECT a FROM EmailTemplate a WHERE a.subject like :subject")

	,@NamedQuery(name="EmailTemplate.findByBody", query="SELECT a FROM EmailTemplate a WHERE a.body = :body")
	,@NamedQuery(name="EmailTemplate.findByBodyContaining", query="SELECT a FROM EmailTemplate a WHERE a.body like :body")

})
public class EmailTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "EmailTemplate.findAll";
    public static final String FIND_BY_NAME = "EmailTemplate.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="EmailTemplate.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "EmailTemplate.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="EmailTemplate.findByDescriptionContaining";
    public static final String FIND_BY_SUBJECT = "EmailTemplate.findBySubject";
    public static final String FIND_BY_SUBJECT_CONTAINING ="EmailTemplate.findBySubjectContaining";
    public static final String FIND_BY_BODY = "EmailTemplate.findByBody";
    public static final String FIND_BY_BODY_CONTAINING ="EmailTemplate.findByBodyContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description; 

    @Column(name="subject"  , length=250 , nullable=false , unique=false)
    private String subject; 

    @Column(name="body"   , nullable=false , unique=false)
    private String body; 

    public EmailTemplate() {
    }

    public EmailTemplate(
       Integer id,
       String name,
       String description,
       String subject,
       String body) {
	 this(
       id,
       name,
       description,
       subject,
       body
	 ,true);
	}
    
	public EmailTemplate(
       Integer id,
       String name,
       String description,
       String subject,
       String body	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       setSubject (subject);
       setBody (body);
    }

	public EmailTemplate flat() {
	   return new EmailTemplate(
          getId(),
          getName(),
          getDescription(),
          getSubject(),
          getBody()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }

    public String getDescription() {
        return description;
    }
	
    public void setDescription (String description) {
        this.description =  description;
    }

    public String getSubject() {
        return subject;
    }
	
    public void setSubject (String subject) {
        this.subject =  subject;
    }

    public String getBody() {
        return body;
    }
	
    public void setBody (String body) {
        this.body =  body;
    }
}
