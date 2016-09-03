package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: SectionDefLang</p>
 *
 * <p>Description: Domain Object describing a SectionDefLang entity</p>
 *
 */
@Entity (name="SectionDefLang")
@Table (name="\"section_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="SectionDefLang.findAll", query="SELECT a FROM SectionDefLang a")
	,@NamedQuery(name="SectionDefLang.findByName", query="SELECT a FROM SectionDefLang a WHERE a.name = :name")
	,@NamedQuery(name="SectionDefLang.findByNameContaining", query="SELECT a FROM SectionDefLang a WHERE a.name like :name")

	,@NamedQuery(name="SectionDefLang.findByDescription", query="SELECT a FROM SectionDefLang a WHERE a.description = :description")
	,@NamedQuery(name="SectionDefLang.findByDescriptionContaining", query="SELECT a FROM SectionDefLang a WHERE a.description like :description")

})

public class SectionDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SectionDefLang.findAll";
    public static final String FIND_BY_NAME = "SectionDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="SectionDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "SectionDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="SectionDefLang.findByDescriptionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=true , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @description-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @description-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-description@
    @Column(name="description"   , nullable=true , unique=false)
    private String description; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    /**
    * Default constructor
    */
    public SectionDefLang() {
    }

	/**
	* All field constructor 
	*/
    public SectionDefLang(
       Integer id,
       String name,
       String description,
       Integer sectionDefId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       sectionDefId,
       languageId
	 ,true);
	}
    
	public SectionDefLang(
       Integer id,
       String name,
       String description,
       Integer sectionDefId,
       Integer languageId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setDescription (description);
       //parents
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
	      setSectionDefId_ (sectionDefId);
       }
    }

	public SectionDefLang flat() {
	   return new SectionDefLang(
          getId(),
          getName(),
          getDescription(),
          getSectionDefId_(),
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
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-name@
    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-description@
    public String getDescription() {
        return description;
    }
	
    public void setDescription (String description) {
        this.description =  description;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


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
	
    public SectionDef getSectionDefId () {
    	return sectionDefId;
    }
	
    public void setSectionDefId (SectionDef sectionDefId) {
    	this.sectionDefId = sectionDefId;
    }

    public Integer getSectionDefId_() {
        return sectionDefId_;
    }
	
    public void setSectionDefId_ (Integer sectionDefId) {
        this.sectionDefId_ =  sectionDefId;
    }
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
