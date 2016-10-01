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
	* - name      : DomainEntityJPA2Metamodel
	* - file name : DomainEntityJPA2Metamodel.vm
	* - time      : 2016/09/04 AD at 08:23:25 BST
*/
package model;

import java.sql.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SetAttribute;

import model.ItemDef;
import model.Section;
import model.SectionDef;
import model.SectionDefLang;
import model.SectionDef;
import model.ContentDef;

@StaticMetamodel(SectionDef.class)
public class SectionDef_ {

    public static volatile SingularAttribute<SectionDef, Integer> id;

    public static volatile SingularAttribute<SectionDef, Boolean> dynamic;
    public static volatile SingularAttribute<SectionDef, SectionDef> parentSectionDefId;
    public static volatile SingularAttribute<SectionDef, Integer> parentSectionDefId_;
    public static volatile SingularAttribute<SectionDef, ContentDef> contentDefId;
    public static volatile SingularAttribute<SectionDef, Integer> contentDefId_;

    public static volatile SetAttribute<SectionDef, ItemDef> itemDefSectionDefViaSectionDefId;
    public static volatile SetAttribute<SectionDef, Section> sectionSectionDefViaSectionDefId;
    public static volatile SetAttribute<SectionDef, SectionDef> sectionDefSectionDefViaParentSectionDefId;
    public static volatile SetAttribute<SectionDef, SectionDefLang> sectionDefLangSectionDefViaSectionDefId;


}
