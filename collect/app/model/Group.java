package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name="name"  , length=45 , nullable=false , unique=false)
    private String name; 

    @OneToMany (targetEntity=model.Respondent.class, fetch=FetchType.LAZY, mappedBy="groupId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <Respondent> respondentGroupViaGroupId = new HashSet<Respondent>(); 

    public Group() {
    }

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
       setId (id);
       setName (name);
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

    public String getName() {
        return name;
    }
	
    public void setName (String name) {
        this.name =  name;
    }

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
}
