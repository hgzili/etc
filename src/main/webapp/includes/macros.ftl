<!-- 
this macro will render a tab item
PARAMETERS:
title		- the text of the link
url 		- the URI to link to
selected	- the nav item will be selected if this link matches request.getServletPath().startsWith(selected)
tooltip		- an optional phrase to be used as the tooltip for the navitem link.
accessKey   - an optional access key
-->
<#macro tabItem title url selected tooltip="" accessKey="" target="_self">
	<#assign matched = req.servletPath?starts_with(selected)>
	<#if matched><th><#else><td></#if>
	<a href="${url}" title="${tooltip}" accessKey="${accessKey}" target="${target}">${title}</a>
	<#if matched></th><#else></td></#if>
</#macro>

<#macro mainRefreshForm includeFile spanId>
<span id="${spanId}">
  <#include "${includeFile}">
</span>
</#macro>

<#macro subRefreshForm includeFile spanId>
<textarea id="_HIDDEN_TABLE_${spanId}" style="display:none">
	<#include "${includeFile}">
</textarea>	

<script>
    window.opener.document.getElementById("${spanId}").innerHTML = document.getElementById("_HIDDEN_TABLE_${spanId}").value;
</script>
</#macro>
<!--
this macro will render the directory link and a getEmployeeInfo function must be existed in action or base action
    public EmployeeInfo getEmployeeInfo(String employeeNo) {
        EmployeeInfo result = ((EmployeeService) Application.getInstance().getContainer().getComponent(EmployeeService.class)).loadEmployeeInfoById(employeeNo);
        if(result == null) {
            result = new EmployeeInfoImpl(employeeNo);
        }
        return result;
    }
PARAMETERS:
employeeNo
-->
<#macro directoryLink employeeNo>
	<#if employeeNo?has_content>
	<#assign employeeInfo = action.getEmployeeInfo(employeeNo)>
	<a href="http://ap1hr01:8080/directory/control/empDirDetail?employeeId=${employeeInfo.employeeNo}" target="_blank">${employeeNo} ${employeeInfo.preferredName}</a>
	</#if>
</#macro>


<!--
this macro will render a PaginationSupport index switcher
PARAMETERS:
formName - which form will be submited
-->
<#macro pager formName exportActionName="">
<#assign base = req.contextPath />
<script>
	function paginationSearch(formName, index){
		var formObj = document.forms[formName];
		formObj.reset();
		formObj['paginationSupport.startIndex'].value = index;
		formObj.submit();
	}
</script>
<div align="center">
<#if paginationSupport.previousStartIndexes?has_content>
	<a href="#" onClick="paginationSearch('${formName}', 0);"><img src="${base}/images/icon_first_16.gif" align="absmiddle" alt="First" title="First"/></a> &nbsp;
	<a href="#" onClick="paginationSearch('${formName}', ${paginationSupport.previousIndex});"><img src="${base}/images/icon_previous_16.gif" align="absmiddle" alt="Previous" title="Previous"/></a> &nbsp;
</#if>	

<#if paginationSupport.totalCount!=0>
	Displaying ${paginationSupport.startIndex + 1} to ${paginationSupport.endIndex} of ${paginationSupport.totalCount}
<#else>
    Displaying 0 to 0 of 0
</#if>	
<#if paginationSupport.nextStartIndexes?has_content>
	&nbsp; <a href="#" onClick="paginationSearch('${formName}', ${paginationSupport.nextIndex});"><img src="${base}/images/icon_next_16.gif" align="absmiddle" alt="Next" title="Next"/></a>
	&nbsp; <a href="#" onClick="paginationSearch('${formName}', ${paginationSupport.nextStartIndexes?last});"><img src="${base}/images/icon_last_16.gif" align="absmiddle" alt="Last" title="Last"/></a>
</#if>
</div>

<#if exportActionName != "">
<script>
	function paginationExport(formName) {
		var formObj = document.forms[formName];
		formObj.reset();
		//backup old form values
		var actionName = formObj.action;
		var startIndex = formObj['paginationSupport.startIndex'].value;
		var countOnEachPage = formObj['paginationSupport.countOnEachPage'].value;
		
		//submit to export action
		formObj.action = '${exportActionName}';
		formObj['paginationSupport.startIndex'].value = 0;
		formObj['paginationSupport.countOnEachPage'].value = 999999;
		formObj.target = "_blank";
		formObj.submit();
		
		//restore
		formObj.action = actionName;
		formObj['paginationSupport.startIndex'].value = startIndex;
		formObj['paginationSupport.countOnEachPage'].value = countOnEachPage;
		formObj.target = "_self";		
	}
