<#-- @ftlvariable name="users" type="kotlin.collections.List<com.wsr.model.h2.entities.User>" -->
<#import "layouts/app.ftl" as app/>
<@app.page>
    <#list users as user>
        <div>${user.userId}</div>
    </#list>
</@app.page>
