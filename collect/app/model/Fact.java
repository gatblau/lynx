package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="Fact")
@Table (name="\"fact\"")
@NamedQueries ({
	 @NamedQuery(name="Fact.findAll", query="SELECT a FROM Fact a")
})
public class Fact implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Fact.findAll";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="fact_def_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private FactDef factDefId;  

    @Column(name="fact_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factDefId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Section sectionId;  

    @Column(name="section_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionId_;

    @OneToMany (targetEntity=model.Option.class, fetch=FetchType.LAZY, mappedBy="factId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Option> optionFactViaFactId = new HashSet<Option>(); 

    public Fact() {
    }

    public Fact(
       Integer id,
       Integer factDefId,
       Integer sectionId) {
	 this(
       id,
       factDefId,
       sectionId
	 ,true);
	}
    
	public Fact(
       Integer id,
       Integer factDefId,
       Integer sectionId	
    , boolean setRelationship) {
       setId (id);
       if (setRelationship && factDefId!=null) {
          this.factDefId = new FactDef();
          this.factDefId.setId(factDefId);
       }
       if (setRelationship && sectionId!=null) {
          this.sectionId = new Section();
          this.sectionId.setId(sectionId);
       }
    }

	public Fact flat() {
	   return new Fact(
          getId(),
          getFactDefId_(),
          getSectionId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }
    

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
	
    public Section getSectionId () {
    	return sectionId;
    }
	
    public void setSectionId (Section sectionId) {
    	this.sectionId = sectionId;
    }

    public Integer getSectionId_() {
        return sectionId_;
    }
	
    public void setSectionId_ (Integer sectionId) {
        this.sectionId_ =  sectionId;
    }

    public Set<Option> getOptionFactViaFactId() {
        if (optionFactViaFactId == null){
            optionFactViaFactId = new HashSet<Option>();
        }
        return optionFactViaFactId;
    }

    public void setOptionFactViaFactId (Set<Option> optionFactViaFactId) {
        this.optionFactViaFactId = optionFactViaFactId;
    }	
    
    public void addOptionFactViaFactId (Option element) {
    	    getOptionFactViaFactId().add(element);
    }

}
