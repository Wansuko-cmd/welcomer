<#assign title="KoTo-Message">

<#import "../../layouts/app.ftl" as app/>
<#import "../../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">
            <@component.card title="メッセージの作成">
                <form action="/message" method="POST">
                    <label>
                        <textarea name="body" class="form-control" rows="10" style="width: 800px; font-size: large; border-radius: 30px" required></textarea>
                    </label>

                    <div class="text-end mx-5 mt-4">
                        <button type="submit" class="btn btn-primary">送信</button>
<#--                        <img src="src/koto.png" alt="表示できませんでした">-->
                    </div>
                </form>
            </@component.card>
        </div>
    </div>
</@app.content>
