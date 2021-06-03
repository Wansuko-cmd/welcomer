## Welcomer
SlackBotの返信用APIおよび管理画面を用意するアプリケーション


### 用途
Slackで何かしらの行動（ワークスペースに入ってくる、発言する、etc...）があったときに発言させる

### 名称の由来
新規でワークスペースに入ってきた人に自己紹介を促すのを良く忘れてしまうため

名称は後日変更の恐れあり

### 編集方法

今回のプロジェクトは何かしらのアクションに答える形を想定している

従って、アクションを検知した際に`Service`の`makeMessage`が呼び出されるようになっている

この関数の返り値を上手く調整してやることで予定通りのメッセージを返すようになる

どうしても`Kotlin`で書きたくない人はGAS等を用いて、叩いたらJsonを返すプログラムを作ること

また、このプロジェクトはPostgresqlを利用している

Dockerfileを用意していないため、独自で用意するか、テストを書くかすること

独自で用意する場合は環境変数ファイルが必要になるため、問い合わせしてください

### サーバーの起動方法
ルートファイル直下の`gradlew`（Windowsユーザーは`gradlew.bat`）ファイルを叩く

また、オートリロードにしたい場合は以下のコマンドも実行する

```
# Mac, Linux
./gradlew -t installDist

# Windows
./gradlew.bat -t installDist
```

[comment]: <> (但し、テスト自体はサーバーを立ち上げなくても動くため、これらのステップをする必要はない)

### TODO

- [x] プロジェクトを作成する

- [ ] Koinを駆使してテストを書く

- [x] 基礎コードを実装する

- [x] Local上でSlackのBotと連携

- [x] チャンネルにメッセージを送信

- [ ] 送信するメッセージを細部まで実装する
 
- [x] クラウド上に上げる

- [x] クラウド上でSlackと連携する

- [x] チームに入ってきた場合の動作を確認する

- [x] Botにメンションを投げられた時の動作を確認する

- [ ] サーバーのログの処理を記述する



### このサーバーについて
使用言語： Kotlin

使用したサーバーフレームワーク： Ktor

使用したデータベース： Postgresql

使用したデータベース関連技術： hikari, flyway

使用したテストフレームワーク： TestEngine, JUnit

使用したシリアライゼーション： Kotlinx.serialization

使用したDIライブラリ： Koin
