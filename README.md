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
        

  ・課題：  
  
          削除コマンドによって、idの連番が崩れる。      
          Validationチェックが不十分。
    
        
        
ありがとうございました。
