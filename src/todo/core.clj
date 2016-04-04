(ns todo.core
  (:require
    [ring.adapter.jetty :as ring]
    [ring.util.response :as response]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
    [clojure.pprint]
    [environ.core :as environ]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [todo.views.layout :as layout]
    [todo.views.contents :as contents]
    [todo.db :as db]
    )
  (:use ring.middleware.keyword-params)
  (:use ring.middleware.params)
  (:use ring.middleware.multipart-params)
  )

(defn post-task [request]
    (db/insert-item (str (get-in request [:params :task-input])))
    (use 'todo.core :reload-all)
    (response/redirect "/")
  )

(defn get-index [request] 
  (contents/index
    (for [item (db/select-todo-items)]
      (println item)
      ;(contents/item-list item) 
      )
    (contents/add-todo-form)
    )
  )

(defroutes todo-routes
  (GET "/" [] (layout/application "Home" (contents/index)))
  (route/resources "/")
  (POST "/post" [] post-task)
  (GET "/about" [] (layout/application "About" (contents/about)))
  (ANY "*" [] (route/not-found (layout/application "Page not found." (contents/not-found))))
  )

(defonce server 
  (let [port (environ/env :port)]
    (ring/run-jetty (wrap-defaults #'todo-routes (assoc site-defaults :security false :session false)) {:port (read-string port) :join? false})
    (println "----")
    (println (str "Jetty server running on port " port))
    (println "----")
    )
  )