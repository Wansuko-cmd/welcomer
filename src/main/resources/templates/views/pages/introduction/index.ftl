<#-- @ftlvariable name="introduction" type="String" -->

<#assign title="KoTo-Introduction">

<#import "../../layouts/app.ftl" as app/>
<#import "../../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">
            <@component.card title="紹介文">
                <div class="h5 rounded border p-5">
                    ${introduction}
                </div>
            </@component.card>

            <@component.card title="送信したユーザー">
                <div class="h5 rounded border p-5">
                    ${introduction}
                </div>
            </@component.card>
        </div>
    </div>
</@app.content>
