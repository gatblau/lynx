/**
	* Copyright (c) minuteproject, minuteproject@gmail.com
	* All rights reserved.
	* 
	* Licensed under the Apache License, Version 2.0 (the "License")
	* you may not use this file except in compliance with the License.
	* You may obtain a copy of the License at
	* 
	* http://www.apache.org/licenses/LICENSE-2.0
	* 
	* Unless required by applicable law or agreed to in writing, software
	* distributed under the License is distributed on an "AS IS" BASIS,
	* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	* See the License for the specific language governing permissions and
	* limitations under the License.
	* 
	* More information on minuteproject:
	* twitter @minuteproject
	* wiki http://minuteproject.wikispaces.com 
	* blog http://minuteproject.blogspot.net
	* 
*/
/**
	* template reference : 
	* - Minuteproject version : 0.9.8
	* - name      : DomainEntityJPA2Annotation
	* - file name : DomainEntityJPA2Annotation.vm
	* - time      : 2016/09/04 AD at 08:23:24 BST
*/
package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity (name="SectionDefLang")
@Table (name="\"section_def_lang\"")
@NamedQueries ({
	 @NamedQuery(name="SectionDefLang.findAll", query="SELECT a FROM SectionDefLang a")
	,@NamedQuery(name="SectionDefLang.findByName", query="SELECT a FROM SectionDefLang a WHERE a.name = :name")
	,@NamedQuery(name="SectionDefLang.findByNameContaining", query="SELECT a FROM SectionDefLang a WHERE a.name like :name")
	,@NamedQuery(name="SectionDefLang.findByDescription", query="SELECT a FROM SectionDefLang a WHERE a.description = :description")
	,@NamedQuery(name="SectionDefLang.findByDescriptionContaining", query="SELECT a FROM SectionDefLang a WHERE a.description like :description")
})
public class SectionDefLang implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL = "SectionDefLang.findAll";
    public static final String FIND_BY_NAME = "SectionDefLang.findByName";
    public static final String FIND_BY_NAME_CONTAINING ="SectionDefLang.findByNameContaining";
    public static final String FIND_BY_DESCRIPTION = "SectionDefLang.findByDescription";
    public static final String FIND_BY_DESCRIPTION_CONTAINING ="SectionDefLang.findByDescriptionContaining";
	
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
    @JoinColumn(name="section_def_id", referencedColumnName = "id" , nullable=false , unique=true  , insertable=true, updatable=true) 
    private SectionDef sectionDefId;  

    @Column(name="section_def_id"  , nullable=false , unique=true, insertable=false, updatable=false)
    private Integer sectionDefId_;

    public SectionDefLang() {
    }

    public SectionDefLang(
       Integer id,
       String name,
       String description,
       Integer sectionDefId,
       Integer languageId) {
	 this(
       id,
       name,
       description,
       sectionDefId,
       languageId
	 ,true);
	}
    
	public SectionDefLang(
       Integer id,
       String name,
       String description,
       Integer sectionDefId,
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
       if (setRelationship && sectionDefId!=null) {
          this.sectionDefId = new SectionDef();
          this.sectionDefId.setId(sectionDefId);
	      setSectionDefId_ (sectionDefId);
       }
    }

	public SectionDefLang flat() {
	   return new SectionDefLang(
          getId(),
          getName(),
          getDescription(),
          getSectionDefId_(),
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

}
