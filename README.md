## Welcomer
SlackBot専用のサーバー

###用途
Slackで何かしらの行動（ワークスペースに入ってくる、発言する、etc...）があったときに発言させる

### 名称の由来
新規でワークスペースに入ってきた人に自己紹介を促すのを良く忘れてしまうため

名称は後日変更の恐れあり

### 編集方法
1. `src/test/kotlin/com/wsr/services/ServiceTest`に移動

2. テストを実行してみて、実際に動くか確認（動かなければどこかでやらかしている可能性あり）

3. 動けば、他のコードを参考にしながらテストをかく（JUnit）

4. テストを実行してみて、予想通りのエラーが出ているか確認する

5. `src/main/kotlin/com/wsr/services/Service.kt`に移動

6. `Service`に新規の関数を追加、記述する

7. `setMessage`に、条件に合わせてその関数を呼ぶように編集

8. テストを実行してみて、動作を確認

9. プルリクを出す

以上

今回のプロジェクトは何かしらのアクションに答える形を想定している

従って、アクションを検知した際に`Service`の`setMessage`が呼び出されるようになっている

この関数の返り値を上手く調整してやることで予定通りのメッセージを返すようになる

どうしても`Kotlin`で書きたくない人はGAS等を用いて、叩いたらJsonを返すプログラムを作ること

### サーバーの起動方法
ルートファイル直下の`gradlew`（Windowsユーザーは`gradlew.bat`）ファイルを叩く

また、オートリロードにしたい場合は以下のようにする

```
# Mac, Linux
./gradlew -t installDist

# Windows
./gradlew.bat -t installDist
```

但し、テスト自体はサーバーを立ち上げなくても動くため、これらのステップをする必要はない

### TODO

-[x] プロジェクトを作成する

-[x] テストを書く

-[x] 基礎コードを実装する

-[x] Local上でSlackのBotと連携

-[x] チャンネルにメッセージを送信

-[ ] 送信するメッセージを細部まで実装する
 
-[ ] クラウド上に上げる

-[ ] クラウド上でSlackと連携する

-[ ] チームに入ってきた場合の動作を確認する

-[ ] Botにメンションを投げられた時の動作を確認する

-[ ] サーバーのログの処理を記述する



### このサーバーについて
使用言語： Kotlin
使用したサーバーフレームワーク： Ktor
使用したテストフレームワーク： TestEngine, JUnit
使用したシリアライゼーション： Kotlinx.serialization
