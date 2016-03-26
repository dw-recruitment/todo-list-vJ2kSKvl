(ns todo.core
  (:require
    [ring.adapter.jetty :as ring]
    [clojure.pprint]
    [environ.core :as environ]
    [compojure.core :refer [defroutes GET]]

    )
  )

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