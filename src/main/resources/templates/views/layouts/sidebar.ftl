<#import "./sidebar_component.ftl" as sidebar_component>

<div style="width: 300px;" class="border border-black-50 box">

    <@sidebar_component.card url="/" title="ホーム" />

    <@sidebar_component.card url="/message" title="メッセージ" />

    <@sidebar_component.card url="/introduction" title="紹介文" />

</div>
