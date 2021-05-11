<#import "./sidebar_component.ftl" as sidebar_component>

<div class="border border-black-50 d-none d-lg-block sidebar">

    <@sidebar_component.card url="/" title="ホーム" />

    <@sidebar_component.card url="/message" title="メッセージ" />

    <@sidebar_component.card url="/introduction" title="紹介文" />

</div>
