<#-- @ftlvariable name="users" type="kotlin.collections.List<com.wsr.model.h2.entities.User>" -->

<#assign title="KoTo">

<#import "layouts/app.ftl" as app/>
<@app.content title=title>
    <button class="btn btn-info">TEST</button>
    <#list users as user>
        <div>${user.userId}</div>
    </#list>
</@app.content>
