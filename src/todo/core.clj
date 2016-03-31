(ns todo.core
  (:require
    [ring.adapter.jetty :as ring]
    [clojure.pprint]
    [environ.core :as environ]
    [compojure.core :refer [defroutes GET ANY]]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [todo.views.layout :as layout]
    [todo.views.contents :as contents]
    [todo.db :as db]
    )
  )

(defroutes routes
  (GET "/" [todo-list] (layout/application "Home" (contents/index)))
  (route/resources "/")
  (GET "/about" [] (layout/application "About" (contents/about)))
  (ANY "*" [] (route/not-found (layout/application "Page not found." (contents/not-found))))
  )

(defonce server 
  (let [port (environ/env :port)]
    (ring/run-jetty #'routes {:port (read-string port) :join? false})
    (println "----")
    (println (str "Jetty server running on port " port))
    (println "----")
    )
  )