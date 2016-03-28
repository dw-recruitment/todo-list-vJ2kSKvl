(ns todo.core
  (:require
    [ring.adapter.jetty :as ring]
    [clojure.pprint]
    [environ.core :as environ]
    [compojure.core :refer [defroutes GET]]
    [clojure.java.jdbc :as postgres]
    [java-jdbc.ddl :as ddl]
    )
  )
;

(def db  {
          :classname "org.postgresql.Driver"
          :subprotocol "postgresql"
          :subname "//127.0.0.1:5432/todo"
          :user "dev"
          :password "dev"}
  )

(postgres/db-do-commands db 
  (ddl/create-table :ToDoList
    [:task "varchar(50)"]
    [:todo "boolean"]
    [:done "boolean"]
    )
  )

(postgres/insert! db :ToDoList :transaction? false 
  {:task "Buy cat food." :todo true :done false}
  {:task "Take out the trash." :todo true :done false})

(println (postgres/query db 
  ["SELECT * FROM todolist WHERE todo = ?" true]))

(defroutes routes
  (GET "/" [] "<h1>Under Construction!</h1> <img src='http://i.giphy.com/GsaGeoyPw24ww.gif' />")
  (GET "/about" [] "<h1>About</h1> <h3>This project is a to do list web app. The purpose is: </h3> 
    <ul> <li> To gauge how well I know Clojure & some common libraries.</li>
    <li> If I don't know Clojure, to gauge how well I can pick it up.</li> </ul>")
  )

(defonce server 
  (let [port (environ/env :port)]
    (ring/run-jetty #'routes {:port (read-string port) :join? false})
    (println "----")
    (println (str "Jetty server running on port " port))
    (println "----")
    )
  )