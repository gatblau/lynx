package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="ContentDef")
@Table (name="\"content_def\"")
@NamedQueries ({
	 @NamedQuery(name="ContentDef.findAll", query="SELECT a FROM ContentDef a")
})
public class ContentDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "ContentDef.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany (targetEntity=model.SectionDef.class, fetch=FetchType.LAZY, mappedBy="contentDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SectionDef> sectionDefContentDefViaContentDefId = new HashSet<SectionDef>();

    @OneToMany (targetEntity=model.Content.class, fetch=FetchType.LAZY, mappedBy="contentDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Content> contentContentDefViaContentDefId = new HashSet<Content>();

    @OneToMany (targetEntity=model.ContentDefLang.class, fetch=FetchType.LAZY, mappedBy="contentDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <ContentDefLang> contentDefLangContentDefViaContentDefId = new HashSet<ContentDefLang>();

    public ContentDef() {
    }

    public ContentDef(
       Integer id) {
	 this(
       id
	 ,true);
	}
    
	public ContentDef(
       Integer id	
    , boolean setRelationship) {
       setId (id);
    }

	public ContentDef flat() {
	   return new ContentDef(
          getId()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Set<SectionDef> getSectionDefContentDefViaContentDefId() {
        if (sectionDefContentDefViaContentDefId == null){
            sectionDefContentDefViaContentDefId = new HashSet<SectionDef>();
        }
        return sectionDefContentDefViaContentDefId;
    }

    public void setSectionDefContentDefViaContentDefId (Set<SectionDef> sectionDefContentDefViaContentDefId) {
        this.sectionDefContentDefViaContentDefId = sectionDefContentDefViaContentDefId;
    }	
    
    public void addSectionDefContentDefViaContentDefId (SectionDef element) {
    	    getSectionDefContentDefViaContentDefId().add(element);
    }

    public Set<Content> getContentContentDefViaContentDefId() {
        if (contentContentDefViaContentDefId == null){
            contentContentDefViaContentDefId = new HashSet<Content>();
        }
        return contentContentDefViaContentDefId;
    }

    public void setContentContentDefViaContentDefId (Set<Content> contentContentDefViaContentDefId) {
        this.contentContentDefViaContentDefId = contentContentDefViaContentDefId;
    }	
    
    public void addContentContentDefViaContentDefId (Content element) {
    	    getContentContentDefViaContentDefId().add(element);
    }

    public Set<ContentDefLang> getContentDefLangContentDefViaContentDefId() {
        if (contentDefLangContentDefViaContentDefId == null){
            contentDefLangContentDefViaContentDefId = new HashSet<ContentDefLang>();
        }
        return contentDefLangContentDefViaContentDefId;
    }

    public void setContentDefLangContentDefViaContentDefId (Set<ContentDefLang> contentDefLangContentDefViaContentDefId) {
        this.contentDefLangContentDefViaContentDefId = contentDefLangContentDefViaContentDefId;
    }	
    
    public void addContentDefLangContentDefViaContentDefId (ContentDefLang element) {
    	    getContentDefLangContentDefViaContentDefId().add(element);
    }

}
