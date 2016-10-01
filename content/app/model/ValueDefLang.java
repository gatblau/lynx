package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ValueDefLang")
@Table (name="\"value_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="ValueDefLang.findAll", query="SELECT a FROM ValueDefLang a")
	,@NamedQuery(name="ValueDefLang.findByName", query="SELECT a FROM ValueDefLang a WHERE a.name = :name")
	,@NamedQuery(name="ValueDefLang.findByNameContaining", query="SELECT a FROM ValueDefLang a WHERE a.name like :name")
	,@NamedQuery(name="ValueDefLang.findByDescription", query="SELECT a FROM ValueDefLang a WHERE a.description = :description")
	,@NamedQuery(name="ValueDefLang.findByDescriptionContaining", query="SELECT a FROM ValueDefLang a WHERE a.description like :description")
})
public class ValueDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ValueDefLang.findAll";
    public static final String FIND_BY_NAME = "ValueDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ValueDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "ValueDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="ValueDefLang.findByDescriptionContaining";
	
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
    @JoinColumn(name="value_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private ValueDef valueDefId;

    @Column(name="value_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer valueDefId_;

    public ValueDefLang() {
    }

    public ValueDefLang(
       Integer id,
       String name,
       String description,
       Integer languageId,
       Integer valueDefId) {
	 this(
       id,
       name,
       description,
       languageId,
       valueDefId
	 ,true);
	}
    
	public ValueDefLang(
       Integer id,
       String name,
       String description,
       Integer languageId,
       Integer valueDefId
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && valueDefId!=null) {
          this.valueDefId = new ValueDef();
          this.valueDefId.setId(valueDefId);
	      setValueDefId_ (valueDefId);
       }
    }

	public ValueDefLang flat() {
	   return new ValueDefLang(
          getId(),
          getName(),
          getDescription(),
          getLanguageId_(),
          getValueDefId_()
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
	
    public ValueDef getValueDefId () {
    	return valueDefId;
    }
	
    public void setValueDefId (ValueDef valueDefId) {
    	this.valueDefId = valueDefId;
    }

    public Integer getValueDefId_() {
        return valueDefId_;
    }
	
    public void setValueDefId_ (Integer valueDefId) {
        this.valueDefId_ =  valueDefId;
    }

}
