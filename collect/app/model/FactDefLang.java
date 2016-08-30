package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * <p>Title: FactDefLang</p>
 *
 * <p>Description: Domain Object describing a FactDefLang entity</p>
 *
 */
@Entity (name="FactDefLang")
@Table (name="\"fact_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="FactDefLang.findAll", query="SELECT a FROM FactDefLang a")
	,@NamedQuery(name="FactDefLang.findByName", query="SELECT a FROM FactDefLang a WHERE a.name = :name")
	,@NamedQuery(name="FactDefLang.findByNameContaining", query="SELECT a FROM FactDefLang a WHERE a.name like :name")

	,@NamedQuery(name="FactDefLang.findByDescription", query="SELECT a FROM FactDefLang a WHERE a.description = :description")
	,@NamedQuery(name="FactDefLang.findByDescriptionContaining", query="SELECT a FROM FactDefLang a WHERE a.description like :description")

	,@NamedQuery(name="FactDefLang.findByQuestion", query="SELECT a FROM FactDefLang a WHERE a.question = :question")
	,@NamedQuery(name="FactDefLang.findByQuestionContaining", query="SELECT a FROM FactDefLang a WHERE a.question like :question")

})

public class FactDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FactDefLang.findAll";
    public static final String FIND_BY_NAME = "FactDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="FactDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "FactDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="FactDefLang.findByDescriptionContaining";
    public static final String FIND_BY_QUESTION = "FactDefLang.findByQuestion";
    public static final String FIND_BY_QUESTION_CONTAINING ="FactDefLang.findByQuestionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @description-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @description-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-description@
    @Column(name="description"   , nullable=true , unique=false)
    private String description; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @question-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @question-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-question@
    @Column(name="question"  , length=250 , nullable=true , unique=false)
    private String question; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="fact_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private FactDef factDefId;  

    @Column(name="fact_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    /**
    * Default constructor
    */
    public FactDefLang() {
    }

	/**
	* All field constructor 
	*/
    public FactDefLang(
       Integer id,
       String name,
       String description,
       String question,
       Integer languageId,
       Integer factDefId) {
	 this(
       id,
       name,
       description,
       question,
       languageId,
       factDefId
	 ,true);
	}
    
	public FactDefLang(
       Integer id,
       String name,
       String description,
       String question,
       Integer languageId,
       Integer factDefId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       setDescription (description);
       setQuestion (question);
       //parents
       if (setRelationship && factDefId!=null) {
          this.factDefId = new FactDef();
          this.factDefId.setId(factDefId);
	      setFactDefId_ (factDefId);
       }
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
    }

	public FactDefLang flat() {
	   return new FactDefLang(
          getId(),
          getName(),
          getDescription(),
          getQuestion(),
          getLanguageId_(),
          getFactDefId_()
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

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-question@
    public String getQuestion() {
        return question;
    }
	
    public void setQuestion (String question) {
        this.question =  question;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public FactDef getFactDefId () {
    	return factDefId;
    }
	
    public void setFactDefId (FactDef factDefId) {
    	this.factDefId = factDefId;
    }

    public Integer getFactDefId_() {
        return factDefId_;
    }
	
    public void setFactDefId_ (Integer factDefId) {
        this.factDefId_ =  factDefId;
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
	




//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
