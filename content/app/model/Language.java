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

    @OneToMany (targetEntity=model.ItemDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ItemDefLang> itemDefLangLanguageViaLanguageId = new HashSet<ItemDefLang>();

    @OneToMany (targetEntity=model.OptionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDefLang> optionDefLangLanguageViaLanguageId = new HashSet<OptionDefLang>(); 

    @OneToMany (targetEntity=model.User.class, fetch=FetchType.LAZY, mappedBy="preferredLanguageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <User> userLanguageViaPreferredLanguageId = new HashSet<User>();

    @OneToMany (targetEntity=model.RoleLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <RoleLang> roleLangLanguageViaLanguageId = new HashSet<RoleLang>(); 

    @OneToMany (targetEntity=model.SectionDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDefLang> sectionDefLangLanguageViaLanguageId = new HashSet<SectionDefLang>(); 

    @OneToMany (targetEntity=model.ContentDefLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentDefLang> contentDefLangLanguageViaLanguageId = new HashSet<ContentDefLang>();

    @OneToMany (targetEntity=model.ContentLang.class, fetch=FetchType.LAZY, mappedBy="languageId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentLang> contentLangLanguageViaLanguageId = new HashSet<ContentLang>();

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

    public Set<ItemDefLang> getItemDefLangLanguageViaLanguageId() {
        if (itemDefLangLanguageViaLanguageId == null){
            itemDefLangLanguageViaLanguageId = new HashSet<ItemDefLang>();
        }
        return itemDefLangLanguageViaLanguageId;
    }

    public void setItemDefLangLanguageViaLanguageId (Set<ItemDefLang> itemDefLangLanguageViaLanguageId) {
        this.itemDefLangLanguageViaLanguageId = itemDefLangLanguageViaLanguageId;
    }	
    
    public void addItemDefLangLanguageViaLanguageId (ItemDefLang element) {
    	    getItemDefLangLanguageViaLanguageId().add(element);
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

    public Set<User> getUserLanguageViaPreferredLanguageId() {
        if (userLanguageViaPreferredLanguageId == null){
            userLanguageViaPreferredLanguageId = new HashSet<User>();
        }
        return userLanguageViaPreferredLanguageId;
    }

    public void setUserLanguageViaPreferredLanguageId (Set<User> userLanguageViaPreferredLanguageId) {
        this.userLanguageViaPreferredLanguageId = userLanguageViaPreferredLanguageId;
    }	
    
    public void addUserLanguageViaPreferredLanguageId (User element) {
    	    getUserLanguageViaPreferredLanguageId().add(element);
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

    public Set<ContentDefLang> getContentDefLangLanguageViaLanguageId() {
        if (contentDefLangLanguageViaLanguageId == null){
            contentDefLangLanguageViaLanguageId = new HashSet<ContentDefLang>();
        }
        return contentDefLangLanguageViaLanguageId;
    }

    public void setContentDefLangLanguageViaLanguageId (Set<ContentDefLang> contentDefLangLanguageViaLanguageId) {
        this.contentDefLangLanguageViaLanguageId = contentDefLangLanguageViaLanguageId;
    }	
    
    public void addContentDefLangLanguageViaLanguageId (ContentDefLang element) {
    	    getContentDefLangLanguageViaLanguageId().add(element);
    }

    public Set<ContentLang> getContentLangLanguageViaLanguageId() {
        if (contentLangLanguageViaLanguageId == null){
            contentLangLanguageViaLanguageId = new HashSet<ContentLang>();
        }
        return contentLangLanguageViaLanguageId;
    }

    public void setContentLangLanguageViaLanguageId (Set<ContentLang> contentLangLanguageViaLanguageId) {
        this.contentLangLanguageViaLanguageId = contentLangLanguageViaLanguageId;
    }	
    
    public void addContentLangLanguageViaLanguageId (ContentLang element) {
    	    getContentLangLanguageViaLanguageId().add(element);
    }
}
