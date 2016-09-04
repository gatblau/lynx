package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @Column(name="code"  , length=10 , nullable=false , unique=false)
    private String code; 

    @OneToMany (targetEntity=model.Country.class, fetch=FetchType.LAZY, mappedBy="defaultLanguageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Country> countryLanguageViaDefaultLanguageId = new HashSet<Country>(); 

    @OneToMany (targetEntity=model.CountryLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <CountryLang> countryLangLanguageViaLanguageId = new HashSet<CountryLang>(); 

    @OneToMany (targetEntity=model.FactDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDefLang> factDefLangLanguageViaLanguageId = new HashSet<FactDefLang>(); 

    @OneToMany (targetEntity=model.OptionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDefLang> optionDefLangLanguageViaLanguageId = new HashSet<OptionDefLang>(); 

    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="preferredLanguageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentLanguageViaPreferredLanguageId = new HashSet<Respondent>(); 

    @OneToMany (targetEntity=model.RoleLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <RoleLang> roleLangLanguageViaLanguageId = new HashSet<RoleLang>(); 

    @OneToMany (targetEntity=model.SectionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDefLang> sectionDefLangLanguageViaLanguageId = new HashSet<SectionDefLang>(); 

    @OneToMany (targetEntity=model.SurveyDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyDefLang> surveyDefLangLanguageViaLanguageId = new HashSet<SurveyDefLang>(); 

    @OneToMany (targetEntity=model.SurveyLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyLang> surveyLangLanguageViaLanguageId = new HashSet<SurveyLang>(); 

    public Language() {
    }

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
       setId (id);
       setName (name);
       setCode (code);
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

    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }

    public String getCode() {
        return code;
    }
	
    public void setCode (String code) {
        this.code =  code;
    }

    public Set<Country> getCountryLanguageViaDefaultLanguageId() {
        if (countryLanguageViaDefaultLanguageId == null){
            countryLanguageViaDefaultLanguageId = new HashSet<Country>();
        }
        return countryLanguageViaDefaultLanguageId;
    }

    public void setCountryLanguageViaDefaultLanguageId (Set<Country> countryLanguageViaDefaultLanguageId) {
        this.countryLanguageViaDefaultLanguageId = countryLanguageViaDefaultLanguageId;
    }	
    
    public void addCountryLanguageViaDefaultLanguageId (Country element) {
    	    getCountryLanguageViaDefaultLanguageId().add(element);
    }

    public Set<CountryLang> getCountryLangLanguageViaLanguageId() {
        if (countryLangLanguageViaLanguageId == null){
            countryLangLanguageViaLanguageId = new HashSet<CountryLang>();
        }
        return countryLangLanguageViaLanguageId;
    }

    public void setCountryLangLanguageViaLanguageId (Set<CountryLang> countryLangLanguageViaLanguageId) {
        this.countryLangLanguageViaLanguageId = countryLangLanguageViaLanguageId;
    }	
    
    public void addCountryLangLanguageViaLanguageId (CountryLang element) {
    	    getCountryLangLanguageViaLanguageId().add(element);
    }

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

    public Set<Respondent> getRespondentLanguageViaPreferredLanguageId() {
        if (respondentLanguageViaPreferredLanguageId == null){
            respondentLanguageViaPreferredLanguageId = new HashSet<Respondent>();
        }
        return respondentLanguageViaPreferredLanguageId;
    }

    public void setRespondentLanguageViaPreferredLanguageId (Set<Respondent> respondentLanguageViaPreferredLanguageId) {
        this.respondentLanguageViaPreferredLanguageId = respondentLanguageViaPreferredLanguageId;
    }	
    
    public void addRespondentLanguageViaPreferredLanguageId (Respondent element) {
    	    getRespondentLanguageViaPreferredLanguageId().add(element);
    }

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

    public Set<SurveyLang> getSurveyLangLanguageViaLanguageId() {
        if (surveyLangLanguageViaLanguageId == null){
            surveyLangLanguageViaLanguageId = new HashSet<SurveyLang>();
        }
        return surveyLangLanguageViaLanguageId;
    }

    public void setSurveyLangLanguageViaLanguageId (Set<SurveyLang> surveyLangLanguageViaLanguageId) {
        this.surveyLangLanguageViaLanguageId = surveyLangLanguageViaLanguageId;
    }	
    
    public void addSurveyLangLanguageViaLanguageId (SurveyLang element) {
    	    getSurveyLangLanguageViaLanguageId().add(element);
    }
}
