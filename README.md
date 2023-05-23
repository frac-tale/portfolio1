# portfolio1
使用言語：java

開発環境：eclipse

フレームワーク：Spring

データベース：postgreSQL


閲覧いただき、ありがとうございます。以下に簡単な仕様を書かせていただきます。  


・目的：PostgreSQLとWebページ間をデータ連携した、Webアプリケーションの構築。  


・設定：

        データベース名；testdb
   
        テーブル名：database
   
        要素：   id        [serial]
        
                name       [text]
                
                age       [Integer]  
                

・動作：
 　databaseテーブルを構築し、該当WebページにHTMLのtableとして一覧表示させる。追加、編集、削除ボタンを設置し、その結果をWebページ、databaseテーブルに反映させる。
        また、保存ボタンを押下することで、eclipse上にbackup.txtを新たに生成し、テーブル要素をテキストデータとして保存する。  
        
        ■一例
          (初期状態)：　　　　　　　　　　　　　　　　　　　　　　　http://github.com/frac-tale/portfolio1/tree/master/images/first.png  
          (下部のテーブルに情報を入力後、追加)：　　　　　　　　　　https://github.com/frac-tale/portfolio1/tree/master/images/second.png  
          (catレコードの編集を押し、編集後のデータを書く)：        https://github.com/frac-tale/portfolio1/tree/master/images/third.png  
          (編集を押し、参照画面へ戻る)：                          https://github.com/frac-tale/portfolio1/tree/master/images/fourth.png           
          (dogレコードの削除を押す)：                             https://github.com/frac-tale/portfolio1/tree/master/images/fifth.png  
          (postgreSQLのテーブルの状態)：                         https://github.com/frac-tale/portfolio1/tree/master/images/final.png  
       

  ・課題：  
  
          削除コマンド、編集コマンドによって、idの連番が崩れたり、順序が入れ替わる。      
          Validationチェックがなお不十分。
    
        
        
以上となります。ありがとうございました。
