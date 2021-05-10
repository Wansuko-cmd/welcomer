<#-- @ftlvariable name="sentMessages" type="kotlin.collections.List<com.wsr.model.h2.entities.SentMessage>" -->

<#assign title="KoTo">

<#import "../layouts/app.ftl" as app/>
<#import "../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">

            <@component.card title="メッセージ送信数">

            </@component.card>

            <@component.card title="送信履歴">
                <table class="table table-striped table-bordered" style="width: 850px;">
                    <thead class="table-dark">
                    <tr>
                        <th scope="col">送られてきたメッセージ</th>
                        <th scope="col">返答</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list sentMessages as sentMessage>
                        <tr>
                            <td>${sentMessage.comingMessage}</td>
                            <td>${sentMessage.reply}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </@component.card>
        </div>
    </div>
</@app.content>