</script>
<div align="center">
	<a href="#" onClick="paginationExport('${formName}');">Export To Excel: <img src="${base}/images/icon_file_excel.png" align="absmiddle"/></a>
</div>
</#if>
</#macro>

<!--
this macro will render an employee no selector
PARAMETERS:
fieldName
fieldId - for same field name in a page
value
callbackName - callback function name, default will call the field filling funtion.
-->
<#macro employeeSelector fieldName fieldId=fieldName value="" callbackName="">
<#assign ww = JspTaglibs["/WEB-INF/webwork.tld"] />
<#assign base = req.contextPath />
<script type="text/javascript" src="${base}/js/xmlextras.js"></script>
<script>
	var employeeSearchPopup;
	var windowOptions = "directories=no,location=no,width=700,height=500,alwaysRaised=yes,resizable=yes,scrollbars=yes";
	function popupSelector_${fieldId?replace(".", "_")}() {
		employeeSearchPopup = window.open('${base}/exporthr/index.action', 'employeeSearchPopup', windowOptions);
		employeeSearchPopup.focus();
		<#if callbackName == "">
		callback = function (employeeNo){
			document.getElementById('${fieldId}').value = employeeNo;
			employeeSearchPopup.close();
		};
		<#else>
		callback = function (employeeNo) {
			${callbackName}(employeeNo);
		};
		</#if>
	}
	function checkEmployeeNo(inputField) {
		var employeeNo = inputField.value;
	    inputField.value = employeeNo.toUpperCase();		
		if(employeeNo != null && employeeNo.length > 0){
			var xmlHttp = XmlHttp.create();
		   	var async = false;
		   	xmlHttp.open("GET", "${base}/exporthr/checkEmployeeNo.action?employeeNo=" + inputField.value, async);
		    xmlHttp.send(null);
			if(xmlHttp.responseText != null && xmlHttp.responseText == '<success/>'){
				//success
		    }else if(xmlHttp.responseXML != null && xmlHttp.responseXML.getElementsByTagName("success").length > 0){
		    	//success
		    }else{
		    	alert("Can not find an employee with the No. " + employeeNo);
		    	inputField.focus();		    	
		    	inputField.select();
		    }
		}
	}
</script>
<#if value == "">
<@ww.textfield name="'${fieldName}'" id="${fieldId}" size="20" theme="'simple'" onkeyup="'this.value=this.value.toUpperCase().replace(\\' \\',\\'\\');'" onblur="'checkEmployeeNo(this);'"/> <a href="#" onclick="popupSelector_${fieldId?replace(".", "_")}();"><img src="${base}/images/icon_usergroups_sml.gif" align="absmiddle"/></a>
<#else>
<@ww.textfield name="'${fieldName}'" id="${fieldId}" size="20" theme="'simple'" onkeyup="'this.value=this.value.toUpperCase().replace(\\' \\',\\'\\');'" onblur="'checkEmployeeNo(this);'" value="'${value}'"/> <a href="#" onclick="popupSelector_${fieldId?replace(".", "_")}();"><img src="${base}/images/icon_usergroups_sml.gif" align="absmiddle"/></a>
</#if>
</#macro>

