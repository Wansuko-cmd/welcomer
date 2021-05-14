<#-- @ftlvariable name="introduction" type="String" -->

<#assign title="KoTo-Introduction">

<#import "../../layouts/app.ftl" as app/>
<#import "../../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">
            <@component.card title="紹介文">
                <div class="h5 border p-5" style="width: 800px; font-size: large; border-radius: 30px; background-color: white">
                    ${introduction}
                </div>
            </@component.card>
        </div>
    </div>
</@app.content>
