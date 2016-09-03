package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Group</p>
 *
 * <p>Description: Domain Object describing a Group entity</p>
 *
 */
@Entity (name="Group")
@Table (name="\"group\"")
@NamedQueries ({
	 @NamedQuery(name="Group.findAll", query="SELECT a FROM Group a")
	,@NamedQuery(name="Group.findByName", query="SELECT a FROM Group a WHERE a.name = :name")
	,@NamedQuery(name="Group.findByNameContaining", query="SELECT a FROM Group a WHERE a.name like :name")

})
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Group.findAll";
    public static final String FIND_BY_NAME = "Group.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="Group.findByNameContaining";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @name-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @name-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-name@
    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentGroupViaGroupId-field-group@
    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="groupId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentGroupViaGroupId = new HashSet<Respondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Group() {
    }

	/**
	* All field constructor 
	*/
    public Group(
       Integer id,
       String name) {
	 this(
       id,
       name
	 ,true);
	}
    
	public Group(
       Integer id,
       String name	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setName (name);
       //parents
    }

	public Group flat() {
	   return new Group(
          getId(),
          getName()
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



//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @respondentGroupViaGroupId-getter-group@
    public Set<Respondent> getRespondentGroupViaGroupId() {
        if (respondentGroupViaGroupId == null){
            respondentGroupViaGroupId = new HashSet<Respondent>();
        }
        return respondentGroupViaGroupId;
    }

    public void setRespondentGroupViaGroupId (Set<Respondent> respondentGroupViaGroupId) {
        this.respondentGroupViaGroupId = respondentGroupViaGroupId;
    }	
    
    public void addRespondentGroupViaGroupId (Respondent element) {
    	    getRespondentGroupViaGroupId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
