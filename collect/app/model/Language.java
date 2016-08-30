package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Language</p>
 *
 * <p>Description: Domain Object describing a Language entity</p>
 *
 */
@Entity (name="Language")
@Table (name="\"language\"")
@NamedQueries ({
	 @NamedQuery(name="Language.findAll", query="SELECT a FROM Language a")
	,@NamedQuery(name="Language.findByName", query="SELECT a FROM Language a WHERE a.name = :name")
	,@NamedQuery(name="Language.findByNameContaining", query="SELECT a FROM Language a WHERE a.name like :name")

	,@NamedQuery(name="Language.findByCode", query="SELECT a FROM Language a WHERE a.code = :code")
	,@NamedQuery(name="Language.findByCodeContaining", query="SELECT a FROM Language a WHERE a.code like :code")

})

public class Language implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Language.findAll";
    public static final String FIND_BY_NAME = "Language.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Language.findByNameContaining";
    public static final String FIND_BY_CODE = "Language.findByCode";
    public static final String FIND_BY_CODE_CONTAINING ="Language.findByCodeContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @code-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @code-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-code@
    @Column(name="code"  , length=10 , nullable=false , unique=false)
    private String code; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefLangLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.FactDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDefLang> factDefLangLanguageViaLanguageId = new HashSet<FactDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefLangLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.OptionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDefLang> optionDefLangLanguageViaLanguageId = new HashSet<OptionDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentLanguageViaLanguageId = new HashSet<Respondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @roleLangLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.RoleLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <RoleLang> roleLangLanguageViaLanguageId = new HashSet<RoleLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefLangLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.SectionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDefLang> sectionDefLangLanguageViaLanguageId = new HashSet<SectionDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyDefLangLanguageViaLanguageId-field-language@
    @OneToMany (targetEntity=model.SurveyDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyDefLang> surveyDefLangLanguageViaLanguageId = new HashSet<SurveyDefLang>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Language() {
    }

	/**
	* All field constructor 
	*/
    public Language(
       Integer id,
       String name,
       String code) {
	 this(
       id,
       name,
       code
	 ,true);
	}
    
	public Language(
       Integer id,
       String name,
       String code	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setCode (code);
       //parents
    }

	public Language flat() {
	   return new Language(
          getId(),
          getName(),
          getCode()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-code@
    public String getCode() {
        return code;
    }
	
    public void setCode (String code) {
        this.code =  code;
    }
	
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @factDefLangLanguageViaLanguageId-getter-language@
    public Set<FactDefLang> getFactDefLangLanguageViaLanguageId() {
        if (factDefLangLanguageViaLanguageId == null){
            factDefLangLanguageViaLanguageId = new HashSet<FactDefLang>();
        }
        return factDefLangLanguageViaLanguageId;
    }

    public void setFactDefLangLanguageViaLanguageId (Set<FactDefLang> factDefLangLanguageViaLanguageId) {
        this.factDefLangLanguageViaLanguageId = factDefLangLanguageViaLanguageId;
    }	
    
    public void addFactDefLangLanguageViaLanguageId (FactDefLang element) {
    	    getFactDefLangLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @optionDefLangLanguageViaLanguageId-getter-language@
    public Set<OptionDefLang> getOptionDefLangLanguageViaLanguageId() {
        if (optionDefLangLanguageViaLanguageId == null){
            optionDefLangLanguageViaLanguageId = new HashSet<OptionDefLang>();
        }
        return optionDefLangLanguageViaLanguageId;
    }

    public void setOptionDefLangLanguageViaLanguageId (Set<OptionDefLang> optionDefLangLanguageViaLanguageId) {
        this.optionDefLangLanguageViaLanguageId = optionDefLangLanguageViaLanguageId;
    }	
    
    public void addOptionDefLangLanguageViaLanguageId (OptionDefLang element) {
    	    getOptionDefLangLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentLanguageViaLanguageId-getter-language@
    public Set<Respondent> getRespondentLanguageViaLanguageId() {
        if (respondentLanguageViaLanguageId == null){
            respondentLanguageViaLanguageId = new HashSet<Respondent>();
        }
        return respondentLanguageViaLanguageId;
    }

    public void setRespondentLanguageViaLanguageId (Set<Respondent> respondentLanguageViaLanguageId) {
        this.respondentLanguageViaLanguageId = respondentLanguageViaLanguageId;
    }	
    
    public void addRespondentLanguageViaLanguageId (Respondent element) {
    	    getRespondentLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @roleLangLanguageViaLanguageId-getter-language@
    public Set<RoleLang> getRoleLangLanguageViaLanguageId() {
        if (roleLangLanguageViaLanguageId == null){
            roleLangLanguageViaLanguageId = new HashSet<RoleLang>();
        }
        return roleLangLanguageViaLanguageId;
    }

    public void setRoleLangLanguageViaLanguageId (Set<RoleLang> roleLangLanguageViaLanguageId) {
        this.roleLangLanguageViaLanguageId = roleLangLanguageViaLanguageId;
    }	
    
    public void addRoleLangLanguageViaLanguageId (RoleLang element) {
    	    getRoleLangLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @sectionDefLangLanguageViaLanguageId-getter-language@
    public Set<SectionDefLang> getSectionDefLangLanguageViaLanguageId() {
        if (sectionDefLangLanguageViaLanguageId == null){
            sectionDefLangLanguageViaLanguageId = new HashSet<SectionDefLang>();
        }
        return sectionDefLangLanguageViaLanguageId;
    }

    public void setSectionDefLangLanguageViaLanguageId (Set<SectionDefLang> sectionDefLangLanguageViaLanguageId) {
        this.sectionDefLangLanguageViaLanguageId = sectionDefLangLanguageViaLanguageId;
    }	
    
    public void addSectionDefLangLanguageViaLanguageId (SectionDefLang element) {
    	    getSectionDefLangLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyDefLangLanguageViaLanguageId-getter-language@
    public Set<SurveyDefLang> getSurveyDefLangLanguageViaLanguageId() {
        if (surveyDefLangLanguageViaLanguageId == null){
            surveyDefLangLanguageViaLanguageId = new HashSet<SurveyDefLang>();
        }
        return surveyDefLangLanguageViaLanguageId;
    }

    public void setSurveyDefLangLanguageViaLanguageId (Set<SurveyDefLang> surveyDefLangLanguageViaLanguageId) {
        this.surveyDefLangLanguageViaLanguageId = surveyDefLangLanguageViaLanguageId;
    }	
    
    public void addSurveyDefLangLanguageViaLanguageId (SurveyDefLang element) {
    	    getSurveyDefLangLanguageViaLanguageId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
