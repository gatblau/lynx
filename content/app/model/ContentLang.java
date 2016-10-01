package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="ContentLang")
@Table (name="\"content_lang\"")
@NamedQueries ({
	 @NamedQuery(name="ContentLang.findAll", query="SELECT a FROM ContentLang a")
	,@NamedQuery(name="ContentLang.findByName", query="SELECT a FROM ContentLang a WHERE a.name = :name")
	,@NamedQuery(name="ContentLang.findByNameContaining", query="SELECT a FROM ContentLang a WHERE a.name like :name")
    ,@NamedQuery(name="ContentLang.findByDescription", query="SELECT a FROM ContentLang a WHERE a.description = :description")
	,@NamedQuery(name="ContentLang.findByDescriptionContaining", query="SELECT a FROM ContentLang a WHERE a.description like :description")
})
public class ContentLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ContentLang.findAll";
    public static final String FIND_BY_NAME = "ContentLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="ContentLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "ContentLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="ContentLang.findByDescriptionContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name"  , length=200 , nullable=false , unique=false)
    private String name; 

    @Column(name="description"   , nullable=true , unique=false)
    private String description;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="content_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private Content contentId;

    @Column(name="content_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contentId_;

    public ContentLang() {
    }

    public ContentLang(
       Integer id,
       String name,
       String description,
       Integer contentId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       contentId,
       languageId
	 ,true);
	}
    
	public ContentLang(
       Integer id,
       String name,
       String description,
       Integer contentId,
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
       if (setRelationship && contentId!=null) {
          this.contentId = new Content();
          this.contentId.setId(contentId);
	      setContentId_ (contentId);
       }
    }

	public ContentLang flat() {
	   return new ContentLang(
          getId(),
          getName(),
          getDescription(),
          getContentId_(),
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
	
    public Content getContentId () {
    	return contentId;
    }
	
    public void setContentId (Content contentId) {
    	this.contentId = contentId;
    }

    public Integer getContentId_() {
        return contentId_;
    }
	
    public void setContentId_ (Integer contentId) {
        this.contentId_ =  contentId;
    }

}
