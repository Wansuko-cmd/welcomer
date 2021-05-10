<#assign title="KoTo-Message-Done">

<#import "../../layouts/app.ftl" as app/>
<#import "../../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">
            <@component.card title="送信完了しました">
                <a href="/message">
                    <button class="btn btn-primary my-4">元のページに戻る</button>
                </a>
            </@component.card>
        </div>
    </div>
</@app.content>
