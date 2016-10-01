package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ContentDefLang")
@Table (name="\"content_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="ContentDefLang.findAll", query="SELECT a FROM ContentDefLang a")
	,@NamedQuery(name="ContentDefLang.findByName", query="SELECT a FROM ContentDefLang a WHERE a.name = :name")
	,@NamedQuery(name="ContentDefLang.findByNameContaining", query="SELECT a FROM ContentDefLang a WHERE a.name like :name")
	,@NamedQuery(name="ContentDefLang.findByDescription", query="SELECT a FROM ContentDefLang a WHERE a.description = :description")
	,@NamedQuery(name="ContentDefLang.findByDescriptionContaining", query="SELECT a FROM ContentDefLang a WHERE a.description like :description")
})
public class ContentDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ContentDefLang.findAll";
    public static final String FIND_BY_NAME = "ContentDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ContentDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "ContentDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="ContentDefLang.findByDescriptionContaining";
	
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
    @JoinColumn(name="content_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private ContentDef contentDefId;

    @Column(name="content_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contentDefId_;

    public ContentDefLang() {
    }

    public ContentDefLang(
       Integer id,
       String name,
       String description,
       Integer contentDefId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       contentDefId,
       languageId
	 ,true);
	}
    
	public ContentDefLang(
       Integer id,
       String name,
       String description,
       Integer contentDefId,
       Integer languageId	
    , boolean setRelationship) {
       setId (id);
       setName (name);
       setDescription (description);
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && contentDefId!=null) {
          this.contentDefId = new ContentDef();
          this.contentDefId.setId(contentDefId);
	      setContentDefId_ (contentDefId);
       }
    }

	public ContentDefLang flat() {
	   return new ContentDefLang(
          getId(),
          getName(),
          getDescription(),
          getContentDefId_(),
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
	
    public ContentDef getContentDefId () {
    	return contentDefId;
    }
	
    public void setContentDefId (ContentDef contentDefId) {
    	this.contentDefId = contentDefId;
    }

    public Integer getContentDefId_() {
        return contentDefId_;
    }
	
    public void setContentDefId_ (Integer contentDefId) {
        this.contentDefId_ =  contentDefId;
    }
}
