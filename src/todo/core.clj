(ns todo.core
  (:require
    [ring.adapter.jetty :as ring]
    [clojure.pprint]
    [compojure.core :refer [defroutes GET]]
    )
  )

(defroutes routes
  (GET "/" [] "<h1>Under Construction!</h1> <br /> <img src='http://i.giphy.com/GsaGeoyPw24ww.gif' />"))

(defonce server (ring/run-jetty #'routes {:port 3000 :join? false}))