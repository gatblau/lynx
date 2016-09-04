package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="SurveyLang")
@Table (name="\"survey_lang\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyLang.findAll", query="SELECT a FROM SurveyLang a")
	,@NamedQuery(name="SurveyLang.findByName", query="SELECT a FROM SurveyLang a WHERE a.name = :name")
	,@NamedQuery(name="SurveyLang.findByNameContaining", query="SELECT a FROM SurveyLang a WHERE a.name like :name")
    ,@NamedQuery(name="SurveyLang.findByDescription", query="SELECT a FROM SurveyLang a WHERE a.description = :description")
	,@NamedQuery(name="SurveyLang.findByDescriptionContaining", query="SELECT a FROM SurveyLang a WHERE a.description like :description")
})
public class SurveyLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyLang.findAll";
    public static final String FIND_BY_NAME = "SurveyLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="SurveyLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "SurveyLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="SurveyLang.findByDescriptionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=200 , nullable=false , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Survey surveyId;  

    @Column(name="survey_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyId_;

    public SurveyLang() {
    }

    public SurveyLang(
       Integer id,
       String name,
       String description,
       Integer surveyId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       surveyId,
       languageId
	 ,true);
	}
    
	public SurveyLang(
       Integer id,
       String name,
       String description,
       Integer surveyId,
       Integer languageId	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && surveyId!=null) {
          this.surveyId = new Survey();
          this.surveyId.setId(surveyId);
	      setSurveyId_ (surveyId);
       }
    }

	public SurveyLang flat() {
	   return new SurveyLang(
          getId(),
          getName(),
          getDescription(),
          getSurveyId_(),
          getLanguageId_()
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

    public Language getLanguageId () {
    	return languageId;
    }
	
    public void setLanguageId (Language languageId) {
    	this.languageId = languageId;
    }

    public Integer getLanguageId_() {
        return languageId_;
    }
	
    public void setLanguageId_ (Integer languageId) {
        this.languageId_ =  languageId;
    }
	
    public Survey getSurveyId () {
    	return surveyId;
    }
	
    public void setSurveyId (Survey surveyId) {
    	this.surveyId = surveyId;
    }

    public Integer getSurveyId_() {
        return surveyId_;
    }
	
    public void setSurveyId_ (Integer surveyId) {
        this.surveyId_ =  surveyId;
    }

}
