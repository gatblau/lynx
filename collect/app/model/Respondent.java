package model;

//MP-MANAGED-ADDED-AREA-BEGINNING @import@
//MP-MANAGED-ADDED-AREA-ENDING @import@
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * <p>Title: Respondent</p>
 *
 * <p>Description: Domain Object describing a Respondent entity</p>
 *
 */
@Entity (name="Respondent")
@Table (name="\"respondent\"")
@NamedQueries ({
	 @NamedQuery(name="Respondent.findAll", query="SELECT a FROM Respondent a")
	,@NamedQuery(name="Respondent.findByFirstname", query="SELECT a FROM Respondent a WHERE a.firstname = :firstname")
	,@NamedQuery(name="Respondent.findByFirstnameContaining", query="SELECT a FROM Respondent a WHERE a.firstname like :firstname")

	,@NamedQuery(name="Respondent.findByLastname", query="SELECT a FROM Respondent a WHERE a.lastname = :lastname")
	,@NamedQuery(name="Respondent.findByLastnameContaining", query="SELECT a FROM Respondent a WHERE a.lastname like :lastname")

	,@NamedQuery(name="Respondent.findByEmail", query="SELECT a FROM Respondent a WHERE a.email = :email")
	,@NamedQuery(name="Respondent.findByEmailContaining", query="SELECT a FROM Respondent a WHERE a.email like :email")

	,@NamedQuery(name="Respondent.findByTelephone", query="SELECT a FROM Respondent a WHERE a.telephone = :telephone")
	,@NamedQuery(name="Respondent.findByTelephoneContaining", query="SELECT a FROM Respondent a WHERE a.telephone like :telephone")

	,@NamedQuery(name="Respondent.findByPwdHash", query="SELECT a FROM Respondent a WHERE a.pwdHash = :pwdHash")
	,@NamedQuery(name="Respondent.findByPwdHashContaining", query="SELECT a FROM Respondent a WHERE a.pwdHash like :pwdHash")

	,@NamedQuery(name="Respondent.findByActive", query="SELECT a FROM Respondent a WHERE a.active = :active")

})

public class Respondent implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "Respondent.findAll";
    public static final String FIND_BY_FIRSTNAME = "Respondent.findByFirstname";
    public static final String FIND_BY_FIRSTNAME_CONTAINING ="Respondent.findByFirstnameContaining";
    public static final String FIND_BY_LASTNAME = "Respondent.findByLastname";
    public static final String FIND_BY_LASTNAME_CONTAINING ="Respondent.findByLastnameContaining";
    public static final String FIND_BY_EMAIL = "Respondent.findByEmail";
    public static final String FIND_BY_EMAIL_CONTAINING ="Respondent.findByEmailContaining";
    public static final String FIND_BY_TELEPHONE = "Respondent.findByTelephone";
    public static final String FIND_BY_TELEPHONE_CONTAINING ="Respondent.findByTelephoneContaining";
    public static final String FIND_BY_PWDHASH = "Respondent.findByPwdHash";
    public static final String FIND_BY_PWDHASH_CONTAINING ="Respondent.findByPwdHashContaining";
    public static final String FIND_BY_ACTIVE = "Respondent.findByActive";
	
    @Id @Column(name="id" ) 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//MP-MANAGED-ADDED-AREA-BEGINNING @firstname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @firstname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-firstname@
    @Column(name="firstname"  , length=45 , nullable=false , unique=false)
    private String firstname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @lastname-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @lastname-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-lastname@
    @Column(name="lastname"  , length=45 , nullable=false , unique=false)
    private String lastname; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @email-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @email-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-email@
    @Column(name="email"  , length=45 , nullable=false , unique=false)
    private String email; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @telephone-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @telephone-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-telephone@
    @Column(name="telephone"  , length=45 , nullable=true , unique=false)
    private String telephone; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @pwd_hash-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @pwd_hash-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-pwd_hash@
    @Column(name="pwd_hash"  , length=45 , nullable=true , unique=false)
    private String pwdHash; 
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-ADDED-AREA-BEGINNING @active-field-annotation@
//MP-MANAGED-ADDED-AREA-ENDING @active-field-annotation@
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @ATTRIBUTE-active@
    @Column(name="active"   , nullable=false , unique=false)
    private Boolean active; 
