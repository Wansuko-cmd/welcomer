<#-- @ftlvariable name="introduction" type="String" -->

<#assign title="KoTo">

<#import "../../layouts/app.ftl" as app/>
<@app.content title=title>
    <div class="container">
        <h1>Introduction</h1>
        <h4>${introduction}</h4>
    </div>
</@app.content>
