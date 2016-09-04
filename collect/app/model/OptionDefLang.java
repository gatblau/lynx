package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="OptionDefLang")
@Table (name="\"option_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="OptionDefLang.findAll", query="SELECT a FROM OptionDefLang a")
	,@NamedQuery(name="OptionDefLang.findByName", query="SELECT a FROM OptionDefLang a WHERE a.name = :name")
	,@NamedQuery(name="OptionDefLang.findByNameContaining", query="SELECT a FROM OptionDefLang a WHERE a.name like :name")
	,@NamedQuery(name="OptionDefLang.findByDescription", query="SELECT a FROM OptionDefLang a WHERE a.description = :description")
	,@NamedQuery(name="OptionDefLang.findByDescriptionContaining", query="SELECT a FROM OptionDefLang a WHERE a.description like :description")
})
public class OptionDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "OptionDefLang.findAll";
    public static final String FIND_BY_NAME = "OptionDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="OptionDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "OptionDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="OptionDefLang.findByDescriptionContaining";
	
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
    @JoinColumn(name="option_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private OptionDef optionDefId;  

    @Column(name="option_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer optionDefId_;

    public OptionDefLang() {
    }

    public OptionDefLang(
       Integer id,
       String name,
       String description,
       Integer languageId,
       Integer optionDefId) {
	 this(
       id,
       name,
       description,
       languageId,
       optionDefId
	 ,true);
	}
    
	public OptionDefLang(
       Integer id,
       String name,
       String description,
       Integer languageId,
       Integer optionDefId	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
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

}
