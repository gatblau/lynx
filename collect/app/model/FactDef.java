package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity (name="FactDef")
@Table (name="\"fact_def\"")
@NamedQueries ({
	 @NamedQuery(name="FactDef.findAll", query="SELECT a FROM FactDef a")
	,@NamedQuery(name="FactDef.findByRequired", query="SELECT a FROM FactDef a WHERE a.required = :required")
	,@NamedQuery(name="FactDef.findByRegex", query="SELECT a FROM FactDef a WHERE a.regex = :regex")
	,@NamedQuery(name="FactDef.findByRegexContaining", query="SELECT a FROM FactDef a WHERE a.regex like :regex")
	,@NamedQuery(name="FactDef.findByMin", query="SELECT a FROM FactDef a WHERE a.min = :min")
	,@NamedQuery(name="FactDef.findByMinContaining", query="SELECT a FROM FactDef a WHERE a.min like :min")
	,@NamedQuery(name="FactDef.findByMax", query="SELECT a FROM FactDef a WHERE a.max = :max")
	,@NamedQuery(name="FactDef.findByMaxContaining", query="SELECT a FROM FactDef a WHERE a.max like :max")
})
public class FactDef implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "FactDef.findAll";
    public static final String FIND_BY_REQUIRED = "FactDef.findByRequired";
    public static final String FIND_BY_REGEX = "FactDef.findByRegex";
    public static final String FIND_BY_REGEX_CONTAINING ="FactDef.findByRegexContaining";
    public static final String FIND_BY_MIN = "FactDef.findByMin";
    public static final String FIND_BY_MIN_CONTAINING ="FactDef.findByMinContaining";
    public static final String FIND_BY_MAX = "FactDef.findByMax";
    public static final String FIND_BY_MAX_CONTAINING ="FactDef.findByMaxContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="required"   , nullable=false , unique=false)
    private Boolean required; 

    @Column(name="regex"  , length=200 , nullable=true , unique=false)
    private String regex; 

    @Column(name="min"  , length=100 , nullable=true , unique=false)
    private String min; 

    @Column(name="max"  , length=100 , nullable=true , unique=false)
    private String max;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="fact_type_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private FactType factTypeId;  

    @Column(name="fact_type_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer factTypeId_;

    @ManyToOne (fetch=FetchType.LAZY )
    @JoinColumn(name="resource_layout_id", referencedColumnName = "id" , nullable=true , unique=true  , insertable=true, updatable=true) 
    private ResourceLayout resourceLayoutId;  

    @Column(name="resource_layout_id"  , nullable=true , unique=true, insertable=false, updatable=false)
    private Integer resourceLayoutId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    @OneToMany (targetEntity=Fact.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Fact> factFactDefViaFactDefId = new HashSet<Fact>(); 

    @OneToMany (targetEntity=FactDefLang.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <FactDefLang> factDefLangFactDefViaFactDefId = new HashSet<FactDefLang>(); 

    @OneToMany (targetEntity=OptionDef.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <OptionDef> optionDefFactDefViaFactDefId = new HashSet<OptionDef>(); 

    @OneToMany (targetEntity=Resource.class, fetch=FetchType.LAZY, mappedBy="factDefId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Resource> resourceFactDefViaFactDefId = new HashSet<Resource>(); 

    public FactDef() {
    }

    public FactDef(
       Integer id,
       Integer sectionDefId,
       Integer factTypeId,
       Boolean required,
       String regex,
       String min,
       String max,
       Integer resourceLayoutId) {
	 this(
       id,
       sectionDefId,
       factTypeId,
       required,
       regex,
       min,
       max,
       resourceLayoutId
	 ,true);
	}
    
	public FactDef(
       Integer id,
       Integer sectionDefId,
       Integer factTypeId,
       Boolean required,
       String regex,
       String min,
       String max,
       Integer resourceLayoutId	
    , boolean setRelationship) {
       setId (id);
       setRequired (required);
       setRegex (regex);
       setMin (min);
       setMax (max);
       if (setRelationship && factTypeId!=null) {
          this.factTypeId = new FactType();
          this.factTypeId.setId(factTypeId);
	      setFactTypeId_ (factTypeId);
       }
       if (setRelationship && resourceLayoutId!=null) {
          this.resourceLayoutId = new ResourceLayout();
          this.resourceLayoutId.setId(resourceLayoutId);
	      setResourceLayoutId_ (resourceLayoutId);
       }
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
	      setSectionDefId_ (sectionDefId);
       }
    }

	public FactDef flat() {
	   return new FactDef(
          getId(),
          getSectionDefId_(),
          getFactTypeId_(),
          getRequired(),
          getRegex(),
          getMin(),
          getMax(),
          getResourceLayoutId_()
       , false
	   );
	}

    public Integer getId() {
        return id;
    }
	
    public void setId (Integer id) {
        this.id =  id;
    }

    public Boolean getRequired() {
        return required;
    }
	
    public void setRequired (Boolean required) {
        this.required =  required;
    }

    public String getRegex() {
        return regex;
    }
	
    public void setRegex (String regex) {
        this.regex =  regex;
    }

    public String getMin() {
        return min;
    }
	
    public void setMin (String min) {
        this.min =  min;
    }

    public String getMax() {
        return max;
    }
	
    public void setMax (String max) {
        this.max =  max;
    }

    public FactType getFactTypeId () {
    	return factTypeId;
    }
	
    public void setFactTypeId (FactType factTypeId) {
    	this.factTypeId = factTypeId;
    }

    public Integer getFactTypeId_() {
        return factTypeId_;
    }
	
    public void setFactTypeId_ (Integer factTypeId) {
        this.factTypeId_ =  factTypeId;
    }
	
    public ResourceLayout getResourceLayoutId () {
    	return resourceLayoutId;
    }
	
    public void setResourceLayoutId (ResourceLayout resourceLayoutId) {
    	this.resourceLayoutId = resourceLayoutId;
    }

    public Integer getResourceLayoutId_() {
        return resourceLayoutId_;
    }
	
    public void setResourceLayoutId_ (Integer resourceLayoutId) {
        this.resourceLayoutId_ =  resourceLayoutId;
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

    public Set<Fact> getFactFactDefViaFactDefId() {
        if (factFactDefViaFactDefId == null){
            factFactDefViaFactDefId = new HashSet<Fact>();
        }
        return factFactDefViaFactDefId;
    }

    public void setFactFactDefViaFactDefId (Set<Fact> factFactDefViaFactDefId) {
        this.factFactDefViaFactDefId = factFactDefViaFactDefId;
    }	
    
    public void addFactFactDefViaFactDefId (Fact element) {
    	    getFactFactDefViaFactDefId().add(element);
    }

    public Set<FactDefLang> getFactDefLangFactDefViaFactDefId() {
        if (factDefLangFactDefViaFactDefId == null){
            factDefLangFactDefViaFactDefId = new HashSet<FactDefLang>();
        }
        return factDefLangFactDefViaFactDefId;
    }

    public void setFactDefLangFactDefViaFactDefId (Set<FactDefLang> factDefLangFactDefViaFactDefId) {
        this.factDefLangFactDefViaFactDefId = factDefLangFactDefViaFactDefId;
    }	
    
    public void addFactDefLangFactDefViaFactDefId (FactDefLang element) {
    	    getFactDefLangFactDefViaFactDefId().add(element);
    }

    public Set<OptionDef> getOptionDefFactDefViaFactDefId() {
        if (optionDefFactDefViaFactDefId == null){
            optionDefFactDefViaFactDefId = new HashSet<OptionDef>();
        }
        return optionDefFactDefViaFactDefId;
    }

    public void setOptionDefFactDefViaFactDefId (Set<OptionDef> optionDefFactDefViaFactDefId) {
        this.optionDefFactDefViaFactDefId = optionDefFactDefViaFactDefId;
    }	
    
    public void addOptionDefFactDefViaFactDefId (OptionDef element) {
    	    getOptionDefFactDefViaFactDefId().add(element);
    }

    public Set<Resource> getResourceFactDefViaFactDefId() {
        if (resourceFactDefViaFactDefId == null){
            resourceFactDefViaFactDefId = new HashSet<Resource>();
        }
        return resourceFactDefViaFactDefId;
    }

    public void setResourceFactDefViaFactDefId (Set<Resource> resourceFactDefViaFactDefId) {
        this.resourceFactDefViaFactDefId = resourceFactDefViaFactDefId;
    }	
    
    public void addResourceFactDefViaFactDefId (Resource element) {
    	    getResourceFactDefViaFactDefId().add(element);
    }

    @javax.persistence.PrePersist
    public void prePersist_ () {
    }

    @javax.persistence.PreUpdate
    public void preUpdate_ () {
    }

}
