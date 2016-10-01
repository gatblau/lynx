package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ItemDefLang")
@Table (name="\"item_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="ItemDefLang.findAll", query="SELECT a FROM ItemDefLang a")
	,@NamedQuery(name="ItemDefLang.findByName", query="SELECT a FROM ItemDefLang a WHERE a.name = :name")
	,@NamedQuery(name="ItemDefLang.findByNameContaining", query="SELECT a FROM ItemDefLang a WHERE a.name like :name")
	,@NamedQuery(name="ItemDefLang.findByDescription", query="SELECT a FROM ItemDefLang a WHERE a.description = :description")
	,@NamedQuery(name="ItemDefLang.findByDescriptionContaining", query="SELECT a FROM ItemDefLang a WHERE a.description like :description")
	,@NamedQuery(name="ItemDefLang.findByQuestion", query="SELECT a FROM ItemDefLang a WHERE a.question = :question")
	,@NamedQuery(name="ItemDefLang.findByQuestionContaining", query="SELECT a FROM ItemDefLang a WHERE a.question like :question")
})
public class ItemDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ItemDefLang.findAll";
    public static final String FIND_BY_NAME = "ItemDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ItemDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "ItemDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="ItemDefLang.findByDescriptionContaining";
    public static final String FIND_BY_QUESTION = "ItemDefLang.findByQuestion";
    public static final String FIND_BY_QUESTION_CONTAINING ="ItemDefLang.findByQuestionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description; 

    @Column(name="question"  , length=250 , nullable=true , unique=false)
    private String question; 

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="item_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true)
    private ItemDef itemDefId;

    @Column(name="item_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer itemDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    public ItemDefLang() {
    }

    public ItemDefLang(
       Integer id,
       String name,
       String description,
       String question,
       Integer languageId,
       Integer itemDefId) {
	 this(
       id,
       name,
       description,
       question,
       languageId,
       itemDefId
	 ,true);
	}
    
	public ItemDefLang(
       Integer id,
       String name,
       String description,
       String question,
       Integer languageId,
       Integer itemDefId
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       setQuestion (question);
       if (setRelationship && itemDefId!=null) {
          this.itemDefId = new ItemDef();
          this.itemDefId.setId(itemDefId);
	      setItemDefId_ (itemDefId);
       }
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
    }

	public ItemDefLang flat() {
	   return new ItemDefLang(
          getId(),
          getName(),
          getDescription(),
          getQuestion(),
          getLanguageId_(),
          getItemDefId_()
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

    public String getQuestion() {
        return question;
    }
	
    public void setQuestion (String question) {
        this.question =  question;
    }

    public ItemDef getItemDefId () {
    	return itemDefId;
    }
	
    public void setItemDefId (ItemDef itemDefId) {
    	this.itemDefId = itemDefId;
    }

    public Integer getItemDefId_() {
        return itemDefId_;
    }
	
    public void setItemDefId_ (Integer itemDefId) {
        this.itemDefId_ =  itemDefId;
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