//MP-MANAGED-UPDATABLE-ENDING

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="group_id", referencedColumnName = "id" , nullable=false , unique=false , insertable=true, updatable=true) 
    private Group groupId;  

    @Column(name="group_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer groupId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="language_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Language languageId;  

    @Column(name="language_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer languageId_;

    @ManyToOne (fetch=FetchType.LAZY , optional=false)
    @JoinColumn(name="role_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private Role roleId;  

    @Column(name="role_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer roleId_;

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentRespondentViaRespondentId-field-respondent@
    @OneToMany (targetEntity=model.SurveyRespondent.class, fetch=FetchType.LAZY, mappedBy="respondentId", cascade=CascadeType.REMOVE)//, cascade=CascadeType.ALL)
    private Set <SurveyRespondent> surveyRespondentRespondentViaRespondentId = new HashSet<SurveyRespondent>(); 

//MP-MANAGED-UPDATABLE-ENDING
    /**
    * Default constructor
    */
    public Respondent() {
    }

	/**
	* All field constructor 
	*/
    public Respondent(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean active,
       Integer languageId) {
	 this(
       id,
       firstname,
       lastname,
       email,
       telephone,
       pwdHash,
       roleId,
       groupId,
       active,
       languageId
	 ,true);
	}
    
	public Respondent(
       Integer id,
       String firstname,
       String lastname,
       String email,
       String telephone,
       String pwdHash,
       Integer roleId,
       Integer groupId,
       Boolean active,
       Integer languageId	
    , boolean setRelationship) {
       //primary keys
       setId (id);
       //attributes
       setFirstname (firstname);
       setLastname (lastname);
       setEmail (email);
       setTelephone (telephone);
       setPwdHash (pwdHash);
       setActive (active);
       //parents
       if (setRelationship && groupId!=null) {
          this.groupId = new Group();
          this.groupId.setId(groupId);
	      setGroupId_ (groupId);
       }
       if (setRelationship && languageId!=null) {
          this.languageId = new Language();
          this.languageId.setId(languageId);
	      setLanguageId_ (languageId);
       }
       if (setRelationship && roleId!=null) {
          this.roleId = new Role();
          this.roleId.setId(roleId);
	      setRoleId_ (roleId);
       }
    }

	public Respondent flat() {
	   return new Respondent(
          getId(),
          getFirstname(),
          getLastname(),
          getEmail(),
          getTelephone(),
          getPwdHash(),
          getRoleId_(),
          getGroupId_(),
          getActive(),
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
    
//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-firstname@
    public String getFirstname() {
        return firstname;
    }
	
    public void setFirstname (String firstname) {
        this.firstname =  firstname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-lastname@
    public String getLastname() {
        return lastname;
    }
	
    public void setLastname (String lastname) {
        this.lastname =  lastname;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-email@
    public String getEmail() {
        return email;
    }
	
    public void setEmail (String email) {
        this.email =  email;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-telephone@
    public String getTelephone() {
        return telephone;
    }
	
    public void setTelephone (String telephone) {
        this.telephone =  telephone;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-pwd_hash@
    public String getPwdHash() {
        return pwdHash;
    }
	
    public void setPwdHash (String pwdHash) {
        this.pwdHash =  pwdHash;
    }
	
//MP-MANAGED-UPDATABLE-ENDING

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @GETTER-SETTER-active@
    public Boolean getActive() {
        return active;
    }
	
    public void setActive (Boolean active) {
        this.active =  active;
    }
	
//MP-MANAGED-UPDATABLE-ENDING


    public Group getGroupId () {
    	return groupId;
    }
	
    public void setGroupId (Group groupId) {
    	this.groupId = groupId;
    }

    public Integer getGroupId_() {
        return groupId_;
    }
	
    public void setGroupId_ (Integer groupId) {
        this.groupId_ =  groupId;
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
	
    public Role getRoleId () {
    	return roleId;
    }
	
    public void setRoleId (Role roleId) {
    	this.roleId = roleId;
    }

    public Integer getRoleId_() {
        return roleId_;
    }
	
    public void setRoleId_ (Integer roleId) {
        this.roleId_ =  roleId;
    }
	

//MP-MANAGED-UPDATABLE-BEGINNING-DISABLE @surveyRespondentRespondentViaRespondentId-getter-respondent@
    public Set<SurveyRespondent> getSurveyRespondentRespondentViaRespondentId() {
        if (surveyRespondentRespondentViaRespondentId == null){
            surveyRespondentRespondentViaRespondentId = new HashSet<SurveyRespondent>();
        }
        return surveyRespondentRespondentViaRespondentId;
    }

    public void setSurveyRespondentRespondentViaRespondentId (Set<SurveyRespondent> surveyRespondentRespondentViaRespondentId) {
        this.surveyRespondentRespondentViaRespondentId = surveyRespondentRespondentViaRespondentId;
    }	
    
    public void addSurveyRespondentRespondentViaRespondentId (SurveyRespondent element) {
    	    getSurveyRespondentRespondentViaRespondentId().add(element);
    }
    
//MP-MANAGED-UPDATABLE-ENDING



//MP-MANAGED-ADDED-AREA-BEGINNING @implementation@
//MP-MANAGED-ADDED-AREA-ENDING @implementation@

}
