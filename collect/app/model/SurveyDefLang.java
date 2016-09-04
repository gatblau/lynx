package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="SurveyDefLang")
@Table (name="\"survey_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="SurveyDefLang.findAll", query="SELECT a FROM SurveyDefLang a")
	,@NamedQuery(name="SurveyDefLang.findByName", query="SELECT a FROM SurveyDefLang a WHERE a.name = :name")
	,@NamedQuery(name="SurveyDefLang.findByNameContaining", query="SELECT a FROM SurveyDefLang a WHERE a.name like :name")
	,@NamedQuery(name="SurveyDefLang.findByDescription", query="SELECT a FROM SurveyDefLang a WHERE a.description = :description")
	,@NamedQuery(name="SurveyDefLang.findByDescriptionContaining", query="SELECT a FROM SurveyDefLang a WHERE a.description like :description")
})
public class SurveyDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SurveyDefLang.findAll";
    public static final String FIND_BY_NAME = "SurveyDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="SurveyDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "SurveyDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="SurveyDefLang.findByDescriptionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=true , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="survey_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SurveyDef surveyDefId;  

    @Column(name="survey_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer surveyDefId_;

    public SurveyDefLang() {
    }

    public SurveyDefLang(
       Integer id,
       String name,
       String description,
       Integer surveyDefId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       surveyDefId,
       languageId
	 ,true);
	}
    
	public SurveyDefLang(
       Integer id,
       String name,
       String description,
       Integer surveyDefId,
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
       if (setRelationship && surveyDefId!=null) {
          this.surveyDefId = new SurveyDef();
          this.surveyDefId.setId(surveyDefId);
	      setSurveyDefId_ (surveyDefId);
       }
    }

	public SurveyDefLang flat() {
	   return new SurveyDefLang(
          getId(),
          getName(),
          getDescription(),
          getSurveyDefId_(),
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
	
    public SurveyDef getSurveyDefId () {
    	return surveyDefId;
    }
	
    public void setSurveyDefId (SurveyDef surveyDefId) {
    	this.surveyDefId = surveyDefId;
    }

    public Integer getSurveyDefId_() {
        return surveyDefId_;
    }
	
    public void setSurveyDefId_ (Integer surveyDefId) {
        this.surveyDefId_ =  surveyDefId;
    }
}
