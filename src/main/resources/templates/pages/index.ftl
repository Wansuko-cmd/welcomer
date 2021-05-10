<#-- @ftlvariable name="sentMessages" type="kotlin.collections.List<com.wsr.model.h2.entities.SentMessage>" -->

<#assign title="KoTo">

<#import "../layouts/app.ftl" as app/>
<@app.content title=title>
    <div class="container">
        <button class="btn btn-info">TEST</button>
        <table>
            <#list sentMessages as sentMessage>
                <tr>
                    <th>${sentMessage.comingMessage}</th>
                    <td>${sentMessage.reply}</td>
                </tr>
            </#list>
        </table>
    </div>
</@app.content>