<#macro employeeSelectorWithResign fieldName fieldId=fieldName value="" callbackName="">
<#assign ww = JspTaglibs["/WEB-INF/webwork.tld"] />
<#assign base = req.contextPath />
<script type="text/javascript" src="${base}/js/xmlextras.js"></script>
<script>
	var employeeSearchPopup;
	var windowOptions = "directories=no,location=no,width=700,height=500,alwaysRaised=yes,resizable=yes,scrollbars=yes";
	function popupSelector_${fieldId?replace(".", "_")}() {
		employeeSearchPopup = window.open('${base}/exporthr/indexWithResign.action', 'employeeSearchPopup', windowOptions);
		employeeSearchPopup.focus();
		<#if callbackName == "">
		callback = function (employeeNo){
			document.getElementById('${fieldId}').value = employeeNo;
			employeeSearchPopup.close();
		};
		<#else>
		callback = function (employeeNo) {
			${callbackName}(employeeNo);
		};
		</#if>
	}
	function checkEmployeeNo(inputField) {
		var employeeNo = inputField.value;
	    inputField.value = employeeNo.toUpperCase();		
		if(employeeNo != null && employeeNo.length > 0){
			var xmlHttp = XmlHttp.create();
		   	var async = false;
		   	xmlHttp.open("GET", "${base}/exporthr/checkEmployeeNoWithResign.action?employeeNo=" + inputField.value, async);
		    xmlHttp.send(null);
			if(xmlHttp.responseText != null && xmlHttp.responseText == '<success/>'){
				//success
		    }else if(xmlHttp.responseXML != null && xmlHttp.responseXML.getElementsByTagName("success").length > 0){
		    	//success
		    }else{
		    	alert("Can not find an employee with the No. " + employeeNo);
		    	inputField.focus();		    	
		    	inputField.select();
		    }
		}
	}
</script>
<#if value == "">
<@ww.textfield name="'${fieldName}'" id="${fieldId}" size="20" theme="'simple'" onkeyup="'this.value=this.value.toUpperCase().replace(\\' \\',\\'\\');'" onblur="'checkEmployeeNo(this);'"/> <a href="#" onclick="popupSelector_${fieldId?replace(".", "_")}();"><img src="${base}/images/icon_usergroups_sml.gif" align="absmiddle"/></a>
<#else>
<@ww.textfield name="'${fieldName}'" id="${fieldId}" size="20" theme="'simple'" onkeyup="'this.value=this.value.toUpperCase().replace(\\' \\',\\'\\');'" onblur="'checkEmployeeNo(this);'" value="'${value}'"/> <a href="#" onclick="popupSelector_${fieldId?replace(".", "_")}();"><img src="${base}/images/icon_usergroups_sml.gif" align="absmiddle"/></a>
</#if>
</#macro>

<!--
this macro will render an multi employee no selector
PARAMETERS:
fieldName
empNos
min	  --  the number of employee selector
npl   --  how many employee selector will be show in one row.
-->
<#macro employeeSelectors name empNos=[] min=2 npl=4>
<#assign ww = JspTaglibs["/WEB-INF/webwork.tld"] />
<#assign base = req.contextPath />
<span id="${name}.wall">
<#assign size = 0>
<#if empNos?has_content>
	<#assign size = empNos?size>
	<#list empNos as member>
		<#if member_index &gt; 0 & member_index % npl == 0>
			<br/>
		</#if>
		<@ww.textfield id="'${name}.${member_index}'" name="'${name}'" value="'${empNos[member_index]}'" onclick="'popupSelector(this.id)'" onfocus="'this.blur()'" onblur="'autoControl(this);'" theme="'simple'"/>&nbsp;&nbsp;
	</#list>
</#if>
<#assign blank = min-size>
<#if blank &gt; 0>
	<#list 1..blank as a>
		<@ww.textfield id="${name}.${size+a-1}" name="'${name}'" value="" theme="'simple'" onclick="'popupSelector(this.id)'" onfocus="'this.blur()'" onblur="'autoControl(this);'"/>&nbsp;&nbsp;
		<#if (size+a-1) &gt; 0 & (size+a-1) % npl == 0>
		<br/>
		</#if>
	</#list>
</#if>
</span>
<script>
function autoControl(obj) {
	var name = obj.name ;
	var tfs = obj.form.all[name];
	var all = tfs.length ;
	var allWithValue = 0 ;
	for(i=0;i<all;i++){
		if(tfs[i].value != "") {
			allWithValue++ ;
		}
	}
	if(all == allWithValue) {
		var h = document.getElementById(name+".wall").innerHTML ;
		if((all++)%${npl}==0)
			h += '<br/>' ;
		h += '<input type="text" id="' + name + '.' + all + '" name="' + name + '" onfocus="this.blur()" onblur="autoControl(this);" onclick="popupSelector(this.id)"/>&nbsp;&nbsp;' ;
		document.getElementById(name + ".wall").innerHTML = h ;
	}
}
autoControl(document.forms[0].all['${name}'][0]);
var employeeSearchPopup;
var windowOptions = "directories=no,location=no,width=700,height=500,alwaysRaised=yes,resizable=yes,scrollbars=yes";
function popupSelector(id) {
	employeeSearchPopup = window.open('${base}/exporthr/index.action', 'employeeSearchPopup', windowOptions);
	employeeSearchPopup.focus();
	callback = function (employeeNo){
		document.getElementById(id).value = employeeNo;
		employeeSearchPopup.close();
		autoControl(document.forms[0].all[document.getElementById(id).name][0]) ;
	};
}
</script>
</#macro>

