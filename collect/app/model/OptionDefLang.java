package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: OptionDefLang</p>
 *
 * <p>Description: Domain Object describing a OptionDefLang entity</p>
 *
 */
@Entity (name="OptionDefLang")
@Table (name="\"option_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="OptionDefLang.findAll", query="SELECT a FROM OptionDefLang a")
	,@NamedQuery(name="OptionDefLang.findByName", query="SELECT a FROM OptionDefLang a WHERE a.name = :name")
	,@NamedQuery(name="OptionDefLang.findByNameContaining", query="SELECT a FROM OptionDefLang a WHERE a.name like :name")

	,@NamedQuery(name="OptionDefLang.findByDescription", query="SELECT a FROM OptionDefLang a WHERE a.description = :description")
	,@NamedQuery(name="OptionDefLang.findByDescriptionContaining", query="SELECT a FROM OptionDefLang a WHERE a.description like :description")

	,@NamedQuery(name="OptionDefLang.findByType", query="SELECT a FROM OptionDefLang a WHERE a.type = :type")
	,@NamedQuery(name="OptionDefLang.findByTypeContaining", query="SELECT a FROM OptionDefLang a WHERE a.type like :type")

})

public class OptionDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "OptionDefLang.findAll";
    public static final String FIND_BY_NAME = "OptionDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="OptionDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "OptionDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="OptionDefLang.findByDescriptionContaining";
    public static final String FIND_BY_TYPE = "OptionDefLang.findByType";
    public static final String FIND_BY_TYPE_CONTAINING ="OptionDefLang.findByTypeContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
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

//MP-MANAGED-ADDED-AREA-BEGINNING @type-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @type-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-type@
    @Column(name="type"  , length=45 , nullable=true , unique=false)
    private String type; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="option_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private OptionDef optionDefId;  

    @Column(name="option_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer optionDefId_;

    /**
    * Default constructor
    */
    public OptionDefLang() {
    }

	/**
	* All field constructor 
	*/
    public OptionDefLang(
       Integer id,
       String name,
       String description,
       String type,
       Integer languageId,
       Integer optionDefId) {
	 this(
       id,
       name,
       description,
       type,
       languageId,
       optionDefId
	 ,true);
	}
    
	public OptionDefLang(
       Integer id,
       String name,
       String description,
       String type,
       Integer languageId,
       Integer optionDefId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setDescription (description);
       setType (type);
       //parents
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && optionDefId!=null) {
          this.optionDefId = new OptionDef();
          this.optionDefId.setId(optionDefId);
	      setOptionDefId_ (optionDefId);
       }
    }

	public OptionDefLang flat() {
	   return new OptionDefLang(
          getId(),
          getName(),
          getDescription(),
          getType(),
          getLanguageId_(),
          getOptionDefId_()
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-type@
    public String getType() {
        return type;
    }
	
    public void setType (String type) {
        this.type =  type;
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
	
    public OptionDef getOptionDefId () {
    	return optionDefId;
    }
	
    public void setOptionDefId (OptionDef optionDefId) {
    	this.optionDefId = optionDefId;
    }

    public Integer getOptionDefId_() {
        return optionDefId_;
    }
	
    public void setOptionDefId_ (Integer optionDefId) {
        this.optionDefId_ =  optionDefId;
    }
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
