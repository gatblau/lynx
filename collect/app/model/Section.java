package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Section")
@Table (name="\"section\"")
@NamedQueries ({
	 @NamedQuery(name="Section.findAll", query="SELECT a FROM Section a")
})
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Section.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="content_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true)
    private Content contentId;

    @Column(name="content_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer contentId_;

    @OneToMany (targetEntity=model.Item.class, fetch=FetchType.LAZY, mappedBy="sectionId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Item> itemSectionViaSectionId = new HashSet<Item>();

    public Section() {
    }

    public Section(
       Integer id,
       Integer sectionDefId,
       Integer contentId) {
	 this(
       id,
       sectionDefId,
       contentId
	 ,true);
	}
    
	public Section(
       Integer id,
       Integer sectionDefId,
       Integer contentId
    , boolean setRelationship) {
       setId (id);
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
       }
       if (setRelationship && contentId!=null) {
          this.contentId = new Content();
          this.contentId.setId(contentId);
       }
    }

	public Section flat() {
	   return new Section(
          getId(),
          getSectionDefId_(),
          getContentId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
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

    public Set<Item> getItemSectionViaSectionId() {
        if (itemSectionViaSectionId == null){
            itemSectionViaSectionId = new HashSet<Item>();
        }
        return itemSectionViaSectionId;
    }

    public void setItemSectionViaSectionId (Set<Item> itemSectionViaSectionId) {
        this.itemSectionViaSectionId = itemSectionViaSectionId;
    }	
    
    public void addItemSectionViaSectionId (Item element) {
    	    getItemSectionViaSectionId().add(element);
    }
}
