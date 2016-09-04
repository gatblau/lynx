package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Country")
@Table (name="\"country\"")
@NamedQueries ({
	 @NamedQuery(name="Country.findAll", query="SELECT a FROM Country a")
})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Country.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="default_language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language defaultLanguageId;  

    @Column(name="default_language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer defaultLanguageId_;

    @OneToMany (targetEntity=model.CountryLang.class, fetch=FetchType.LAZY, mappedBy="countryId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <CountryLang> countryLangCountryViaCountryId = new HashSet<CountryLang>(); 

    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="countryId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentCountryViaCountryId = new HashSet<Respondent>(); 

    public Country() {
    }

    public Country(
       Integer id,
       Integer defaultLanguageId) {
	 this(
       id,
       defaultLanguageId
	 ,true);
	}
    
	public Country(
       Integer id,
       Integer defaultLanguageId	
    , boolean setRelationship) {

       setId (id);
       if (setRelationship && defaultLanguageId!=null) {
          this.defaultLanguageId = new Language();
          this.defaultLanguageId.setId(defaultLanguageId);
       }
    }

	public Country flat() {
	   return new Country(
          getId(),
          getDefaultLanguageId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    

    public Language getDefaultLanguageId () {
    	return defaultLanguageId;
    }
	
    public void setDefaultLanguageId (Language defaultLanguageId) {
    	this.defaultLanguageId = defaultLanguageId;
    }

    public Integer getDefaultLanguageId_() {
        return defaultLanguageId_;
    }
	
    public void setDefaultLanguageId_ (Integer defaultLanguageId) {
        this.defaultLanguageId_ =  defaultLanguageId;
    }

    public Set<CountryLang> getCountryLangCountryViaCountryId() {
        if (countryLangCountryViaCountryId == null){
            countryLangCountryViaCountryId = new HashSet<CountryLang>();
        }
        return countryLangCountryViaCountryId;
    }

    public void setCountryLangCountryViaCountryId (Set<CountryLang> countryLangCountryViaCountryId) {
        this.countryLangCountryViaCountryId = countryLangCountryViaCountryId;
    }	
    
    public void addCountryLangCountryViaCountryId (CountryLang element) {
    	    getCountryLangCountryViaCountryId().add(element);
    }

    public Set<Respondent> getRespondentCountryViaCountryId() {
        if (respondentCountryViaCountryId == null){
            respondentCountryViaCountryId = new HashSet<Respondent>();
        }
        return respondentCountryViaCountryId;
    }

    public void setRespondentCountryViaCountryId (Set<Respondent> respondentCountryViaCountryId) {
        this.respondentCountryViaCountryId = respondentCountryViaCountryId;
    }	
    
    public void addRespondentCountryViaCountryId (Respondent element) {
    	    getRespondentCountryViaCountryId().add(element);
    }
}
