# 概要

[API仕様書](https://tomoroltuto.github.io/Business-management-application-document/dist/index.html)を元に業務管理アプリケーション用REST APIを作成しました。下請業者との作業内容を共有し業務改善することを目指し、主な機能は以下のと通りです。

* 作業予定・作業実績を登録・編集・削除できる
* 作業予定・作業実績の一覧表を表示できる
* 過去の作業予定・作業実績の履歴を検索できる

[作成背景や全体仕様書は別のREADMEにまとめております](https://github.com/tomoroltuto/Business-management-application-document.git)

# 主な技術

### バックエンド
* Java 17
* SpringBoot v2.7.5

### インフラ・DB
* docker/docker compose 
* AWS(VPC・EC2)
* MySQL

### CI
* GitHub Actions

# 設計書

<h2>ER図</h2>

![ER図](https://user-images.githubusercontent.com/90845405/201934297-650bcf1c-b119-4502-b55d-a776db4d3c0a.jpg)

<h2>URL設計書</h2>

![新URL](https://user-images.githubusercontent.com/90845405/210511788-4b0c780a-8912-40dc-a9f7-78409a2a1579.jpg)

<h2>インフラ構成図</h2>

# テスト
以下にそれぞれの統合テスト・単独テスト・DBテストを記載します。

### user

<details><summary> 統合テスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | ユーザーが全件取得に成功すると200で内容を返すこと | 200 | 
| GET | ユーザーが全件取得できること | 200 | 
| GET | 存在するユーザのIDを指定したとき正常にユーザーが返されること| 200| 
| GET | 存在しないユーザーのidにアクセスしたときにと404が返ること | 404 | 
| GET | 検索時に該当するIDのユーザーがいないときエラーメッセージが返ること | 404 | 
| POST | ユーザー登録に成功すると201とレスポンスメッセージを返すこと | 201 | 
| POST | ユーザー登録時空文字nullの場合エラーメッセージを返すこと | 400 | 
| POST | ユーザー登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| PATCH | ユーザー更新に成功すると200とレスポンスメッセージを返すこと | 200 | 
| PATCH | ユーザー更新時に該当するIDのユーザーがいないときエラーメッセージを返すこ | 404 | 
| PATCH | ユーザー登録時空文字nullの場合エラーメッセージを返すこと | 400 | 
| PATCH | ユーザー登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| DELETE | 指定したデーターを1件削除できること | 204 | 
| DELETE | 削除時に該当するIDのユーザーがいないときエラーメッセージが返ること | 404 | 

</details>

<details><summary> 単体テスト表（Service）</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーを全件取得してそのまま返すこと | 200 | 
| GET | 存在するユーザのIDを指定したとき正常にユーザーが返されること | 200 | 
| GET | 検索時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること | 404 | 
| POST | ユーザーを新規登録できること | 201 | 
| PATCH | 指定したIDのユーザーを更新できること | 200 | 
| PATCH | 更新時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること | 404 | 
| DELETE | 指定したデーターを1件削除できること | 204 | 
| DELETE | 削除時に該当するIDのユーザーがいないときUserEntityNotFoundExceptionとなること | 404 | 

</details>

<details><summary> DBテスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーが取得できること | 200 | 
| GET | 存在するユーザのIDを指定してユーザーが取得できること | 200 | 
| GET | ユーザーが登録いされてないときに空のリストが返ること | 404 | 
| POST | ユーザーを新規登録できること | 201 | 
| PATCH | キーに紐づく1件の更新が出来ること | 200 |
| DELETE | 指定したデーターを1件削除できること | 204 | 

</details>

### schedule

<details><summary> 統合テスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | 作業予定が全件取得に成功すると200で内容を返すこと | 200 | 
| GET | 作業予定が全件取得できること | 200 | 
| GET | 存在する作業予定のIDを指定したとき正常に作業予定が返されること| 200| 
| GET | 存在しない作業予定のidにアクセスしたときにと404が返ること | 404 | 
| GET | 検索時に該当するIDの作業予定がいないときエラーメッセージが返ること | 404 | 
| POST | 作業予定の登録に成功すると201とLocationヘッダーとレスポンスメッセージを返すこと | 201 |  
| POST | 作業予定登録時過去の日付入力した場合エラーメッセージを返すこと | 400 | 
| POST | 作業予定登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| POST | 作業予定登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| POST | 作業予定登録時空白がある場合エラーメッセージを返すこと | 400 | 
| PATCH | 作業予定更新に成功すると200とレスポンスメッセージを返すこと | 200 | 
| PATCH | 作業予定更新時に該当するIDの作業予定がいないときエラーメッセージを返すこと | 404 | 
| PATCH | 作業予定更新時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| PATCH | 作業予定更新時人数が０の場合エラーメッセージを返すこと | 400 | 
| PATCH | 作業予定更新時人数が200以上の場合エラーメッセージを返すこと | 400 | 
| DELETE | 指定した作業予定を1件削除できること | 204 | 
| DELETE | 削除時に該当するIDの作業予定がいないときエラーメッセージが返ること | 404 | 

</details>

<details><summary> 単体テスト表(Service)　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべての作業予定を全件取得してそのまま返すこと | 200 | 
| GET | 存在する作業予定のIDを指定したとき正常に作業予定が返されること | 200 | 
| GET | 検索時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること | 404 | 
| POST | 作業予定を新規登録できること | 201 | 
| PATCH | 指定したIDの作業予定を更新できること | 200 | 
| PATCH | 更新時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること | 404 | 
| DELETE | 指定した作業予定を1件削除できること | 204 | 
| DELETE | 削除時に該当するIDの作業予定がいないときScheduleEntityNotFoundExceptionとなること | 404 | 

</details>

<details><summary> DBテスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべての作業予定が取得できること | 200 | 
| GET | 存在する作業予定のIDを指定して作業予定が取得できること | 200 | 
| GET | 作業予定が登録されてないときに空のリストが返ること | 404 | 
| POST | 作業予定を新規登録できること | 201 | 
| PATCH | キーに紐づく1件の更新が出来ること | 200 |
| DELETE | 指定した作業予定を1件削除できること | 204 | 

</details>

### performance

<details><summary> 統合テスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | 作業実績が全件取得に成功すると200で内容を返すこと | 200 | 
| GET | 作業実績が全件取得できること | 200 | 
| GET | 存在する作業実績のIDを指定したとき正常に作業実績が返されること| 200| 
| GET | 存在しない作業実績のidにアクセスしたときにと404が返ること | 404 | 
| GET | 検索時に該当するIDの作業実績がいないときエラーメッセージが返ること | 404 | 
| POST | 作業実績の登録に成功すると201とLocationヘッダーとレスポンスメッセージを返すこと | 201 | 
| POST | 作業実績登録時過去の日付入力した場合エラーメッセージを返すこと | 400 | 
| POST | 作業実績登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| POST | 作業実績登録時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| POST | 作業実績登録時空白がある場合エラーメッセージを返すこと | 400 | 
| PATCH | 作業実績更新に成功すると200とレスポンスメッセージを返すこと | 200 | 
| PATCH | 作業実績更新時に該当するIDの作業予定がいないときエラーメッセージを返すこと | 404 | 
| PATCH | 作業実績更新時文字数が256文字以上の場合エラーメッセージを返すこと | 400 | 
| PATCH | 作業実績更新時人数が200以上の場合エラーメッセージを返すこと | 400 | 
| DELETE | 指定した作業実績を1件削除できること | 204 | 
| DELETE | 削除時に該当するIDの作業実績がいないときエラーメッセージが返ること | 404 | 

</details>

<details><summary> 単体テスト表(Service)　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべての作業実績を全件取得してそのまま返すこと | 200 | 
| GET | 存在する作作業実績のIDを指定したとき正常に作業予定が返されること | 200 | 
| GET | 検索時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること | 404 | 
| POST | 作業実績を新規登録できること | 201 | 
| PATCH | 指定したIDの作業実績を更新できること | 200 | 
| PATCH | 更新時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること | 404 | 
| DELETE | 指定した作業実績を1件削除できること | 204 | 
| DELETE | 削除時に該当するIDの作業実績がいないときPerformanceEntityNotFoundExceptionとなること | 404 | 

</details>

<details><summary> DBテスト表　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべての作業実績が取得できること | 200 | 
| GET | 存在する作業実績のIDを指定して作業実績が取得できること | 200 | 
| GET | 作業実績が登録されてないときに空のリストが返ること | 404 | 
| POST | 作業実績を新規登録できること | 201 | 
| PATCH | キーに紐づく1件の更新が出来ること | 200 |
| DELETE | 指定した作業実績を1件削除できること | 204 | 

</details>

### userschedule

<details><summary> 統合テスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | ユーザーと作業予定が全件取得に成功すると200で内容を返すこと | 200 | 
| GET | ユーザーと作業予定が全件取得できること | 200 | 
| GET | 存在するユーザーと作業予定のIDを指定したとき正常にユーザーと作業予定が返されること| 200| 
| GET | 存在しないユーザーと作業予定のidにアクセスしたときにと404が返ること | 404 | 
| GET | 検索時に該当するIDのユーザーと作業予定がいないときエラーメッセージが返ること | 404 | 

</details>

<details><summary> 単体テスト表(Service)　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーと作業予定を全件取得してそのまま返すこと | 200 | 
| GET | 存在するユーザーと作業予定のIDを指定したとき正常に作業予定が返されること | 200 | 
| GET | 検索時に該当するIDのユーザーと作業予定がいないときUserEntityNotFoundExceptionとなること | 404 | 

</details>

<details><summary> DBテスト表　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーと作業予定が取得できること | 200 | 
| GET | ユーザーと作業予定が登録されてないときに空のリストが返ること | 200 | 
| GET | 存在するユーザーと作業予定のIDを指定してユーザーと作業予定が取得できること | 404 | 

</details>

### userperformance

<details><summary> 統合テスト表</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | ユーザーと作業実績が全件取得に成功すると200で内容を返すこと | 200 | 
| GET | ユーザーと作業実績が全件取得できること | 200 | 
| GET | 存在するユーザーと作業実績のIDを指定したとき正常にユーザーと作業実績が返されること| 200| 
| GET | 存在しないユーザーと作業実績のidにアクセスしたときにと404が返ること | 404 | 
| GET | 検索時に該当するIDのユーザーと作業実績がいないときエラーメッセージが返ること | 404 | 

</details>

<details><summary> 単体テスト表(Service)　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーと作業実績を全件取得してそのまま返すこと | 200 | 
| GET | 存在するユーザーと作業実績のIDを指定したとき正常にユーザーと作業実績が返されること | 200 | 
| GET | 検索時に該当するIDのユーザーと作業実績がいないときUserEntityNotFoundExceptionとなること | 404 | 

</details>

<details><summary> DBテスト表　</summary>

| メソッド|テスト内容 | ポート| 
| :---: | :---: |  :---: | 
| GET | すべてのユーザーと作業実績が取得できること | 200 | 
| GET | ユーザーと作業実績が登録されてないときに空のリストが返ること | 200 | 
| GET | 存在するユーザーと作業実績のIDを指定してユーザーと作業実績が取得できること | 404 | 

</details>

# 自動テスト

GitHub Actionsの導入して自動テストを行いました

<img width="1436" alt="CI状況" src="https://user-images.githubusercontent.com/90845405/210567519-39afb6c4-2178-4173-8c87-4b6bb6576f48.png">

# 工夫した点

* 仕様書や設計関係に時間をかけました。図を多めにして相手にも理解してもらえるように考えて書きました。

# ハマった点

* 最初にAPI仕様書作成しましたがいざ仕様書通りにうまくいかず悩みながら実装しました。
* SQLのテーブル結合をしたものをどうSpring bootで表示できるように実装するのか悩みました。

# 今後の展開

* ログイン機能に実装
* 外部連携(Line通知機能)
* フロント実装
* 作業予定の承認時、作業実績に反映できるように実装


