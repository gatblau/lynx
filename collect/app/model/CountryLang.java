package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="CountryLang")
@Table (name="\"country_lang\"")
@NamedQueries ({
	 @NamedQuery(name="CountryLang.findAll", query="SELECT a FROM CountryLang a")
	,@NamedQuery(name="CountryLang.findByName", query="SELECT a FROM CountryLang a WHERE a.name = :name")
	,@NamedQuery(name="CountryLang.findByNameContaining", query="SELECT a FROM CountryLang a WHERE a.name like :name")
})
public class CountryLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "CountryLang.findAll";
    public static final String FIND_BY_NAME = "CountryLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="CountryLang.findByNameContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="country_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Country countryId;  

    @Column(name="country_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer countryId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    public CountryLang() {
    }

    public CountryLang(
       Integer id,
       String name,
       Integer countryId,
       Integer languageId) {
	 this(
       id,
       name,
       countryId,
       languageId
	 ,true);
	}
    
	public CountryLang(
       Integer id,
       String name,
       Integer countryId,
       Integer languageId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       //parents
       if (setRelationship && countryId!=null) {
          this.countryId = new Country();
          this.countryId.setId(countryId);
	      setCountryId_ (countryId);
       }
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
    }

	public CountryLang flat() {
	   return new CountryLang(
          getId(),
          getName(),
          getCountryId_(),
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

    public Country getCountryId () {
    	return countryId;
    }
	
    public void setCountryId (Country countryId) {
    	this.countryId = countryId;
    }

    public Integer getCountryId_() {
        return countryId_;
    }
	
    public void setCountryId_ (Integer countryId) {
        this.countryId_ =  countryId;
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

}