<!--
this macro will render i18n value
PARAMETERS:
key - key in properties files, default as empty string to avoid NPE
-->
<#macro i18n key="">
	${action.getText(key)}
</#macro>

<!--
this macro will render a check box for boolean field and data binding 
PARAMETERS:
fieldName - a boolean field name
-->
<#macro checkBoxBoolean fieldName>
	<#assign value = ("(" + fieldName + ")?default(false)")?eval/>
	<input type="checkbox" <#if value>checked="true"</#if> onclick="javascript:document.getElementById('_${fieldName}_hidden_').value = this.checked ? 'true' : 'false'">
	<input type="hidden" id="_${fieldName}_hidden_" name="${fieldName}" value="${value?string}"/>
</#macro>

<!--
this macro will render a check box list that can set how many items in a line 
PARAMETERS:
fieldName - a check box list field name
listValues- a list or map valu of the field example:list->["a","b"],map->{"display name1":"value 1","display name2":"value 2"}
lineCount - how many items in a line
-->
<#macro checkBoxList listValues fieldName lineCount=1>
<#assign fieldValue = ("(" + fieldName + ")?if_exists")?eval/>
<table border="0">
<#if listValues?is_sequence>
	<#list listValues?chunk(lineCount) as row>
	<tr>
		<#list row as eachOne>
		<td>
			<input type="checkbox" name="${fieldName}" value="${eachOne}" id="${fieldName}-${row_index}-${eachOne_index}" <#if fieldValue?seq_contains(eachOne)>checked="true"</#if>/>
			<label for="${fieldName}-${row_index}-${eachOne_index}" class="checkboxLabel">${eachOne}</label>
		</td>
		</#list>
	</tr>
	</#list>
<#elseif listValues?is_hash_ex>
	<#assign keys = listValues?keys>
	<#list keys?chunk(lineCount) as row>
	<tr>
		<#list row as eachOne>
		<td>
			<input type="checkbox" name="${fieldName}" value="${listValues[eachOne]}" id="${fieldName}-${row_index}-${eachOne_index}" <#if fieldValue?seq_contains(listValues[eachOne])>checked="true"</#if>/>
			<label for="${fieldName}-${row_index}-${eachOne_index}" class="checkboxLabel">${eachOne}</label>
		</td>
		</#list>
	</tr>
	</#list>
<#else>
listValues input error!!
</#if>
</table>
</#macro>

<!--
this macro will display org name by orgId 
PARAMETERS:
	orgId
-->
<#macro orgName orgId>
	<#if orgId?has_content>
	<#assign org = action.getOrganizationById(orgId)>
	${orgId}:${(org.orgDesc)?default('The org is closed')}${org.effective?string("","(The org is closed)")}
	</#if>
</#macro>

<!--
this macro will fiter org  by your inputed filtername
PARAMETERS:
	orgId 
	id
	orgFilterName
-->
<#macro orgSelect orgId id="" orgFilterName="" >
<#assign ww = JspTaglibs["/WEB-INF/webwork.tld"] />
<#if id == "">
<@ww.select name="'${orgId}'" id="newOrgId" list="getOrganizationService().loadAllOrganizations()" listKey="orgId" listValue="orgDesc" headerKey="''" headerValue="'Please Select'" required="true" theme="'simple'"/>
	<script>
		var newOrgFilter = new filterlist(document.getElementById('newOrgId'));			
	</script>
	Filter: <input type="text" onblur="newOrgFilter.set(this.value);"/>
<#else>
<@ww.select name="'${orgId}'" id="${id}" list="getOrganizationService().loadAllOrganizations()" listKey="orgId" listValue="orgDesc" headerKey="''" headerValue="'Please Select'" required="true" theme="'simple'"/>
	<script>
		var ${orgFilterName} = new filterlist(document.getElementById('${id}'));			
	</script>
	Filter: <input type="text" onblur="${orgFilterName}.set(this.value);"/>
</#if>
</#macro>

